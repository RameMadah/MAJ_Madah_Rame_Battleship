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
    //Attribute
    private BattleshipGame game;
    private final Path saveFilePath = Path.of("battleship.save");

    public static void main(String[] args) {
        BattleshipApplication battleshipApplication = new BattleshipApplication();
        battleshipApplication.mainMenu();
    }
    
    // Methode
    /**
     * Zeigt den Menu auf die Konsole an,und gibt die Möglichkeit
     * eine Option auszuwählen.
     */
    private void mainMenu() {
            
        // TODO print main menu to the console. let user select an option. (s. Aufgabe 3)
    	boolean exit = false;
    	
        while( exit == false) {
    	int auswahl = 0;
                          
    	// Menu header
         System.out.println("***********************");
         System.out.println("***** BATTLESHIP ******");
         System.out.println("***********************");
         System.out.println(" ");
         
         // wird immer im Menu gezeigt
         System.out.println("(1) Neues Spiel starten");
         
         
         // wird nur gezeigt, wenn das Spiel gespeichert wird.
         if(hasSavedGame() == true) {
         System.out.println("(2) Spiel laden");
         }
        
         //wird nur gezeigt, wenn das Spiel läuft.
         if(hasRunningGame() == true ) {
         System.out.println("(3) Spiel fortsetzen");
         }
         
         //wird nur gezeigt, wenn das Spiel läuft.
         if(hasRunningGame() == true ) 
         {
         System.out.println("(4) Spiel speichern");
         }
         
         //wird immer im Menu gezeigt.
         System.out.println("(5) Beenden");
         System.out.println(" ");
         System.out.println("=======================");
         System.out.println("Auswahl eingeben : ");
              

        //Scanner um Werte einzugeben
       
	Scanner scanner = new Scanner(System.in);
        
       // Exeption überwachen um andere Werte zu behandlen.
       try {
    	
        auswahl = scanner.nextInt();
        System.out.println(auswahl+" ist gewählt.");
        System.out.println("=======================");
        System.out.println(" ");
        
         }
       catch (InputMismatchException a ) {
    	   System.out.println("Ops!");   	   
         }
       
      
    // switch statement 
       switch (auswahl) {
       
       case 1: //Falls input (1) das Spiel wird gestartet
       startNewGame();
       break;

       case 2: //Falls input (2) stellt wieder das Spiel aus dem File ""battleship.save" her 
       loadGame();
       break;

       case 3: //Falls input (3) bringt Das Spiel zum laufen wieder
       continueGame();
       break;
       
       case 4:       
       saveGame();//Falls input (4) Speichert das Spiel im File "battleship.save" her
       break;
           
       case 5://Falls input (5) Spiel wird ausgeschaltet
       System.out.println("Spiel ist ausgeschalten");
       exit = true;
       break;

       default://Falls was anderes, dann wird dieses Text auf die Konsole angezeigt.
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
     * Speichert das Spiel im File "battleship.save" her
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
     * 
     * überprüft ob das File "battleship.save" existiert.
     * @return true, wenn das File "battleship.save" existiert, false sonst.
     */
    private boolean hasSavedGame() {
        return saveFilePath.toFile().exists();
    }

    /**
     * checks if game is runing
     * 
     * überprüft ob das Spiel läuft.
     * @return true, wenn das Spiel läuft, false sonst.
     * 
     */
    private boolean hasRunningGame() {
        return !(game == null || game.isFinished());
    }

    /**
     * let the game run
     * 
     * Bringt Das Spiel zum laufen wieder
     */
    private void continueGame() {
        this.game.run();
    }

     /**
      * starts the game
      * 
      * Startet das Spiel
      */
    private void startNewGame() {
        this.game = new BattleshipGame();
        continueGame();
    }

}