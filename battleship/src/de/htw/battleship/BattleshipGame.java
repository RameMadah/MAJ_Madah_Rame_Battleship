package de.htw.battleship;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * An instance of this class holds the state of a running match and the game logic.
 */
public class BattleshipGame {

    private static final char EMPTY  = 0;
	private static final char HIT = 0;
	private static final char MISSED_SHOT = 0;
	final Board playerBoard;
    final Board villainBoard;

    /**
     * Set to TRUE to keep the game loop running. Set to FALSE to exit.
     */
    boolean running;

    /**
     * When playing, enemy ships should be hidden from the player.
     * Change below to FALSE for testing purposes during development of this program.
     */
    private final boolean hideVillainShips = false;

    /**
     * Creates a new game with new boards.
     */
    public BattleshipGame() {
        playerBoard = new Board();
        villainBoard = new Board();
    }

    /**
     * Creates a game based on saved boards from a previous game.
     */
    public BattleshipGame(Board playerBoard, Board villainBoard) {
        this.playerBoard = playerBoard;
        this.villainBoard = villainBoard;
    }


    /**
     * Main game loop. Keep running to play.
     * Interrupt the loop to get back to main menu.
     */
    public void run() {
        running = true;        
        System.out.println("Spiel gestartet. Drücke ENTER während der Zieleingabe,"+"\n" +"um zum Hauptmenü zurückzukehren.");
        System.out.println(" ");
        while (running) {
            playersTurn();
            if (running) villainsTurn();
        }
    }

    private void playersTurn() {

        System.out.println("Spieler ist am Zug.");
        villainBoard.print(hideVillainShips);
        System.out.println(" ");
        System.out.println("Zielfeld eingabe:");
      //int[] playerShot = null;

        //**************************************************************
        // TODO (s. Aufgabe 5)
        String myShot;      
        Scanner input = new Scanner(System.in);      
        
        try {  myShot = input.next();               
        int x = myShot.toUpperCase().charAt(0)- 65 ;
        int y = Integer.parseInt(myShot.substring(1))-1 ;
        int[] playerShot = new int[]{x, y};
         	   System.out.println("Sie haben auf " + convertCoordinatesToString(playerShot) +" gezielt.");    
         	  System.out.println("              ~~");
               System.out.println(" ");
            
            
            char hit = 'x';
            char empty = '.';
            char ship = 'O';
            char missed = '-';
       if(this.villainBoard.fields[x][y] == ship ) {
    	 this.villainBoard.fields[x][y] = hit; 
       	 System.out.println("Treffer!");
     	 System.out.println(" ");
     	pause();
     	 }
       
       if(this.villainBoard.fields[x][y] == empty  ) {
     	 this.villainBoard.fields[x][y] = missed;
         System.out.println("Daneben! Das war wohl nix.");
         System.out.println(" ");
         pause();
         }
       
       
       else 
       {
       System.out.println("Piraten nehmt eine Deckung, der Feind will schießen");
       System.out.println("haltet euch fest");}
       System.out.println(" ");
       }
               
      catch(NumberFormatException nfe) {
    	  System.out.println("Error 1");
    	System.out.println("Bitte nur Kooridinaten eingeben");
    	System.out.println(" ");
    	playersTurn();    	
       }
      catch (ArrayIndexOutOfBoundsException aio) {
    	  System.out.println("Error 2");
	   System.out.println("Bitte nur Kooridinaten eingeben");
	   System.out.println(" ");
	   playersTurn();
	   
	   }
      catch (InputMismatchException ime ) {
    	  System.out.println("Error 3");
  	   System.out.println("Bitte nur Kooridinaten eingeben");
  	   System.out.println(" ");
  	   playersTurn();
       }
        
       //
       
       
        //****************************************************************
      //exit();
      //pause();  
    }
    
    public void shooting() {
		
	}

	// player wants to exit game
    public void exit() {
        
    	System.out.println("  ");
        System.out.println("Spiel pausiert.");
        
        running = true;
        
    }
    

    private void villainsTurn() {

        System.out.println("Gegner ist am Zug.");
        playerBoard.print(false);
        

       // TODO (s. Aufgabe 6)
        
        int x;
        int y;
        char hit = 'x';
        char empty = '.';
        char ship = 'O';
        char missed = '-';
        
    do {
        x = new Random().nextInt(Board.BOARD_SIZE);
        y = new Random().nextInt(Board.BOARD_SIZE);
        }
    while (playerBoard.getField(x, y) != Board.EMPTY);

    int[] villainShot = new int[]{x, y};
        System.out.println("Gegner zielt auf " + convertCoordinatesToString(villainShot));
        
   if(this.playerBoard.fields[x][y] == ship ) {
	 this.playerBoard.fields[x][y] = hit; 
   	 System.out.println("Treffer!");
 	 System.out.println(" ");
 	 pause();
 	 }
   
   if(this.playerBoard.fields[x][y] == empty  ) {
 	 this.playerBoard.fields[x][y] = missed;
     System.out.println("Daneben! Das war wohl nix.");
     System.out.println(" ");
     pause();
     }
   
   
   else 
   {
   System.out.println("Bitte nur Kooridinaten eingeben");
   System.out.println(" ");
   pause();
   }
  
    }
      
   
        
        
    
    
    
    

    /**
     * Asks the user to press ENTER to continue.
     * Can be called anywhere in the game to avoid too much output at once.
     */
    public void pause() {
    	System.out.println("=======================");
        System.out.println();
        System.out.println("Drücke ENTER um fortzufahren...");
        System.out.println();
        System.out.println("=======================");
        new Scanner(System.in).nextLine();
    }
    
    
    
    
    

    /**
     * Gets an array with the two coordinates (x,y) the villain shoots at.
     */
    private int[] getVillainShot() {
        int x;
        int y;

        // Strategy to aim a shot: Pick a random field that is empty
        do {
            x = new Random().nextInt(Board.BOARD_SIZE);
            y = new Random().nextInt(Board.BOARD_SIZE);
        } while (playerBoard.getField(x, y) != Board.EMPTY);

        int[] shot = new int[]{x, y};
        System.out.println("Gegner zielt auf " + convertCoordinatesToString(shot));
        return shot;
    }


    public boolean isFinished() {
        return playerBoard.isWholeFleetSunk() || villainBoard.isWholeFleetSunk();
    }


    
    
    
    /**
     * Converts alphanumeric board coordinates to array indexes, e.g. A1 to [0,0]
     */
    public static int[] convertCoordinatesToInt(String input) {
        int x = input.toUpperCase().charAt(0) - 65;
        int y = Integer.parseInt(input.substring(1)) - 1;
        return new int[]{x, y};
    }

    /**
     * Converts array indexes to ahlphanumeric board coordinates, e.g. [0,0] to A1
     */
    public static String convertCoordinatesToString(int[] input) {
        char x = (char) (input[0] + 65);
        String y = Integer.toString(input[1]);
        return x + y;
    }
}