package Othello;
import java.util.*;
import Othello.Board;
import Othello.MinimaxAlphaBeta;
import Othello.Board.Point;

public class Main {
  public static void main(String[] args) {
      Scanner scan = new Scanner(System.in);
      Board board = new Board();
      System.out.println("**************************************");
      System.out.print("      **WELCOME TO OUR OTHELLO**\n");
      board.printBoard();
      int player = -1; //default
      while (player!=1 && player!=2) {
       System.out.print("Type '1' if you want to play first, type '2' if not.\n");
       player = scan.nextInt(); 
       //Player = Black | AI = White/Minimizer 
        if (player == 1) {
    	 		System.out.println("You are playing first as: " + board.getCurrentPlayerPiece());
      	}else if(player==2) {
      		System.out.println("You are playing second as: " + board.getCurrentPlayerPiece());
      		board.changeTurn();
      		
      	}else {
      		player=-1;
      		System.out.println("Invalid input.\n");
      	}
      }
        int moves =2;
        while(moves==1 || moves ==2) {
        	if (board.getCurrentPlayerPiece()=="Black") {
        		if(board.finished()==0) {
        			System.out.println("No more moves left for "+board.getCurrentPlayerPiece() +". Skipping Turn..\n" );
        			board.changeTurn();
        			moves--;
        			continue;	
        		}
        		System.out.println(board.getCurrentPlayerPiece()+": Where do you want to place the next piece? ");
                System.out.println("Row : ");
                int row = scan.nextInt();
                System.out.println("Column : ");
                int col = scan.nextInt();
                if(!board.placePiece(row - 1,col - 1 )) System.out.println("Illegal piece placement.");
                board.printBoard();
                moves = 2;
                continue;
        	} else if(board.getCurrentPlayerPiece()=="White") {
        		if(board.finished()==0) {
        			System.out.println("No more moves left for "+board.getCurrentPlayerPiece() +". Skipping Turn..\n" );
        			board.changeTurn();
        			moves--;
        			continue;	
        		}
        		System.out.println("AI is thinking.. ");
                System.out.println(board.evaluate());
                MinimaxAlphaBeta Ai = new MinimaxAlphaBeta(board, 1, -1000, 1000, true);
                Ai.alphabeta();
                board.placePiece(Ai.getRow(), Ai.getCol());
                System.out.println("AI played its piece at: ("+ (Ai.getRow()+1)+ " , "+ (Ai.getCol()+1)+ ")");
                board.printBoard();
                moves = 2;
                continue;
        	}
      }
    	System.out.println("GAME IS OVER..");
    	System.out.println("THE WINNER IS : " + board.winner());        
        scan.close();
  }
}
