package Tests;

import com.MainPackage.Main;
import com.MainPackage.playerInput;
import jdk.nashorn.internal.ir.LiteralNode;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class MainTest {

    private playerInput mockPlayer;
    private Main mockGame;

    @BeforeEach
    void setUp() {
        mockPlayer = new playerInput();
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
    }

    @Test
    void printCell() {

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