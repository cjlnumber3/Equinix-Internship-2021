package Tests;

import com.MainPackage.Main;

public class TestAccessory {
    Main program = new Main();

    public String validatePrintedCell(int row, int column) {
        String printedCell = " ";

        if (program.gameBoard[row][column] == 2) {
            printedCell = " O ";
        }
        else if (program.gameBoard[row][column] == 1) {
            printedCell = " X ";
        }
        else if (program.gameBoard[row][column] == 3) {
            printedCell = " Y ";
        }
        else if (program.gameBoard[row][column] == 0) {
            printedCell = "   ";
        }

        return printedCell;
    }

    public String emptyGameBoard() {
        int winningPlayer = 1; // 0, 1 or 3 as per game
        int numberOfGameBoardRows = 100;
        int numberOfGameBoardColumns = 100;

        for (int row = 0; row < numberOfGameBoardRows; ++row) {
            System.out.print("   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   " +
                    "|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   " +
                    "|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   " +
                    "|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   " +
                    "|   |   " +
                    "\n--------------------------------------------------------------------------------------------------" +
                    "----------------------------------------------------------------------------------------------------" +
                    "----------------------------------------------------------------------------------------------------" +
                    "-----------------------------------------------------------------------------------------------------\n");

        }

        return null;
    }

}
