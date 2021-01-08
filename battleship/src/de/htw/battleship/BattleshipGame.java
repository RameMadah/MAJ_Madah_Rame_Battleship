package de.htw.battleship;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;


/**
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
    private final boolean hideVillainShips = false;//changed by me

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
        System.out.println("Spiel gestartet. Drücke ENTER während der Zieleingabe, um zum Hauptmenü zurückzukehren.");

        while (running) {
            playersTurn();
            if (running) villainsTurn();
        }
    }

    private void playersTurn() {

        System.out.println("Spieler ist am Zug.");
        villainBoard.print(hideVillainShips);
        
        String myShot;
        // TODO (s. Aufgabe 5)
      //String hit= Arrays.toString(playershot);
      //convertCoordinatesToInt(Arrays.toString(playerShot))
  
        Scanner input = new Scanner(System.in);
        System.out.println("Zieleingabe :");       
       
        
        
       try {  myShot = input.next();
       int x = myShot.toUpperCase().charAt(0)- 65 ;
       int y = Integer.parseInt(myShot.substring(1)) ;
       int[] playerShot = new int[]{x, y};
       
    	   System.out.println("Sie haben auf " + convertCoordinatesToString(playerShot) +" gezielt.");    
           System.out.println("heads down pirates we are Shooting.");
           }
        
        catch (InputMismatchException a ) {
     	   System.out.println("wählen Sie eine Möglichkeit aus der Optionenliste aus");   	   
          }
      
       
        
        
        // player wants to exit game
       /*Scanner exit = new Scanner(System.in);
         myShot = exit.next();
         myShot == null || myShot.isEmpty()*/
        if (exit()) {
        System.out.println("Spiel ist pausiert ");            
        running = true;
        BattleshipApplication battleshipApplication = new BattleshipApplication();
        battleshipApplication.mainMenu();
        }
        pause();
    }

    
    public  boolean exit() {
    String Enter= new Scanner(System.in).nextLine() ;
    BattleshipApplication battleshipApplication = new BattleshipApplication();
    battleshipApplication.mainMenu();	
    	pause();
    	return true;
    	}
    
    
    private void villainsTurn() {

        System.out.println("Gegner ist am Zug.");
        playerBoard.print(false);
        int[] villainShot = getVillainShot();

        // TODO (s. Aufgabe 6)

        pause();
    }

    /**
     * Asks the user to press ENTER to continue.
     * Can be called anywhere in the game to avoid too much output at once.
     */
    private void pause() {
        System.out.println();
        System.out.println("Drücke ENTER um fortzufahren...");
        System.out.println();
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
