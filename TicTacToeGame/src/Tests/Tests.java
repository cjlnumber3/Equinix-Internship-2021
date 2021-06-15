package Tests;

import com.MainPackage.Main;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.util.Scanner;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Tests {

    @Test
    void runGame() {
    }

    @Test
    void playerMove() {
    }

    @Test
    void validMovement() {
        int numberOfGameBoardRows = 3;
        int numberOfGameBoardColumns = 3;
        int emptyCell = 3;

        for (int row = 0; row < numberOfGameBoardRows; ++row) {
            for (int column = 0; column < numberOfGameBoardColumns; ++column) {
                if (Main.getCellStatus(row, column) != emptyCell) {
                    assertTrue(Main.getCellStatus(row, column) != emptyCell, "This cell needs to be empty to play here!");
                }
            }
        }


    }

    @Test
    void getCellStatus() {

    }


    @Test
    void printGameBoard() {
        PrintStream original = System.out;
        String emptyVisualBoard = "   |   |   -----------   |   |   -----------   |   |   ";

        ByteArrayOutputStream newOutputForVoidBoard = new ByteArrayOutputStream();
        PrintStream printStreamForBoard = new PrintStream(newOutputForVoidBoard);
        Main.printPlayerDirections(1);
        System.setOut(original);
        printStreamForBoard.flush();
        assertEquals(emptyVisualBoard, newOutputForVoidBoard.toString());
        assertThat(newOutputForVoidBoard.toString(), CoreMatchers.containsString(emptyVisualBoard));

    }

    @Test
    public void clearGameBoard() {
        int numberOfGameBoardRows = 3;
        int numberOfGameBoardColumns = 3;
        int emptyCell = 3;

        for (int row = 0; row < numberOfGameBoardRows; ++row) {
            for (int column = 0; column < numberOfGameBoardColumns; ++column) {
                assertEquals(emptyCell, Main.gameBoard[row][column], "Hey! This isn't an empty board!");
            }
        }
    }

    @Test
    public void printCell() {
        PrintStream original = System.out;

        ByteArrayOutputStream newOutputForVoidXCell = new ByteArrayOutputStream();
        PrintStream printStreamForXCell = new PrintStream(newOutputForVoidXCell);
        Main.printPlayerDirections(1);
        System.setOut(original);
        printStreamForXCell.flush();
        assertEquals(" X ", newOutputForVoidXCell.toString());

        ByteArrayOutputStream newOutputForVoidOCell = new ByteArrayOutputStream();
        PrintStream printStreamForOCell = new PrintStream(newOutputForVoidOCell);
        Main.printPlayerDirections(1);
        System.setOut(original);
        printStreamForOCell.flush();
        assertEquals(" O ", newOutputForVoidXCell.toString());

        ByteArrayOutputStream newOutputForVoidEmptyCell = new ByteArrayOutputStream();
        PrintStream printStreamForEmptyCell = new PrintStream(newOutputForVoidEmptyCell);
        Main.printPlayerDirections(1);
        System.setOut(original);
        printStreamForEmptyCell.flush();
        assertEquals("   ", newOutputForVoidEmptyCell.toString());
    }

    @Test
    public void winConditions() {
        int[][] mockGameBoard = new int[3][3];

        int numberOfGameBoardRows = 3;
        int numberOfGameBoardColumns = 3;
        int emptyCell = 3;

        //pulls gameBoard and copies to mockGameBoard

        for (int row = 0; row < numberOfGameBoardRows; ++row) {
            for (int column = 0; column < numberOfGameBoardColumns; ++column) {
                mockGameBoard[row][column] = Main.gameBoard[row][column];
            }
        }

        //return false if ANY CELL in the board is filled with emptyCell

        for (int row = 0; row < numberOfGameBoardRows; ++row) {
            for (int column = 0; column < numberOfGameBoardColumns; ++column) {
                if (mockGameBoard[row][column] == emptyCell) {
                    assertFalse(Main.winConditions(), "Error! This isn't a win!");
                }
            }
        }

        //diagonal win

        for (int row = 0; row < numberOfGameBoardRows; ++row) {
            for (int column = 0; column < numberOfGameBoardColumns; ++column) {
                if (mockGameBoard[row][column] == mockGameBoard[column + 1][row + 1] &&
                        mockGameBoard[row][column] == mockGameBoard[column + 2][row + 2]) {
                    assertTrue(Main.winConditions(), "Error! This has to be a win!");
                }
            }
        }

        //vertical win

        for (int column = 0; column < numberOfGameBoardColumns; ++column) {
            if (mockGameBoard[0][column] == mockGameBoard[1][column] && mockGameBoard[0][column] == mockGameBoard[2][column]) {
                assertTrue(Main.winConditions(), "Error! This has to be a win!");
            }
        }

        //horizontal win

        for (int row = 0; row < numberOfGameBoardRows; ++row) {
            if (mockGameBoard[0][row] == mockGameBoard[1][row] && mockGameBoard[0][row] == mockGameBoard[2][row]) {
                assertTrue(Main.winConditions(), "Error! This has to be a win!");
            }
        }

    }


    @BeforeClass
    public static void printWelcomeBanner() throws Exception {
        System.out.println("Welcome to a game of Tic-Tac-Toe! (Player 1 is X, and Player 2 is O)");
    }

    @Before
    public void printWinningBanner() throws Exception {
        assertFalse(Main.winConditions());
    }

    @Test
    public void initializeGame() {
        Scanner scanner = new Scanner(System.in);
        assertTrue(scanner.hasNextLine(), "Error! You must press 'Enter' to initialize the game!");
    }


    @Test
    public void printPlayerDirections() {
        PrintStream original = System.out;

        ByteArrayOutputStream newOutputForVoidMethodPlayer1 = new ByteArrayOutputStream();
        PrintStream printStreamForPlayer1Directions = new PrintStream(newOutputForVoidMethodPlayer1);
        Main.printPlayerDirections(1);
        System.setOut(original);
        printStreamForPlayer1Directions.flush();
        assertEquals("Player 1! Please enter the coordinate of your move (row[1-3] column[1-3]): ", newOutputForVoidMethodPlayer1.toString());

        ByteArrayOutputStream newOutputForVoidMethodPlayer2 = new ByteArrayOutputStream();
        PrintStream printStreamForPlayer2Directions = new PrintStream(newOutputForVoidMethodPlayer2);
        Main.printPlayerDirections(2);
        System.setOut(original);
        printStreamForPlayer2Directions.flush();
        assertEquals("Player 2! Please enter the coordinate of your move (row[1-3] column[1-3]): ", newOutputForVoidMethodPlayer2.toString());
    }
}