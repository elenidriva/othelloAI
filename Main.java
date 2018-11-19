package Othello;

import java.util.*;

public class Main {

	private static boolean blackSkippedTurn,whiteSkippedTurn = false;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Board board = new Board();

		System.out.println("**************************************");
		System.out.print("      **WELCOME TO OUR OTHELLO**\n");

		board.printBoard();
		// Asks who plays first.
		int player = -1; 
		while (player!=1 && player!=2) {
		       System.out.print("Type '1' if you want to play first, type '2' if not.\n");
		       player = scan.nextInt(); 
		       //Player plays first 
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
		// Asks about Minimax's depth.
		System.out.print("Enter the depth of Ai-MinimaxAlphaBeta.\n");
		int depth = 5;
		depth = scan.nextInt();
		boolean gameContinues = true;
		while(gameContinues){
			if ((blackSkippedTurn == true && whiteSkippedTurn == true) || board.finished() == -1 ) { break; }

			if (board.getCurrentPlayerPiece() == "Black" ){ 

				/*Checks to see if Black player has any moves left*/
				if(board.finished() == 0) {
					System.out.println("No more moves left for " + board.getCurrentPlayerPiece() + ". Skipping Turn..\n" );
					board.changeTurn();
					blackSkippedTurn = true;
					continue;  			
				}

				System.out.println("\n");
				System.out.println(board.getCurrentPlayerPiece() + " player is playing ");
				System.out.println("Where do you want to place the next piece? ");
				System.out.println("Row : ");
				int row = scan.nextInt();
				System.out.println("Column : ");
				int col = scan.nextInt();
				board.placePieceForPlayer(row - 1,col - 1 ); // Για να λογικευτούν οι μεταβλητές
				board.printBoard();

			}else if ( board.getCurrentPlayerPiece() == "White") {
				
				/*Checks to see if White player has any moves left*/
				if (board.finished() == 0 ) {
					System.out.println("No more moves left for " + board.getCurrentPlayerPiece() + ". Skipping Turn..\n" );
					board.changeTurn();
					whiteSkippedTurn = true;
					continue;
				}

				System.out.println(board.getCurrentPlayerPiece() + " player is playing \n");
				System.out.println("Where do you want to place the next piece?\nAI is thinking.. ");

				MinimaxAlphaBeta Ai = new MinimaxAlphaBeta(board, depth, -10000000, 10000000, true);
				Ai.alphabeta();
				board.placePieceForAi(Ai.getRow(), Ai.getCol());
				System.out.println("Row " + (Ai.getRow()+1));
				System.out.println("Column " + (Ai.getCol()+1));
				board.printBoard();
			}

			blackSkippedTurn = false;
			whiteSkippedTurn = false;

		}
		System.out.println("GAME IS OVER..");
		System.out.println("THE WINNER IS : " + board.winner());  
		scan.close();
	}
}