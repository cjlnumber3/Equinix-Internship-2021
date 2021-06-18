package Tests;

import com.MainPackage.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.containsString;

class MainTest {

    private TestAccessory mockPlayer;

    @BeforeEach
    void setUp() {
        mockPlayer = new TestAccessory();
    }

    @Test
    void validateInput() {
        String playerCoordinates = "1 1";
        boolean isValid = mockPlayer.validatePlayerCoordinates(playerCoordinates);

        assertTrue(isValid);
    }

    @Test
    void playerMove() {
        int row = 1; //0-2 as per array
        int column = 1; //0-2 as per array
        int player = 1; //0, 1 or 3 as per game instructions

        PrintStream original = System.out;
        ByteArrayOutputStream byteOutputOfPrintPlayerDirections = new ByteArrayOutputStream();
        PrintStream byteOutputOfPrintPlayerDirectionsToPrintStream = new PrintStream(byteOutputOfPrintPlayerDirections);
        System.setOut(byteOutputOfPrintPlayerDirectionsToPrintStream);

        Main.setCellStatus(row, column, player);
        boolean isValid = Main.getValidMovementReturnsTrueIfFree(row, column, player);
        System.setOut(original);
        byteOutputOfPrintPlayerDirectionsToPrintStream.flush();

        assertFalse(isValid);
    }

    @Test
    void validMovementWhenTaken() {
        int row = 1; //0-2 as per array
        int column = 1; //0-2 as per array
        int player = 1; //0, 1 or 3 as per game instructions
        String cellStatusTaken = "This move at (" + (row + 1) + "," + (column + 1) + ") is taken.\n";

        PrintStream original = System.out;
        ByteArrayOutputStream byteOutputOfValidMovement = new ByteArrayOutputStream();
        PrintStream byteOutputOfValidMovementToPrintStream = new PrintStream(byteOutputOfValidMovement);
        System.setOut(byteOutputOfValidMovementToPrintStream);

        Main.setCellStatus(row, column, player);
        Main.validMovement(row, column, player);

        System.setOut(original);
        String notAValidMovementError = byteOutputOfValidMovement.toString();
        byteOutputOfValidMovementToPrintStream.flush();

        assertEquals(notAValidMovementError, cellStatusTaken);
    }

    @Test
    void validMovementWhenFree() {
        int player = 1; //0, 1 or 3 as per game instructions
        int column = 1; //0-2 as per array
        int row = 1; //0-2 as per array

        Main.setCellStatus(row, column, player);
        Main.validMovement(row, column, player);

        assertEquals(Main.gameBoard[row][column], player);
    }

    @Test
    void printGameBoard() {
        String emptyGameBoard = "   |   |   \n-----------\n   |   |   \n-----------\n   |   |   ";

        PrintStream original = System.out;
        ByteArrayOutputStream byteOutputOfPrintGameBoard = new ByteArrayOutputStream();
        PrintStream byteOutputOfPrintGameBoardToPrintStream = new PrintStream(byteOutputOfPrintGameBoard);
        System.setOut(byteOutputOfPrintGameBoardToPrintStream);

        Main.clearGameBoard();
        Main.printGameBoard();
        System.setOut(original);

        String actualPrintedGameBoard = byteOutputOfPrintGameBoard.toString();
        byteOutputOfPrintGameBoardToPrintStream.flush();

        assertThat(actualPrintedGameBoard, containsString(emptyGameBoard));
    }

    @Test
    void clearGameBoard() {
        int row = 1; //0-2 as per array
        int column = 0; //0-2 as per array
        int emptyCell = 3; //0-2 as per array

        Main.clearGameBoard();
        int clearedCell = Main.getCellStatus(row,  column); //range of 0-2 in array

        assertEquals(emptyCell, clearedCell);
    }

    @Test
    void getCellStatus() {
        int row = 2; //0-2 as per array
        int column = 2; //0-2 as per array
        int player = 0; //0, 1, or 3 as per game

        Main.setCellStatus(row, column, player);
        int cellStatus = Main.getCellStatus(row, column);

        assertEquals(cellStatus, player); //is a free space
    }

    @Test
    void printCell() {
        int row = 1; //0-2 as per array
        int column = 2; //0-2 as per array
        int player = 3; //0, 1 or 3 as per game instructions

        Main.setCellStatus(row, column, player);
        int cellStatus = Main.getCellStatus(row, column);
        String printedCell = mockPlayer.validatePrintedCell(row, column);
        boolean isValid = false;

        if (cellStatus == 0 && printedCell.equals(" O ")) {
            isValid = true;
        }
        else if (cellStatus == 1 && printedCell.equals(" X ")) {
            isValid = true;
        }
        else if (cellStatus == 3 && printedCell.equals("   ")) {
            isValid = true;
        }

        assertTrue(isValid);
    }


    @Test
    public void printWelcomeBanner() {
        String welcomeBanner = "Welcome to a game of Tic-Tac-Toe! (Player 1 is X, and Player 2 is O)\n";

        PrintStream original = System.out;
        ByteArrayOutputStream byteOutputOfPrintWelcomeBanner = new ByteArrayOutputStream();
        PrintStream byteOutputOfPrintWelcomeBannerToPrintStream = new PrintStream(byteOutputOfPrintWelcomeBanner);
        System.setOut(byteOutputOfPrintWelcomeBannerToPrintStream);

        Main.printWelcomeBanner();
        System.setOut(original);
        byteOutputOfPrintWelcomeBannerToPrintStream.flush();

        assertEquals(welcomeBanner, byteOutputOfPrintWelcomeBanner.toString());
    }

    @Test
    public void printWinningBanner() {
        String winnerBannerPlayer1 = "Player 1 (X) has won! Congrats!\n";
        String winnerBannerPlayer2 = "Player 2 (O) has won! Congrats!\n";
        String drawBanner = "It's a Draw!\n";
        int winner = 3; //0, 1 or 3 as per game instructions

        PrintStream original = System.out;
        ByteArrayOutputStream byteOutputOfPrintWinningBanner = new ByteArrayOutputStream();
        PrintStream byteOutputOfPrintWinningBannerToPrintStream = new PrintStream(byteOutputOfPrintWinningBanner);
        System.setOut(byteOutputOfPrintWinningBannerToPrintStream);

        Main.setWinningPlayer(winner);
        Main.printWinningBanner();
        System.setOut(original);
        byteOutputOfPrintWinningBannerToPrintStream.flush();

        assertEquals(drawBanner, byteOutputOfPrintWinningBanner.toString());
    }

    @Test
    void printPlayerDirections() {
        String player1Directions = "Player 1! Please enter the coordinate of your move (row[1-3] column[1-3]): \n";
        String player2Directions = "Player 2! Please enter the coordinate of your move (row[1-3] column[1-3]): \n";
        int player = 1; //0, 1 or 3 as per game instructions

        PrintStream original = System.out;
        ByteArrayOutputStream byteOutputOfPrintPlayerDirections = new ByteArrayOutputStream();
        PrintStream byteOutputOfPrintPlayerDirectionsToPrintStream = new PrintStream(byteOutputOfPrintPlayerDirections);
        System.setOut(byteOutputOfPrintPlayerDirectionsToPrintStream);

        Main.printPlayerDirections(player);
        System.setOut(original);
        byteOutputOfPrintPlayerDirectionsToPrintStream.flush();

        assertEquals(player1Directions, byteOutputOfPrintPlayerDirections.toString());
    }

    @Test
    public void winConditionsDiagonalLeft() {
        int winningPlayer = 1; // 0, 1 or 3 as per game

        Main.gameBoard[0][0] = winningPlayer;
        Main.gameBoard[1][1] = winningPlayer;
        Main.gameBoard[2][2] = winningPlayer;

        assertEquals(Main.winningPlayer, winningPlayer);
    }

    @Test
    public void winConditionsDiagonalRight() {
        int winningPlayer = 1; // 0, 1 or 3 as per game

        Main.gameBoard[2][0] = winningPlayer;
        Main.gameBoard[1][1] = winningPlayer;
        Main.gameBoard[0][2] = winningPlayer;

        assertEquals(Main.winningPlayer, winningPlayer);
    }

    @Test
    public void winConditionsHorizontalRow1() {
        int winningPlayer = 1; // 0, 1 or 3 as per game

        Main.gameBoard[0][0] = winningPlayer;
        Main.gameBoard[0][1] = winningPlayer;
        Main.gameBoard[0][2] = winningPlayer;

        assertEquals(Main.winningPlayer, winningPlayer);
    }

    @Test
    public void winConditionsHorizontalRow2() {
        int winningPlayer = 1; // 0, 1 or 3 as per game

        Main.gameBoard[1][0] = winningPlayer;
        Main.gameBoard[1][1] = winningPlayer;
        Main.gameBoard[1][2] = winningPlayer;

        assertEquals(Main.winningPlayer, winningPlayer);
    }

    @Test
    public void winConditionsHorizontalRow3() {
        int winningPlayer = 1; // 0, 1 or 3 as per game

        Main.gameBoard[2][0] = winningPlayer;
        Main.gameBoard[2][1] = winningPlayer;
        Main.gameBoard[2][2] = winningPlayer;

        assertEquals(Main.winningPlayer, winningPlayer);
    }

    @Test
    public void winConditionsVerticalColumn1() {
        int winningPlayer = 1; // 0, 1 or 3 as per game

        Main.gameBoard[0][0] = winningPlayer;
        Main.gameBoard[1][0] = winningPlayer;
        Main.gameBoard[2][0] = winningPlayer;

        assertEquals(Main.winningPlayer, winningPlayer);
    }

    @Test
    public void winConditionsVerticalColumn2() {
        int winningPlayer = 1; // 0, 1 or 3 as per game

        Main.gameBoard[0][1] = winningPlayer;
        Main.gameBoard[1][1] = winningPlayer;
        Main.gameBoard[2][1] = winningPlayer;

        assertEquals(Main.winningPlayer, winningPlayer);
    }

    @Test
    public void winConditionsVerticalColumn3() {
        int winningPlayer = 1; // 0, 1 or 3 as per game

        Main.gameBoard[0][2] = winningPlayer;
        Main.gameBoard[1][2] = winningPlayer;
        Main.gameBoard[2][2] = winningPlayer;

        assertEquals(Main.winningPlayer, winningPlayer);
    }

}