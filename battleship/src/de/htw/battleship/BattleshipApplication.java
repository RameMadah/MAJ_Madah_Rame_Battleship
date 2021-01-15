package de.htw.battleship;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Requires Java 11 or higher.
 */
public class BattleshipApplication {
   
    private BattleshipGame game;
    private final Path saveFilePath = Path.of("battleship.save"); //save path
    private static BattleshipApplication instance=null; //We only want one instance of the application so that the game continues from last round.
    
  
    
    
    
    public static void main(String[] args) {
    	 BattleshipApplication.GetInstance().mainMenu(); //Example of using the function GetInstance.
    }
    
  //*****************************************************************************************
    
    
    
    
    
    /**
     * it shows the main menu on the console and let the user select
     * between many options
     */
    public void mainMenu() {
            
        // TODO print main menu to the console. let user select an option. (s. Aufgabe 3)
    	boolean running = true;
    	
        while( running == true) {
    	int auswahl = 0;
    	
                          
    	// Menu header
         System.out.println("***********************");
         System.out.println("***** BATTLESHIP ******");
         System.out.println("***********************");
         System.out.println(" ");
         
         // will always appear on the Menu 
         System.out.println("(1) Neues Spiel starten");
         
         
         // will only appear if the game has a saved data.
         if(hasSavedGame() == true) {
         System.out.println("(2) Spiel laden");
         }
        
         //will only appear if the game in running and the user paused the game
         if(hasRunningGame() == true ) {
         System.out.println("(3) Spiel fortsetzen");
         }
         
         //will only appear if the game in running and the user paused the game
         if(hasRunningGame() == true ) 
         {
         System.out.println("(4) Spiel speichern");
         }
         
         //will be always shown on the console
         System.out.println("(5) Beenden");
         System.out.println(" ");
         System.out.println("=======================");
         System.out.println("Auswahl eingeben : ");
              

        //Scanner to read values (input)
       
	Scanner scanner = new Scanner(System.in);
        
       // watching out for exeptions and handling with it
       try {
    	
        auswahl = scanner.nextInt();
        System.out.println(" "+ auswahl +" ist gewählt.");       
        System.out.println("=======================");
        System.out.println(" ");
        
         }
       catch (InputMismatchException a ) {
    	   System.out.println("Ops!");   	   
         }
       
      
    // switch statement 
       switch (auswahl) {
       
       case 1: //in case input (1) the game will start    	  
    	   
       startNewGame();
       break;

       case 2: // in case input (2) the game will be loaded from""battleship.save" 
       loadGame();
       break;

       case 3: //in case input (3) user can continue the game
       continueGame();
       break;
       
       case 4://in case input (4) the game will be saved in File "battleship.save"
       saveGame();
       break;
           
       case 5://in case input (5) the game will stop
       System.out.println("Spiel ist ausgeschalten");
      
       break;

       default://in case something else then this text will be shown
       System.out.println("Ungueltige Eingabe");
       System.out.println("Bitte, wählen Sie eine Möglichkeit aus der Optionenliste aus");
       }
      
       System.out.println("=======================");
       }
        //test for a commit


    }

    /**
     * Restores a game from the file "battleship.save"
     * 
     * Stellt wieder das Spiel aus dem File ""battleship.save"
     */
    private void loadGame() {
        if (!hasSavedGame()) {
            System.out.println("Kein gespeicherter Spielstand vorhanden.");
            return;
        }

        try {
            String saveGame = Files.readString(saveFilePath, StandardCharsets.UTF_8);
            String[] boards = saveGame.split("\n");
            Board playerBoard = new Board(boards[0]);
            Board villainBoard = new Board(boards[1]);
            this.game = new BattleshipGame(playerBoard, villainBoard);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Loading failed");
        }
    }

    /**
     * Saves a game into the file "battleship.save"
     * 
     */
    private void saveGame() {
        File file = saveFilePath.toFile();

        if (file.exists()) file.delete();
        try {
            file.createNewFile();

            String playerBoard = game.playerBoard.exportAsString();
            String villainBoard = game.villainBoard.exportAsString();
            Files.writeString(file.toPath(), playerBoard + villainBoard, StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Save failed");
        }
    }

    /**
     * Checks if file "battleship.save" exists
     * @return  true, if the File "battleship.save" exist, false if not.
     */
    private boolean hasSavedGame() {
        return saveFilePath.toFile().exists();
    }

    /**
     * checks if game is running
     * 
     * @return true, if the game is running, and false if not.
     * 
     */
    private boolean hasRunningGame() {
        if(game!=null){
            return !game.isFinished();
        }
        return false;
    }
    /**
     * let the game run
     */
    private void continueGame() {
        this.game.run();
    }

     /**
      * starts the game
      */
    private void startNewGame() {
    	 
        this.game = new BattleshipGame();
        continueGame();
    }
    
    /**
     * getter for the instance of the running game.
     * @return instance , the main instance of the game
     */
    public static BattleshipApplication GetInstance(){ 
        if(instance==null){
            instance=new BattleshipApplication();//If there is no instance of the game, we make a new one.
        }
        return instance;
    }

}