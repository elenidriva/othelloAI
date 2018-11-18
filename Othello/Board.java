package Othello;
import java.util.*;

// Added .txt with input cases for testing purposes.
//++ coding and app aesthetics	
// Fixed main - working. Also, no longer pops illegal piece placement on AI moves.
// TODO Check Minimax?
// TODO DEPTH?
// TODO add evaluate() method inside Board.java

public class Board{

	public static enum Piece{
		BLACK, // 
		WHITE;
	}

	public Piece[][] board;
	public Piece currentPlayerPiece;
	private int counter;
	public int numberOfWhiteDisks;
	public int numberOfBlackDisks;

	Point topLeftStartingPointDownDir = new Point(0,0);
	Point topLeftStartingPointRightDir = new Point(0,0);
	
	Point topRightStartingPointDownDir = new Point(0,7);
	Point topRightStartingPointLeftDir = new Point(0,7);
	
	Point bottomLeftStartingPointUpDir = new Point(7,0);
	Point bottomLeftStartingPointRightDir = new Point(7,0);
	
	Point bottomRightStartingPointUpDir = new Point(7,7);
	Point bottomRightStartingPointLeftDir = new Point(7,7);
	

	public static class Point {
		public final int row;
		public final int col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
  
	private static final Point[] possibleDirections = new Point[]{
			new Point(1, 0),
			new Point(1, 1),
			new Point(0, 1),
			new Point(-1, 1),
			new Point(-1, 0),
			new Point(-1, -1),
			new Point(0, -1),
			new Point(1, -1),
	};

	interface CellHandler {
		boolean handleCell(int row, int col, Piece piece);

		boolean handleCell(Point step, int row, int col, Piece piece);
	}

	/*iterateCells() accepts some direction and navigates through it
  till it hits an empty cell or border */
	void iterateCells(Point start, Point step, CellHandler handler) {

		for (int row = start.row + step.row, col = start.col + step.col;
				isValidPosition(row,col);
				row += step.row, col += step.col) {

			Piece piece = board[row][col]; 
			// empty cell
			if (piece == null) // fillArray() with EMPTY or use NULL
				break;
			// handler can stop iteration
			if (handler.handleCell(row, col, piece) == false)
				break;
		}
	}

	void iterateCellsForStability(Point start, Point step, CellHandler handler) {
		for (int row = start.row + step.row, col = start.col + step.col;
				isValidPosition(row,col);
				row += step.row, col += step.col) {

			Piece piece = board[row][col]; 
			// empty cell
			if (piece == null) // fillArray() with EMPTY or use NULL
				break;
			// handler can stop iteration
			if (handler.handleCell(step, row, col, piece) == false)
				break;
		}
	}


	static class CheckCellHandler implements CellHandler {
		private final Piece otherPiece;
		private boolean directionHasOpponentsPlayerPiece = false;
		private boolean endsWithMine = false;

		public CheckCellHandler(Piece otherPiece) {
			this.otherPiece = otherPiece;
		}

		@Override
		public boolean handleCell(int row, int column, Piece piece) {
			if (piece == otherPiece) {
				directionHasOpponentsPlayerPiece = true; 
				return true;
			} else {
				endsWithMine = true; 
				return false;
			}
		}

		public boolean isGoodMove() {
			return directionHasOpponentsPlayerPiece && endsWithMine;
		}

		@Override
		public boolean handleCell(Point step, int row, int col, Piece piece) {
			// TODO Auto-generated method stub
			return false;
		}

	}

	class FlipCellHandler implements CellHandler {
		private final Piece myPiece;
		private final Piece otherPiece;
		private final List<Point> currentFlipList = new ArrayList<Point>();

		public FlipCellHandler(Piece myPiece, Piece otherPiece) {
			this.myPiece = myPiece;
			this.otherPiece = otherPiece;
		}

		@Override
		public boolean handleCell(int row, int column, Piece piece) {
			if (piece == myPiece) {
				// flip all cells
				for (Point p : currentFlipList) {
					board[p.row][p.col] = myPiece;
				}
				return false;
			} else {
				currentFlipList.add(new Point(row, column));
				return true;
			}
		}

		@Override
		public boolean handleCell(Point step, int row, int col, Piece piece) {
			// TODO Auto-generated method stub
			return false;
		}
	}

	class StabilityHandler implements CellHandler {
		private final Piece myPiece;


		public StabilityHandler(Piece myPiece) {
			this.myPiece = myPiece;
		}

		public boolean handleCell(Point step,int row, int col, Piece piece) {
			if (piece == myPiece){				
				setNewStartingPoint(step,row,col);
				return true;
			}

			return false;
		} 

		public void setNewStartingPoint(Point step, int row, int col){
			if (step.row == 1 && step.col == 0 && row == 0) {		  
				topLeftStartingPointDownDir = new Point(row,col);
			}else if (step.row == 1 && step.col == 0 && row != 0 ){
				topRightStartingPointDownDir = new Point(row,col);
			}
			if (step.row == 0 && step.col == 1 && col == 0 ){
				topLeftStartingPointRightDir = new Point(row,col);
			}else if(step.row == 0 && step.col == 1 && col != 0 ){
				bottomLeftStartingPointRightDir = new Point(row,col);
			}
			if (step.row == -1 && step.col == 0 && row == 0){
				bottomRightStartingPointUpDir = new Point(row,col);
			}else if (step.row == -1 && step.col == 0 && row != 0){
				bottomLeftStartingPointUpDir = new Point(row,col);
			}
			if (step.row == 0 && step.col == -1 && col == 0 ) {
				topRightStartingPointLeftDir = new Point(row,col);
			}else if (step.row == 0 && step.col == -1 && col == 0){
				topLeftStartingPointRightDir = new Point(row,col);
			}
		}



		@Override
		public boolean handleCell(int row, int col, Piece piece) {
			// TODO Auto-generated method stub
			return false;
		}
	}

	private boolean isValidPosition(int x, int y){
		if( x < 0 || x >= 8 || y < 0 || y >=  8 ){
			return false;
		}
		return true;
	}

	/*implement CheckCellHandler and iterate
  through all possible directions to see if we can flip in that direction*/
	int checkLegalPlay(int row, int col) {
		int checkLegalPlay = 0 ;
		Piece otherPiece = ( currentPlayerPiece == Piece.BLACK ) ? Piece.WHITE : Piece.BLACK;
		Point start = new Point(row, col);
		for (Point step : possibleDirections) {
			// handler is stateful so create new for each direction
			CheckCellHandler checkCellHandler = new CheckCellHandler(otherPiece);
			iterateCells(start, step, checkCellHandler);
			if (checkCellHandler.isGoodMove())
				checkLegalPlay++;
				
		}
		return checkLegalPlay;
	}

	/**
	 * @param row
	 * @param col
	 * Flips the pieces by iterating the board, in order to replace them with the appropriate colour tile.
	 */
	private void flipPieces(int row, int col) {
		Piece otherPiece = ( currentPlayerPiece == Piece.BLACK ) ? Piece.WHITE : Piece.BLACK;
		Point start = new Point(row,col);
		for (Point step: possibleDirections) {
			FlipCellHandler flipCellHandler = new FlipCellHandler(currentPlayerPiece, otherPiece);
			iterateCells(start,step,flipCellHandler);
		}
	}

	public Board(){
		this.board = new Piece[8][8];
		this.currentPlayerPiece = Piece.BLACK;
		this.counter = 4;
		this.numberOfBlackDisks = 2;
		this.numberOfWhiteDisks = 2;
		
	    this.board[3][3] = Piece.WHITE;
	    this.board[3][4] = Piece.BLACK;
	    this.board[4][3] = Piece.BLACK;
	    this.board[4][4] = Piece.WHITE;
		//  fillArray(this.board);

		// Have created .txt input cases for testing reasons.

	}

	public Piece[][] getBoard() {
		return board;
	}

	public void setBoard(Piece[][] board) {
		this.board = board;
	}

	public boolean placePiece(int i, int j) {
		if (this.checkLegalPlay(i,j)>0) {
			this.board[i][j] = this.currentPlayerPiece;
			flipPieces(i,j);
			//updateBoard(i, j) 
			changeTurn();
			this.counter++;
			updateNumberOfDisks();
			return true;
		} else {
			//System.out.println("Illegal piece placement \n");
			return false;
		}
	}

	public String getCurrentPlayerPiece(){
		if (currentPlayerPiece == Piece.BLACK){
			return "Black";
		}else {
			return "White";
		}
	}

	public void changeTurn() {
		if (currentPlayerPiece == Piece.BLACK){
			currentPlayerPiece = Piece.WHITE;
		} else {
			currentPlayerPiece = Piece.BLACK;
		}
	}

	public int finished(){
		if(this.counter > 63) return -1;
		if(mobility(this.currentPlayerPiece)==0) {	
			return 0;
		}else {
			return 1;
		}
	}


	private void updateNumberOfDisks(){
		int white = 0;
		int black = 0;
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(this.board[i][j] == Piece.BLACK){
					black++;
				}
				else if(this.board[i][j] == Piece.WHITE){
					white++;
				}
			}
		}
		this.numberOfBlackDisks = black;
		this.numberOfWhiteDisks = white;
	}

	public String winner(){
		updateNumberOfDisks();
		if(this.numberOfBlackDisks > this.numberOfWhiteDisks){
			return "BLACK";
		}else if (this.numberOfBlackDisks < this.numberOfWhiteDisks){
			return "WHITE";
		}else{
			return null;
		}
	}
	public void printBoard(){
		System.out.println("**************************************");
		System.out.println("|   || 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | ");
		for (int row = 0; row <= 7; row++){
			System.out.print("| " + (row + 1) + " |");
			for (int col = 0; col <= 7; col++){
				if (board[row][col] == Piece.BLACK){
					System.out.print("| " + "B" + " "); 
				} else if(board[row][col] == Piece.WHITE){
					System.out.print("| " + "W" + " ");
				} else {
					System.out.print("| " + "-" + " ");
				}
			}
			System.out.print("| \n");
		}
	}/* /printBoard() */

	// Mobility function: Counts eligible moves for currentPlayer (otherPiece too)
	int mobility(Piece currentPlayerPiece ){
		int mobilityCurr = 0;
		for( int row=0; row<7;row++){
			for( int col=0; col<7;col++){
				if (board[row][col] != Piece.BLACK && board[row][col] != Piece.WHITE ){
					mobilityCurr = mobilityCurr + checkLegalPlay(row,col);
				}
			}
		}

		return mobilityCurr;
	}

	public void copyBoard(Board b) {
		Piece [][] newB = b.getBoard();
		for (int row = 0; row < 7; row++) {
			for (int col = 0; col < 7; col++) {
				this.board[row][col] = newB[row][col];
			}
		}
		// maybe some updates needed ?? 
		//if (b.getCurrentPlayerPiece() == "White") {
			
		//}
	}

	  public int discDif() {
		  //System.out.print("\nBlack disks " + this.numberOfBlackDisks);
		  // System.out.print("\nWhite disks " + this.numberOfWhiteDisks);
		  //System.out.print(this.numberOfBlackDisks - this.numberOfWhiteDisks);
		  return this.numberOfBlackDisks -  this.numberOfWhiteDisks;
	  }
	  //Stability function: Checks whether corner and edges cells are stable.
	  public int stability(Piece currentPlayerPiece ){
		  int currStab = 0;
		  int col1 = 1; int row1 = 1;  int col2 = 1; int row2 = 1;
		  if (board[0][0]== currentPlayerPiece){
			  currStab++;
			  while(board[0][col1]== currentPlayerPiece) {
				  currStab++; col1++;
			  }
			  while(board[row1][0]== currentPlayerPiece) {
				  currStab++; row1++;
			  }
		  }
		  if (board[0][7]== currentPlayerPiece){
			  currStab++;
			  int i = 1;
			while (board[0][7-i] == currentPlayerPiece) {
				if (i!=col1) {
					currStab++; i++;
				}else {
					break;
				}
			}
			while(board[row2][7] == currentPlayerPiece) {
				currStab++; row2++;
			 }			  					
		  }
		  if (board[7][0]== currentPlayerPiece){
			  currStab++;
			  int i = 1;
			  while (board[7-i][0]== currentPlayerPiece){
				  if (i!=row1) {
					  currStab++; i++;
				  }else {
					  break;
				  }
			  }
			  while(board[7][col2]== currentPlayerPiece) {
					  currStab++; col2++;
			  }	
		  }
		  if (board[7][7]== currentPlayerPiece){
			  currStab++;
			  int i = 6;
			  while(board[i][7]== currentPlayerPiece) {
				  if (i!=row2) {
					  currStab++; i--;
				  } else {
					  break;
				  }
			  }
			  i=6;
			  while(board[7][i]== currentPlayerPiece) {
				if (i!=col2) {
					currStab++; i--;
				}else {
					break;
				}
			}
		  }
		  return currStab;
	  }

//square weights + parity
	public int evaluate(){
		  
		 if(this.counter <=20) {
			//System.out.println("mob: " +mobility(currentPlayerPiece));                     // - mobility(otherPiece));
			//System.out.println("stab: " +stability(currentPlayerPiece));                      //- stability(otherPiece));
			// System.out.println("\ndisc Diff medthod:  "+ discDif()+"\n");
			// System.out.println("\nEvaluate:  ");
			 return 10* mobility(currentPlayerPiece) + 10*stability(currentPlayerPiece)+ 10* discDif();
		 }else if(this.counter <= 58) {
			 System.out.println("\nmobility is "+ mobility(currentPlayerPiece));
			 System.out.println("\nstability is "+ stability(currentPlayerPiece));
			 System.out.println("\ndisc Diff medthod:  "+ discDif());
			 return 10* mobility(currentPlayerPiece);
			 
		 }
		 else {
			 System.out.println("\nmobility is "+ mobility(currentPlayerPiece));
			 System.out.println("\nstability is "+ stability(currentPlayerPiece));
			 System.out.println("\ndisc Diff medthod:  "+ discDif());
			 return 10* mobility(currentPlayerPiece);
		 }
	  }

}