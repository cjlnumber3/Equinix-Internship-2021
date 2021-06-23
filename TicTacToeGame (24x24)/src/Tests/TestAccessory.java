package Tests;

import com.MainPackage.Main;

public class TestAccessory {


    public String validatePrintedCell(int row, int column) {
        String printedCell = " ";

        if (Main.gameBoard[row][column] == 0) {
            printedCell = " O ";
        }
        else if (Main.gameBoard[row][column] == 1) {
            printedCell = " X ";
        }
        else if (Main.gameBoard[row][column] == 3) {
            printedCell = "   ";
        }

        return printedCell;
    }

    public String emptyGameBoard() {
        int winningPlayer = 1; // 0, 1 or 3 as per game
        int numberOfGameBoardRows = 24;
        int numberOfGameBoardColumns = 24;

        for (int row = 0; row < numberOfGameBoardRows; ++row) {
            System.out.print("   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   \n-----------------------------------------------------------------------------------------------\n");

        }

        return null;
    }

}
