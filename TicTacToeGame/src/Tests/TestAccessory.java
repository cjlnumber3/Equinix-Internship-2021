package Tests;

import com.MainPackage.Main;

import java.util.function.Predicate;

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

    public boolean validateCellStatus(int gameBoard) {
        int numberOfGameBoardColumns = 3;
        int numberOfGameBoardRows = 3;

        boolean isValid = false;

        for (int row = 0; row < numberOfGameBoardRows; ++row) {
            for (int col = 0; col < numberOfGameBoardColumns; ++col) {
                if (Main.gameBoard[row][col] == 0) {
                    isValid = true;
                }
                else if (Main.gameBoard[row][col] == 1) {
                    isValid = true;
                }
                else if (Main.gameBoard[row][col] == 3) {
                    isValid = false;
                }
            }
        }

        return isValid;

    }


}
