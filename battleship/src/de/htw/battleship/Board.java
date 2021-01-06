package de.htw.battleship;

import java.util.List;
import java.util.Random;

/**
 * Holds the state of one players board
 * as well as the methods to generate a board and process shots.
 */
public class Board {

    public static final char EMPTY = '.';
    public static final char SHIP = 'O';
    public static final char HIT = 'X';
    public static final char MISSED_SHOT = '-';

    public static final int BOARD_SIZE = 10;

    private final char[][] fields = new char[BOARD_SIZE][BOARD_SIZE];

    /**
     * Create a new Board and generate ships
     */
    public Board() {
    	
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                fields[i][j] = EMPTY;
                	
                
                
                }
            }
        generate();
        }
           
     // TODO generate ships (s. Aufgabe 4)
        public char generate() {
                         	
            		int first = (int) (Math.random()*(9 - 5)) + 0;
            		int second = (int) (Math.random()*4)+5;
            		int third = (int) (Math.random()*6);
            		int fourth = (int) (Math.random()*7);
            		int fifth = (int) (Math.random()*6);
            		int sixth = (int) (Math.random()*5);
            		int ran = (int)(Math.random()*2)+8;
            		fields[0][first]  =SHIP;
                	fields[0][first+1]=SHIP;
            		
                	fields[0][second]  = SHIP;
                	fields[0][second+1]= SHIP;
            	
            	fields[2][third]  = SHIP;
            	fields[2][third+1]= SHIP;
            	fields[2][third+2]= SHIP;
            	
            	fields[4][fourth]  = SHIP;
            	fields[4][fourth+1]= SHIP;
            	fields[4][fourth+2]= SHIP;
            	
            	fields[6][fifth] = SHIP;
            	fields[6][fifth+1] = SHIP;
            	fields[6][fifth+2] = SHIP;
            	fields[6][fifth+3] = SHIP;
            	
            	fields[ran][sixth] = SHIP;
            	fields[ran][sixth+1] = SHIP;
            	fields[ran][sixth+2] = SHIP;
            	fields[ran][sixth+3] = SHIP;
            	fields[ran][sixth+4] = SHIP;
            	
            	
            	
            	return SHIP;
              }

    /**
     * Create a Board from an exported string.
     */
    public Board(String savedBoard) {
        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                int index = y * BOARD_SIZE + x;
                fields[x][y] = savedBoard.charAt(index);
            }
        }
    }

    /**
     * Prints the board to System.out
     *
     * @param hideShips if TRUE, replaces ships by empty fields in output
     */
    public void print(boolean hideShips) {
        /* print column headers A - J */
        System.out.print("# ");
        for (int x = 0; x < fields[0].length; x++) {
            char column = (char) (x + 65);
            System.out.print(" " + column);
        }
        System.out.println();

        for (int y = 0; y < fields.length; y++) {
            /* print row number */
            int rowNumber = y + 1;
            System.out.print(rowNumber + " ");
            if (rowNumber < 10) System.out.print(" ");

            /* print row */
            for (int x = 0; x < fields[y].length; x++) {
                char output = fields[x][y];
                if (output == SHIP && hideShips)
                    output = EMPTY;
                System.out.print(output + " ");
            }
            System.out.println();
        }
    }

    public String exportAsString() {
        StringBuilder builder = new StringBuilder();
        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                builder.append(fields[x][y]);
            }
        }
        builder.append("\n");
        return builder.toString();
    }

    /**
     * @return FALSE if at least one ship is remaining. TRUE otherwise.
     */
    public boolean isWholeFleetSunk() {
        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                if (fields[x][y] == SHIP)
                    return false;
            }
        }
        return true;
    }

    public char getField(int x, int y) {
        return fields[x][y];
    }
}
