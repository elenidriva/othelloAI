import java.util.*;


public class Main {

  public static void main(String[] args) {
 
       System.out.println("||WELCOME TO OTHELLO.||");
       //KEEP SCANNING TILL END OF GAME
       Board board = new Board();
	  //while(!board.endOfGame()) {
       System.out.println();
       board.print();
       char c;
       do {
    	   System.out.println("Would you like to play first? Answer with y/n");
    	   @SuppressWarnings("resource")
    	   Scanner reader = new Scanner(System.in);
    	   c = reader.next("[a-zA-Z]").charAt(0);
    	   if(c=='y' || c=='Y') {
    		   board.setWhoPlayed(Board.W);
    		   System.out.println("You're playing as BLACK!");
    		   break;
    	   }else if (c=='n' || c=='N') {
    		   board.setWhoPlayed(Board.B);
    		   System.out.println("You're playing as WHITE!");
    		   break;
    	   }else {
    		   System.out.println("Please enter y or n...");
    	   }
       } while (c!='y' || c!='Y' || c!='n' || c!='N');
       
       int x; int y;
       //boolean invalid=true;
       switch(board.getWhoPlayed()) {
       //Paizei o epomenos = B
       case Board.W:
    	   System.out.println("'B' is playing...");
    	  // while(invalid) {
    	   System.out.println("Enter the coordinates you would like to place your piece \n");
    	   System.out.println("Row: ");
    	   Scanner reader = new Scanner(System.in);
    	   x = reader.next().charAt(0);
    	   System.out.println("Column: ");
    	   y = reader.next().charAt(0);
    	 //  if(x >0 && x<=8 && y>0 && y<=8) invalid = false;
    	   //Need to check if the move the player made is Valid, if not keep looping back.
		//Paizei o epomenos = W
    	   case Board.B:
    		   System.out.println("'W' is playing...");
		
    		   default:
    			   break;
	  }
	  
	  
	  
	  
	 
	  
	  
	  
	  
  }
  
}