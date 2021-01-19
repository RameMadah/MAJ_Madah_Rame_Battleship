package de.htw.battleship;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


/*
 * An instance of this class holds the state of a running match and the game logic.
 */
public class BattleshipGame {

   
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
        System.out.println("  ");
        System.out.println("Spiel gestartet."+"\n"+"Drücke [ ENTER ] während der Zieleingabe,"+"\n" +"um zum Hauptmenü zurückzukehren.");
        System.out.println(" ");
        while (running) {
            playersTurn();
            if (running) villainsTurn();
        }
    }

    /**
     * gives the possibility to give coordinates or get back to the main menu
     * 
     */
    private void playersTurn() {
    	
    	 String myShot;      
         Scanner input = new Scanner(System.in);   
        
         scored();
        System.out.println("Spieler ist am Zug.");
        villainBoard.print(hideVillainShips);  
        System.out.println(" ");
        System.out.println("Zielfeld eingabe:");
      

        //**************************************************************
        // TODO (s. Aufgabe 5)
        //**************************************************************
          
        
        try {  myShot = input.nextLine();
        if(myShot.isEmpty()) {     //If user inserted "" the game goes back to main menu.
            System.out.println(" ");
            exit();
        }
        //If user inserts something else than "".
        int x = myShot.toUpperCase().charAt(0)- 65 ;
        int y = Integer.parseInt(myShot.substring(1)) ;
        int[] playerShot = new int[]{x, y};
       //print out the coordinates after converting them to string
       System.out.println("Sie haben auf " + convertCoordinatesToString(playerShot) +" gezielt.");    
   y = Integer.parseInt(myShot.substring(1))-1 ;
 	   System.out.println("              ~~");
       System.out.println(" ");
    
    
            char hit = 'X';
            char empty = '.';
            char ship = 'O';
            char missed = '-';
           
       	
            
       //printing out the damaged ship piece
       if(this.villainBoard.fields[x][y] == ship ) {
    	 this.villainBoard.fields[x][y] = hit; 
       	 System.out.println("Treffer!");
       	 System.out.println("+1 Schuss zusätzlich.");
         System.out.println(" ");
         // if statements to checks if ships are sunk
         if (x == 0 && this.villainBoard.fields[0][y] == hit && this.villainBoard.fields[0][y-1] == hit )
        	 System.out.println("Piratenschiff versenkt!");
         if(x==2 && this.villainBoard.fields[x][y] == hit && this.villainBoard.fields[x][y-1] == hit && this.villainBoard.fields[x][y-2] == hit)
        	 System.out.println("Piratenschiff versenkt!"); 
         if(x==4 && this.villainBoard.fields[x][y] == hit && this.villainBoard.fields[x][y-1] == hit && this.villainBoard.fields[x][y-2] == hit )
        	 System.out.println("Piratenschiff versenkt!"); 
          if(x==6 && this.villainBoard.fields[x][y] == hit && this.villainBoard.fields[x][y-1] == hit && this.villainBoard.fields[x][y-2] == hit && this.villainBoard.fields[x][y-3] == hit )
        	  System.out.println("Piratenschiff versenkt!");   
          if(x==8 && this.villainBoard.fields[x][y] == hit && this.villainBoard.fields[x][y-1] == hit && this.villainBoard.fields[x][y-2] == hit && this.villainBoard.fields[x][y-3] == hit && this.villainBoard.fields[x][y-4] == hit)
        	  System.out.println("Piratenschiff versenkt!");  
          if(x==9 && this.villainBoard.fields[x][y] == hit && this.villainBoard.fields[x][y-1] == hit && this.villainBoard.fields[9][y-2] == hit && this.villainBoard.fields[x][y-3] == hit && this.villainBoard.fields[x][y-4] == hit) {   
           System.out.println("Piratenschiff versenkt!");
         }
      
        win();     
     	playersTurn();
     	 }
       
     //printing out the missed shots
      
        
       //printing out the missed shots
       if(this.villainBoard.fields[x][y] == empty  ) {
     	 this.villainBoard.fields[x][y] = missed;
         System.out.println("Daneben! Das war wohl nix.");
         
         System.out.println(" ");
         pause();
         }
       
       
       else 
       {
       System.out.println("Piraten lasst uns die Welt eroberen !!");
       System.out.println("haltet euch fest");
       System.out.println(" ");
       }
        
        }    
      catch(NumberFormatException nfe) {
    	  System.out.println("Error 1");
    	System.out.println("Bitte nur Koordinaten eingeben");
    	System.out.println(" ");
    	playersTurn();    	
       }
      catch (ArrayIndexOutOfBoundsException aio) {
         System.out.println("");
       System.out.println("Spiel wird geladen...");	   
	   System.out.println(" ");
	   playersTurn();
	   
	   }
      catch (InputMismatchException ime ) {
    	  System.out.println("Error 3");
  	   System.out.println("Bitte nur Koordinaten eingeben");
  	   System.out.println(" ");
  	   playersTurn();
       }
     
    }
    
  

	// player wants to exit game
    public void exit() {
        
    	System.out.println("  ");
        System.out.println("Spiel pausiert.");
        BattleshipApplication.GetInstance().mainMenu();
    }
    
    
    
 public void lastexit() {
        
		 System.out.println("  ");
	     System.out.println("Spiel pausiert.");	  
	     System.out.println("  ");
	     System.out.println("__________________  ");
	     scorelist();
	     System.out.println("__________________  ");
	     System.out.println("  ");
        BattleshipApplication battleshipApplication = new BattleshipApplication();
        battleshipApplication.mainMenu();
    }
    

 /**
  * With a updated AI the computer will hit the player fields with a random yet a good strategy
  *
  */
    private void villainsTurn() {

        System.out.println("Gegner ist am Zug.");
        playerBoard.print(false);

       // TODO (s. Aufgabe 6)

        int x;
        int y;
        int AI1 = 0;
        int AI2 = 0;
      
        
        char hit = 'X';
        char empty = '.';
        char ship = 'O';
        char missed = '-';
        
    do {  // generate random numbers that are most likely to hit the player ships
        x = AI1 ;      		
        y = AI2 ;
        AI2 = (int) (Math.random()*9);
  	    if (AI2 ==0) {
  	    	AI1=0;
  	    	AI2=(int) (Math.random()*(9 - 6)); 	    	
  	    }
  	    else if (AI2 == 1) {
			AI1=0;
			AI2 =(int) (Math.random()*4)+5;
			}
  	    else if (AI2 == 2) {
			AI1=2;
			AI2 =(int) (Math.random()*9);
			}
		
  	    else if (AI2 == 3) {
			AI1=8;
			AI2=(int) (Math.random()*9);
		    }
		
  	    else if (AI2 == 4) {
			AI1=4;
			AI2=(int) (Math.random()*9);
		    }
  	    
  	    else if (AI2 == 5){ 
			AI1=9;
			AI2=(int) (Math.random()*9);
		    }
  	    else if (AI2 == 6){ 
			AI1=6;
			AI2=(int) (Math.random()*9);
		    }
  	    else if (AI2 == 7){ 
			AI1=6;
			AI2=(int) (Math.random()*9);
		    }
  	    else if (AI2 == 8) {
			AI1 = 8;
			AI2=(int) (Math.random()*9);
			}
  	    else  {
			AI1 = 9;
			AI2=(int) (Math.random()*9);
			}
           
        }
    //***************( HARD MODE )******************************
    //if you want to set the game one the hard mode the use the code bellow it will always hit a ship
    //while (playerBoard.getField(x, y) == Board.EMPTY 
    //|| playerBoard.getField(x, y) == Board.HIT || playerBoard.getField(x, y) == Board.MISSED_SHOT );
    
    
    //prevent the computer from aiming on a known field
    while (playerBoard.getField(x, y) == Board.MISSED_SHOT || playerBoard.getField(x, y) == Board.HIT );
  
    int[] villainShot = new int[]{x, y};
    //printing out the enemy coordinates
    System.out.println("Gegner zielt auf " + convertCoordinatesToString(villainShot));
    
    //in case of successful shot
   if(this.playerBoard.fields[x][y] == ship ) {
	 this.playerBoard.fields[x][y] = hit; 
   	 System.out.println("Treffer!");
     System.out.println("Gengner bekommt +1 Schuss. ");
     
     //if statements to checks if ships are sunk
   try {  if (x == 0 && this.playerBoard.fields[0][y] == hit && this.playerBoard.fields[0][y-1] == hit )
    	 System.out.println("Piratenschiff versenkt!");
     if(x==2 && this.playerBoard.fields[x][y] == hit && this.playerBoard.fields[x][y-1] == hit && this.playerBoard.fields[x][y-2] == hit)
    	 System.out.println("Piratenschiff versenkt!"); 
     if(x==4 && this.playerBoard.fields[x][y] == hit && this.playerBoard.fields[x][y-1] == hit && this.playerBoard.fields[x][y-2] == hit )
    	 System.out.println("Piratenschiff versenkt!"); 
      if(x==6 && this.playerBoard.fields[x][y] == hit && this.playerBoard.fields[x][y-1] == hit && this.playerBoard.fields[x][y-2] == hit && this.playerBoard.fields[x][y-3] == hit )
    	  System.out.println("Piratenschiff versenkt!");   
      if(x==8 && this.playerBoard.fields[x][y] == hit && this.playerBoard.fields[x][y-1] == hit && this.playerBoard.fields[x][y-2] == hit && this.playerBoard.fields[x][y-3] == hit && this.playerBoard.fields[x][y-4] == hit)
    	  System.out.println("Piratenschiff versenkt!");  
      if(x==9 && this.playerBoard.fields[x][y] == hit && this.playerBoard.fields[x][y-1] == hit && this.playerBoard.fields[9][y-2] == hit && this.playerBoard.fields[x][y-3] == hit && this.playerBoard.fields[x][y-4] == hit) {   
       System.out.println("Piratenschiff versenkt!");
     }
   } catch (ArrayIndexOutOfBoundsException AIO) {
	   lost();
		 pause();
		 villainsTurn(); 
   }
     lost();
	 pause();
	 villainsTurn();  
 	 }
   // in case of missed shot
   else if(this.playerBoard.fields[x][y] == empty  ) {
 	 this.playerBoard.fields[x][y] = missed;
     System.out.println("Daneben! Das war wohl nix.");
     System.out.println(" ");
     pause();
     }
   
   
    }
    
    /**
     * counts the drowned ships of the player and depending on that will be decided if 
     * the player lost the game or not
     */
    public void lost() {
    	int drown = 0;
        char hit = 'X';
        
    	for (int i=0;i<this.playerBoard.fields.length;i++) {
    		
    		for (int j=0;j<this.playerBoard.fields[i].length ;j++) {
    if (this.playerBoard.fields[i][j] == hit)
    	drown += 1;
    		}
    	}
    	if (drown == 19) { //if  the player lost 19 ships then he loses 
        System.out.println("Spiel ist verloren");
        System.out.println("~~~~~~~~~~~~~~~~~~");
           	
    	 pause();
    	 lastexit();
    	 running = false;
    	}
    }
      
   /**
    * counts the drowned ships of the computer and depending on the will 
    * be decided if the player wins and leads to the Main menu
    *  
    */
     public void win() {
        	int sunk = 0;
        	char hit = 'X';
        	
        	for (int i=0;i<this.villainBoard.fields.length;i++) {       	
            	for (int j=0;j<this.villainBoard.fields[i].length;j++) {
        if (this.villainBoard.fields[i][j] == hit)
        	sunk += 1;
        		}
            }
        	
        	if (sunk == 19)  { // if the player sinked 19 ships then he wins 
        		System.out.println("Spiel ist gewonnen!!");
        		System.out.println("~~~~~~~~~~~~~~~~~~");
        		
                pause();
                lastexit();
                
        	}
        }
    
     /**
      * prints the Score list  on the console
      */
     public void scorelist() {
    	   
    	   	 int total=0;
    	   	 char missed = '-';
    	  	     char hit = 'X';
    			   	
    		for (int i=0; i<this.villainBoard.fields.length; i++) {
    	     for (int j=0; j<this.villainBoard.fields[i].length; j++) {
    	   	 if ( this.villainBoard.fields[i][j] == hit ) // adds successful shot to total shots
    	   		 total ++;
    	    	 if ( this.villainBoard.fields[i][j] == missed)// adds missed shot to total shots
    	    		total ++;
    			   }
    	   	  
    			}
    		System.out.println("SCORE LISTE");
    		System.out.println(BattleshipApplication.getName()+"'s  score ist "+ total); // prints the name & the total score
    		System.out.println("player "+" score ist "+ "00");
    		System.out.println("player "+" score ist "+ "00");
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
     * counts all shots of the player and gives back the total number of shots
     * @return  total  the total number of all shots
     * 
     */
    
    public int scored() {
    	 int total=0;
    	 char missed = '-';
   	     char hit = 'X';
		   	
	for (int i=0; i<this.villainBoard.fields.length; i++) {
      for (int j=0; j<this.villainBoard.fields[i].length; j++) {
    	 if ( this.villainBoard.fields[i][j] == hit ) 
    		 total ++;
     	 if ( this.villainBoard.fields[i][j] == missed)
     		total ++;
		   }
    	  
		}
	System.out.println("Dein score ist "+ total );
	return total;

   }
    
    
    
    /*public void playerSunkCheck() {
    	
        char hit = 'X';
        System.out.println("Schiffe versinkt soweit :  ");
        for (int i=0; i<10;i++) {
          if(this.villainBoard.fields[0][i] == hit && this.villainBoard.fields[0][i-1] == hit ) {   
               System.out.println("Piratenschiff am column A  versenkt!");
                } 
          
          else if(this.villainBoard.fields[2][i] == hit && this.villainBoard.fields[2][i-1] == hit && this.villainBoard.fields[2][i-2] == hit ) {   
               System.out.println("Piratenschiff am column C  versenkt!");
                }
            
          else if(this.villainBoard.fields[4][i] == hit && this.villainBoard.fields[4][i-1] == hit && this.villainBoard.fields[4][i-2] == hit ) {   
               System.out.println("Piratenschiff am column E  versenkt!");
                }
           
          else if(this.villainBoard.fields[6][i] == hit && this.villainBoard.fields[6][i-1] == hit && this.villainBoard.fields[6][i-2] == hit && this.villainBoard.fields[8][i-3] == hit) {   
               System.out.println("Piratenschiff am column G  versenkt!");
                }
           
          else if(this.villainBoard.fields[8][i] == hit && this.villainBoard.fields[8][i-1] == hit && this.villainBoard.fields[8][i-2] == hit && this.villainBoard.fields[8][i-3] == hit && this.villainBoard.fields[9][i-4] == hit) {   
               System.out.println("Piratenschiff am column I  versenkt!");
                }
           
          else if(this.villainBoard.fields[9][i] == hit && this.villainBoard.fields[9][i-1] == hit && this.villainBoard.fields[9][i-2] == hit && this.villainBoard.fields[9][i-3] == hit && this.villainBoard.fields[9][i-4] == hit ) {   
               System.out.println("Piratenschiff am column J  versenkt!");
                }else { System.out.println(" ");}
        }
    }
    
    
public void villainSunkCheck() {
    	
        char hit = 'X';
        System.out.println("Schiffe versinkt soweit :  ");
        for (int i=0; i<10;i++) {
          if(this.playerBoard.fields[0][i] == hit && this.playerBoard.fields[0][i-1] == hit ) {   
               System.out.println("der villain hat Piratenschiff am column A  versenkt!");
                }
          
            if(this.playerBoard.fields[2][i] == hit && this.playerBoard.fields[2][i-1] == hit && this.playerBoard.fields[2][i-2] == hit ) {   
               System.out.println("der villain hat Piratenschiff am column C  versenkt!");
                }
            
           if(this.playerBoard.fields[4][i] == hit && this.playerBoard.fields[4][i-1] == hit && this.playerBoard.fields[4][i-2] == hit ) {   
               System.out.println("der villain hat Piratenschiff am column E  versenkt!");
                }
           
           if(this.playerBoard.fields[6][i] == hit && this.playerBoard.fields[6][i-1] == hit && this.playerBoard.fields[6][i-2] == hit && this.playerBoard.fields[8][i-3] == hit) {   
               System.out.println("der villain hat Piratenschiff am column G  versenkt!");
                }
           
           if(this.playerBoard.fields[8][i] == hit && this.playerBoard.fields[8][i-1] == hit && this.playerBoard.fields[8][i-2] == hit && this.playerBoard.fields[8][i-3] == hit && this.playerBoard.fields[9][i-4] == hit) {   
               System.out.println("der villain hat Piratenschiff am column I  versenkt!");
                }
           
            if(this.playerBoard.fields[9][i] == hit && this.playerBoard.fields[9][i-1] == hit && this.playerBoard.fields[9][i-2] == hit && this.playerBoard.fields[9][i-3] == hit && this.playerBoard.fields[9][i-4] == hit ) {   
               System.out.println("der villain hat Piratenschiff am column J  versenkt!");
                }    
        }
    }
    
    */
    

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

/**
 * check if the ships are sinked 
 * @return true if the player or the computer lost all the ship , false if not  
 */
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
     * Converts array indexes to alphanumeric board coordinates, e.g. [0,0] to A1
     */
    public static String convertCoordinatesToString(int[] input) {
        char x = (char) (input[0] + 65);
        String y = Integer.toString(input[1]);
        return x + y;
    }
}