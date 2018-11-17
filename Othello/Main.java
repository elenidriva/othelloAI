package Othello;

import java.util.*;

import Othello.Board;
import Othello.MinimaxAlphaBeta;
import Othello.Board.Point;

public class Main {
  public static void main(String[] args) {
      Scanner scan = new Scanner(System.in);
      Board board = new Board();

      board.printBoard();
  	
      boolean gameContinues = true;
      while(gameContinues){
        
      /*  System.out.print("Type '1' if you want to play first, type '2' if not");
        if (player == 2) {
    	 		board.changeTurn();
    	 		System.out.println(board.getCurrentPlayerPiece() + " player is playing \n");
      	}
      	*/
       // int player = scan.nextInt();  
        System.out.println("\n");
        System.out.println(board.getCurrentPlayerPiece() + " player is playing \n");
        board.evaluate();
        System.out.println("Where do you want to place the next piece? ");
        System.out.println("Row : ");
        int row = scan.nextInt();
        System.out.println("Column : ");
        int col = scan.nextInt();
        board.placePiece(row - 1,col - 1 ); 
        board.printBoard(); 
        if (board.finished() == true) {
        	System.out.println("THE WINNER IS : " + board.winner());
        	gameContinues = false;
        }
        if ( board.getCurrentPlayerPiece() == "White") {
          System.out.println(board.getCurrentPlayerPiece() + " player is playing \n");
          System.out.println("Where do you want to place the next piece? ");
          System.out.println(board.evaluate());
          MinimaxAlphaBeta Ai = new MinimaxAlphaBeta(board, 4, -1000, 1000, false);
          Ai.alphabeta();
          board.placePiece(Ai.getRow(), Ai.getCol());
          System.out.println("Row " + (Ai.getRow()+1));
          System.out.println("Column " + (Ai.getCol()+1));
          board.printBoard();
        }
        
      }
      scan.close();
  }
}