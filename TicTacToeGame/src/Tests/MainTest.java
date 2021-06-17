package Tests;

import com.MainPackage.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class MainTest {

    private TestAccessory mockPlayer;
    private Main mockGame;

    @BeforeEach
    void setUp() {
        mockPlayer = new TestAccessory();
        mockGame = new Main();
    }

    @Test
    void validateInput() {
        String playerCoordinates = "1 1";
        boolean isValid = mockPlayer.validatePlayerCoordinates(playerCoordinates);
        assertTrue(isValid);

    }


//======================================================================================================================



    @Test
    void runGame() {
    }

    @Test
    void playerMove() {
    }

    @Test
    void validMovement() {

    }

    @Test
    void printGameBoard() {

    }

    @Test
    void clearGameBoard() {
        Main.clearGameBoard();
        int clearedCell = Main.getCellStatus(1,  0); //range of 0-2 in array
        assertEquals(3, clearedCell, "test");



    }

    @Test
    void getCellStatus() {
        int cellStatus = Main.getCellStatus(2, 2);
        boolean isValid = mockPlayer.validateCellStatus(Main.gameBoard[1][2]);
        assertTrue(isValid); //is a free space

    }

    @Test
    void printCell() {
        int test = Main.getCellStatus(2, 2);

    }

    @Test
    void getPrintedCell() {

    }

    @Test
    void printCellIfInputPlayerIs1() {

    }

    @Test
    void printCellIfInputPlayerIs2() {

    }



    @Test
    public void winConditions() {

    }

    @Test
    public void printWelcomeBanner() {

    }

    @Test
    public void printWinningBanner() {

    }

    @Test
    void initializeGame() {

    }

    @Test
    void printPlayerDirections() {

    }
}