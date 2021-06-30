package Tests;

import com.MainPackage.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.containsString;

class MainTest {

    private TestAccessory mockPlayer;
    Main program = new Main();

    @BeforeEach
    void setUp() {
        mockPlayer = new TestAccessory();
    }

    @Test
    void testPlayerMove() {
        int row = 1;
        int column = 1;
        int player = 1;

        PrintStream original = System.out;
        ByteArrayOutputStream byteOutputOfPrintPlayerDirections = new ByteArrayOutputStream();
        PrintStream byteOutputOfPrintPlayerDirectionsToPrintStream = new PrintStream(byteOutputOfPrintPlayerDirections);
        System.setOut(byteOutputOfPrintPlayerDirectionsToPrintStream);

        program.setCellStatus(row, column, player);
        boolean isValid = program.getValidMovementReturnsTrueIfFree(row, column, player);
        System.setOut(original);
        byteOutputOfPrintPlayerDirectionsToPrintStream.flush();

        assertFalse(isValid);
    }

    @Test
    void testValidMovementWhenTaken() {
        int row = 1;
        int column = 1;
        int player = 1;
        String cellStatusTaken = "This move at (" + (row + 1) + "," + (column + 1) + ") is taken.\n";

        PrintStream original = System.out;
        ByteArrayOutputStream byteOutputOfValidMovement = new ByteArrayOutputStream();
        PrintStream byteOutputOfValidMovementToPrintStream = new PrintStream(byteOutputOfValidMovement);
        System.setOut(byteOutputOfValidMovementToPrintStream);

        program.setCellStatus(row, column, player);
        program.validMovement(row, column, player);

        System.setOut(original);
        String notAValidMovementError = byteOutputOfValidMovement.toString();
        byteOutputOfValidMovementToPrintStream.flush();

        assertEquals(notAValidMovementError, cellStatusTaken);
    }

    @Test
    void testValidMovementWhenFree() {
        int player = 1;
        int column = 1;
        int row = 1;

        program.setCellStatus(row, column, player);
        program.validMovement(row, column, player);

        assertEquals(program.gameBoard[row][column], player);
    }

    @Test
    void testPrintGameBoard() {
        PrintStream emptyGameBoard = System.out;
        ByteArrayOutputStream byteOutputOfEmptyGameBoard = new ByteArrayOutputStream();
        PrintStream byteOutputOfEmptyGameBoardToPrintStream = new PrintStream(byteOutputOfEmptyGameBoard);
        System.setOut(byteOutputOfEmptyGameBoardToPrintStream);

        program.clearGameBoard();
        program.printGameBoard();
        System.setOut(emptyGameBoard);

        String emptyPrintedGameBoard = byteOutputOfEmptyGameBoard.toString();
        byteOutputOfEmptyGameBoardToPrintStream.flush();


        PrintStream original = System.out;
        ByteArrayOutputStream byteOutputOfPrintGameBoard = new ByteArrayOutputStream();
        PrintStream byteOutputOfPrintGameBoardToPrintStream = new PrintStream(byteOutputOfPrintGameBoard);
        System.setOut(byteOutputOfPrintGameBoardToPrintStream);

        program.clearGameBoard();
        program.printGameBoard();
        System.setOut(original);

        String actualPrintedGameBoard = byteOutputOfPrintGameBoard.toString();
        byteOutputOfPrintGameBoardToPrintStream.flush();

        assertThat(actualPrintedGameBoard, containsString(emptyPrintedGameBoard));
    }

    @Test
    void tesClearGameBoard() {
        int row = 1;
        int column = 0;
        int emptyCell = 0;

        program.clearGameBoard();
        int clearedCell = program.getCellStatus(row, column);

        assertEquals(emptyCell, clearedCell);
    }

    @Test
    void testGetCellStatus() {
        int row = 2;
        int column = 2;
        int player = 0;

        program.setCellStatus(row, column, player);
        int cellStatus = program.getCellStatus(row, column);

        assertEquals(cellStatus, player); //is a free space
    }

    @Test
    void testPrintCell() {
        int row = 1;
        int column = 1;
        int player = 3;

        program.setCellStatus(row, column, player);
        int cellStatus = program.getCellStatus(row, column);
        String printedCell = mockPlayer.validatePrintedCell(row, column);
        boolean isValid = false;

        if (cellStatus == 0 && printedCell.equals(" O ")) {
            isValid = true;
        } else if (cellStatus == 1 && printedCell.equals(" X ")) {
            isValid = true;
        } else if (cellStatus == 3 && printedCell.equals("   ")) {
            isValid = true;
        }

        assertTrue(isValid);
    }

    @Test
    public void testPrintWelcomeBanner() {
        String welcomeBanner = "Welcome to a game of Tic-Tac-Toe! (Player 1 is X, Player 2 is O, and Player 3 is Y)\n";

        PrintStream original = System.out;
        ByteArrayOutputStream byteOutputOfPrintWelcomeBanner = new ByteArrayOutputStream();
        PrintStream byteOutputOfPrintWelcomeBannerToPrintStream = new PrintStream(byteOutputOfPrintWelcomeBanner);
        System.setOut(byteOutputOfPrintWelcomeBannerToPrintStream);

        program.printWelcomeBanner();
        System.setOut(original);
        byteOutputOfPrintWelcomeBannerToPrintStream.flush();

        assertEquals(welcomeBanner, byteOutputOfPrintWelcomeBanner.toString());
    }

    @Test
    public void testPrintWinningBanner() {
        String winnerBannerPlayer1 = "Player 1 (X) has won! Congrats!\n";
        String winnerBannerPlayer2 = winnerBannerPlayer1.replace("1 (X)", "2 (O)");
        String winnerBannerPlayer3 = winnerBannerPlayer1.replace("1 (X)", "3 (Y)");
        String drawBanner = "It's a Draw!\n";
        int winner = 0;

        PrintStream original = System.out;
        ByteArrayOutputStream byteOutputOfPrintWinningBanner = new ByteArrayOutputStream();
        PrintStream byteOutputOfPrintWinningBannerToPrintStream = new PrintStream(byteOutputOfPrintWinningBanner);
        System.setOut(byteOutputOfPrintWinningBannerToPrintStream);

        program.setWinningPlayer(winner);
        program.printWinningBanner();
        System.setOut(original);
        byteOutputOfPrintWinningBannerToPrintStream.flush();

        assertEquals(drawBanner, byteOutputOfPrintWinningBanner.toString());
    }

    @Test
    void testPrintPlayerDirections() {
        String player1Directions = "Player 1! Please enter the coordinate of your move (row[1-100] column[1-100]): \n";
        String player2Directions = player1Directions.replace("1", "2");
        String player3Directions = player1Directions.replace("1", "3");
        int player = 3;

        PrintStream original = System.out;
        ByteArrayOutputStream byteOutputOfPrintPlayerDirections = new ByteArrayOutputStream();
        PrintStream byteOutputOfPrintPlayerDirectionsToPrintStream = new PrintStream(byteOutputOfPrintPlayerDirections);
        System.setOut(byteOutputOfPrintPlayerDirectionsToPrintStream);

        program.printPlayerDirections(player);
        System.setOut(original);
        byteOutputOfPrintPlayerDirectionsToPrintStream.flush();

        assertEquals(player3Directions, byteOutputOfPrintPlayerDirections.toString());
    }

    @Test
    public void testDrawGameWin() {
        int numberOfGameBoardRows = 100;
        int numberOfGameBoardColumns = 100;

        int nonWinningGameBoardCell1 = 5;
        int nonWinningGameBoardCell2 = 7;


        for (int row = 0; row < numberOfGameBoardRows; row++) {
            for (int column = 0; column < numberOfGameBoardColumns; column++) {
                program.gameBoard[row][column] = (row + column) % 2 == 0 ? nonWinningGameBoardCell2 : nonWinningGameBoardCell1;
            }
        }

        program.winConditions();

        assertEquals(program.winningPlayer, 0);
    }

    @Test
    public void testGameBoardIsFull() {
        boolean isFullShouldReturnTrue;

        int numberOfGameBoardRows = 100;
        int numberOfGameBoardColumns = 100;
        int somePlayer = 1;


        for (int row = 0; row < numberOfGameBoardRows; ++row) {
            for (int column = 0; column < numberOfGameBoardColumns; ++column) {
                program.gameBoard[row][column] = somePlayer;
            }
        }

        isFullShouldReturnTrue = program.gameBoardIsFull();

        assertTrue(isFullShouldReturnTrue);
    }

    @Test
    public void testWinConditionsHorizontalRow() {
        int winningPlayer = 3;
        int numberOfGameBoardColumns = 100;
        int numberOfGameBoardRows = 100;
        int actualWinningPlayer = 0;

        for (int row = 0; row < numberOfGameBoardRows; ++row) {
            for (int column = 0; column < numberOfGameBoardColumns; ++column) {
                program.gameBoard[row][column] = winningPlayer;
            }
        }

        if (program.winConditions()) {
            if (program.horizontalWinPlayer1()) {
                actualWinningPlayer = 1;
            }
            if (program.horizontalWinPlayer2()) {
                actualWinningPlayer = 2;
            }
            if (program.horizontalWinPlayer3()) {
                actualWinningPlayer = 3;
            }

            assertEquals(program.winningPlayer, winningPlayer);
        }
    }

    @Test
    public void testWinConditionsVerticalColumn() {
        int winningPlayer = 3;
        int numberOfGameBoardRows = 100;
        int numberOfGameBoardColumns = 100;
        int actualWinningPlayer = 0;

        for (int row = 0; row < numberOfGameBoardRows; ++row) {
            for (int column = 0; column < numberOfGameBoardColumns; ++column) {
                program.gameBoard[row][column] = winningPlayer;
            }
        }

        if (program.winConditions()) {
            if (program.verticalWinPlayer1()) {
                actualWinningPlayer = 1;
            }
            if (program.verticalWinPlayer2()) {
                actualWinningPlayer = 2;
            }
            if (program.verticalWinPlayer3()) {
                actualWinningPlayer = 3;
            }

            assertEquals(program.winningPlayer, winningPlayer);
        }
    }

    @Test
    public void testWinConditionsDiagonalLeft() {
        int winningPlayer = 1;
        int numberOfGameBoardRows = 100;
        int numberOfGameBoardColumns = 100;
        int actualWinningPlayer;

        for (int row = 0; row < numberOfGameBoardRows; ++row) {
            for (int column = 0; column < numberOfGameBoardColumns; ++column) {
                for (int i = row; i < numberOfGameBoardRows; i++) {
                    program.gameBoard[i][numberOfGameBoardRows - 1 - i] = winningPlayer;
                }
            }
        }

        if (program.winConditions()) {
            if (program.diagonalWinLeftPlayer1()) {
                actualWinningPlayer = 1;
            }
            if (program.diagonalWinLeftPlayer2()) {
                actualWinningPlayer = 2;
            }
            if (program.diagonalWinLeftPlayer3()) {
                actualWinningPlayer = 3;
            }

            assertEquals(program.winningPlayer, winningPlayer);
        }
    }

    @Test
    public void testWinConditionsDiagonalRight() {
        int winningPlayer = 1;
        int numberOfGameBoardRows = 100;
        int numberOfGameBoardColumns = 100;
        int actualWinningPlayer;

        for (int row = 0; row < numberOfGameBoardRows; ++row) {
            for (int column = 0; column < numberOfGameBoardColumns; ++column) {
                for (int i = row; i < numberOfGameBoardRows; i++) {
                    program.gameBoard[i][i] = winningPlayer;
                }
            }
        }

        if (program.winConditions()) {
            if (program.diagonalWinRightPlayer1()) {
                actualWinningPlayer = 1;
            }
            if (program.diagonalWinRightPlayer2()) {
                actualWinningPlayer = 2;
            }
            if (program.diagonalWinRightPlayer3()) {
                actualWinningPlayer = 3;
            }

            assertEquals(program.winningPlayer, winningPlayer);
        }



    }

    @Test
    public void testInputSizeOfGameBoard() {

    }

    @Test
    public void testInitializeGame() {
        InputStream enterButton = System.in;
        ByteArrayInputStream enterButtonByteStream = new ByteArrayInputStream("My string".getBytes());
        System.setIn(enterButtonByteStream);

        program.initializeGame();

        assertTrue(enterButtonByteStream.hasNext());

        System.setIn(enterButton);

    }




    
}
