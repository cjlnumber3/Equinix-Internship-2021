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

        if (playerCoordinates.contains(possiblePlayerCoordinates[0])) {
            return true;
        }
        else if (playerCoordinates.contains(possiblePlayerCoordinates[1])) {
            return true;
        }
        else if (playerCoordinates.contains(possiblePlayerCoordinates[2])) {
            return true;
        }
        else if (playerCoordinates.contains(possiblePlayerCoordinates[3])) {
            return true;
        }
        else if (playerCoordinates.contains(possiblePlayerCoordinates[4])) {
            return true;
        }
        else if (playerCoordinates.contains(possiblePlayerCoordinates[5])) {
            return true;
        }
        else if (playerCoordinates.contains(possiblePlayerCoordinates[6])) {
            return true;
        }
        else if (playerCoordinates.contains(possiblePlayerCoordinates[7])) {
            return true;
        }
        else if (playerCoordinates.contains(possiblePlayerCoordinates[8])) {
            return true;
        }
        else if (!playerCoordinates.equals(playerCoordinates.toString())) {
            return false;
        }


        return false;
    }

    public String validatePrintedCell(int row, int column) {
        int numberOfGameBoardColumns = 3;
        int numberOfGameBoardRows = 3;

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

    public void Point() {

    }






}
