import java.util.*;


public class Main {

  public static void main(String[] args) {
 
       System.out.println("WELCOME TO OTHELLO.");
       //KEEP SCANNING TILL END OF GAME
       Board board = new Board();
	  //while(!board.endOfGame()) {
       System.out.println();
       char c;
       do {
    	   System.out.println("Would you like to play first? Answer with y/n");
       @SuppressWarnings("resource")
	Scanner reader = new Scanner(System.in);
        c = reader.next("[a-zA-Z]").charAt(0);
       if(c=='y' || c=='Y') {
    	   board.setWhoPlayed('W');
    	   System.out.println("You're playing as BLACK!");
    	   break;
       }else if (c=='n' || c=='N') {
    	   board.setWhoPlayed('B');
    	   System.out.println("You're playing as WHITE!");
    	   break;
       }else {
    	   System.out.println("Please enter y or n...");
       }
       
       } while (c!='y' || c!='Y' || c!='n' || c!='N');
       
       switch(board.getWhoPlayed()) {
       //X plays
       case Board.W:
    	   System.out.println("'B' is playing...");
		//O plays  
    	   case Board.B:
    		   System.out.println("'W' is playing...");
		
    		   default:
    			   break;
	  }
	  
	  
	  
	  
	  board.print();
	  
	  
	  
	  
  }
  
}