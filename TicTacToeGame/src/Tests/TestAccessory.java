package Tests;

import com.MainPackage.Main;

public class TestAccessory {

    public boolean validatePlayerCoordinates (String playerCoordinates) {
        String[] possiblePlayerCoordinates = new String[9];
        possiblePlayerCoordinates[0] = "1 1";
        possiblePlayerCoordinates[1] = "1 2";
        possiblePlayerCoordinates[2] = "1 3";
        possiblePlayerCoordinates[3] = "2 1";
        possiblePlayerCoordinates[4] = "2 2";
        possiblePlayerCoordinates[5] = "2 3";
        possiblePlayerCoordinates[6] = "3 1";
        possiblePlayerCoordinates[7] = "3 2";
        possiblePlayerCoordinates[8] = "3 3";

        if (playerCoordinates.contains(possiblePlayerCoordinates[0])
                || playerCoordinates.contains(possiblePlayerCoordinates[1])
                || playerCoordinates.contains(possiblePlayerCoordinates[2])
                || playerCoordinates.contains(possiblePlayerCoordinates[3])
                || playerCoordinates.contains(possiblePlayerCoordinates[4])
                || playerCoordinates.contains(possiblePlayerCoordinates[5])
                || playerCoordinates.contains(possiblePlayerCoordinates[6])
                || playerCoordinates.contains(possiblePlayerCoordinates[7])
                || playerCoordinates.contains(possiblePlayerCoordinates[8])) {
            return true;
        }

        return false;
    }

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

}
