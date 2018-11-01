


public class Board{
	
	public static final int B = 1;
	public static final int W = -1;
	public static final int EMPTY = 0;
	private int whoPlayed;
	private int [][] gameBoard;
	//private Move move;!
	
	//default constructor, initializing board with empty values.
	public Board() {
		gameBoard = new int[8][8];
		//gia na orisw an o minimax paizei prwtos i oxi tha allazw sto initial
		//state "poios" epaikse k autho tha ekxwreitai ston algorithmo minimax
		whoPlayed = W;
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				gameBoard[i][j] = EMPTY;	
			}
			// initial state of the game
			gameBoard[3][3]= 'B';
			gameBoard[3][4]= 'W';
			gameBoard[4][4]= 'B';
			gameBoard[4][3]= 'W';
		
		}
	}
	//This will keep constructing new instances of the game depending on THE MOVE
	//that was made by the one whoPlayed.
	public Board(Board board) {
		whoPlayed = board.whoPlayed;
		
		
		
	}
	
	
	// print the board
	public void print() {
		System.out.println(" 1  2  3  4  5  6  7  8");

		for(int i=0; i<8; i++) {
			
			for(int j=0; j<8; j++) {
				
				if(gameBoard[i][j]=='B') {
					System.out.print(" B ");
				}else if(gameBoard[i][j]=='W') {
					System.out.print(" W ");
				} else {
					System.out.print(" _ ");
				}
			}
			System.out.println(i+1);
		}
	}
	
	public void setWhoPlayed(int whoPlayed)
	{
		this.whoPlayed=whoPlayed;
	}
	public int getWhoPlayed() {
		return whoPlayed;
	}
	
	
	
	
	
	
	
	
	
	
	/*public boolean endOfGame() {
		
		if 
		
	}
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}