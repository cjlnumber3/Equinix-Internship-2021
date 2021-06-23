package Tests;

import com.MainPackage.Main;

public class TestAccessory {

    public boolean validatePlayerCoordinates (String playerCoordinates) {
        String[] possiblePlayerCoordinates = new String[36];
        possiblePlayerCoordinates[0] = "1 1";
        possiblePlayerCoordinates[1] = "1 2";
        possiblePlayerCoordinates[2] = "1 3";
        possiblePlayerCoordinates[3] = "1 4";
        possiblePlayerCoordinates[4] = "1 5";
        possiblePlayerCoordinates[5] = "1 6";

        possiblePlayerCoordinates[6] = "2 1";
        possiblePlayerCoordinates[7] = "2 2";
        possiblePlayerCoordinates[8] = "2 3";
        possiblePlayerCoordinates[9] = "2 4";
        possiblePlayerCoordinates[10] = "2 5";
        possiblePlayerCoordinates[11] = "2 6";

        possiblePlayerCoordinates[12] = "3 1";
        possiblePlayerCoordinates[13] = "3 2";
        possiblePlayerCoordinates[14] = "3 3";
        possiblePlayerCoordinates[15] = "3 4";
        possiblePlayerCoordinates[16] = "3 5";
        possiblePlayerCoordinates[17] = "3 6";

        possiblePlayerCoordinates[18] = "4 1";
        possiblePlayerCoordinates[19] = "4 2";
        possiblePlayerCoordinates[20] = "4 3";
        possiblePlayerCoordinates[21] = "4 4";
        possiblePlayerCoordinates[22] = "4 5";
        possiblePlayerCoordinates[23] = "4 6";

        possiblePlayerCoordinates[24] = "5 1";
        possiblePlayerCoordinates[25] = "5 2";
        possiblePlayerCoordinates[26] = "5 3";
        possiblePlayerCoordinates[27] = "5 4";
        possiblePlayerCoordinates[28] = "5 5";
        possiblePlayerCoordinates[29] = "5 6";

        possiblePlayerCoordinates[30] = "6 1";
        possiblePlayerCoordinates[31] = "6 2";
        possiblePlayerCoordinates[32] = "6 3";
        possiblePlayerCoordinates[33] = "6 4";
        possiblePlayerCoordinates[34] = "6 5";
        possiblePlayerCoordinates[35] = "6 6";



        if (playerCoordinates.contains(possiblePlayerCoordinates[0])
                || playerCoordinates.contains(possiblePlayerCoordinates[1])
                || playerCoordinates.contains(possiblePlayerCoordinates[2])
                || playerCoordinates.contains(possiblePlayerCoordinates[3])
                || playerCoordinates.contains(possiblePlayerCoordinates[4])
                || playerCoordinates.contains(possiblePlayerCoordinates[5])
                || playerCoordinates.contains(possiblePlayerCoordinates[6])
                || playerCoordinates.contains(possiblePlayerCoordinates[7])
                || playerCoordinates.contains(possiblePlayerCoordinates[8])
                || playerCoordinates.contains(possiblePlayerCoordinates[9])
                || playerCoordinates.contains(possiblePlayerCoordinates[10])
                || playerCoordinates.contains(possiblePlayerCoordinates[11])
                || playerCoordinates.contains(possiblePlayerCoordinates[12])
                || playerCoordinates.contains(possiblePlayerCoordinates[13])
                || playerCoordinates.contains(possiblePlayerCoordinates[14])
                || playerCoordinates.contains(possiblePlayerCoordinates[15])
                || playerCoordinates.contains(possiblePlayerCoordinates[16])
                || playerCoordinates.contains(possiblePlayerCoordinates[17])
                || playerCoordinates.contains(possiblePlayerCoordinates[18])
                || playerCoordinates.contains(possiblePlayerCoordinates[19])
                || playerCoordinates.contains(possiblePlayerCoordinates[20])
                || playerCoordinates.contains(possiblePlayerCoordinates[21])
                || playerCoordinates.contains(possiblePlayerCoordinates[22])
                || playerCoordinates.contains(possiblePlayerCoordinates[23])
                || playerCoordinates.contains(possiblePlayerCoordinates[24])
                || playerCoordinates.contains(possiblePlayerCoordinates[25])
                || playerCoordinates.contains(possiblePlayerCoordinates[26])
                || playerCoordinates.contains(possiblePlayerCoordinates[27])
                || playerCoordinates.contains(possiblePlayerCoordinates[28])
                || playerCoordinates.contains(possiblePlayerCoordinates[29])
                || playerCoordinates.contains(possiblePlayerCoordinates[30])
                || playerCoordinates.contains(possiblePlayerCoordinates[31])
                || playerCoordinates.contains(possiblePlayerCoordinates[32])
                || playerCoordinates.contains(possiblePlayerCoordinates[33])
                || playerCoordinates.contains(possiblePlayerCoordinates[34])
                || playerCoordinates.contains(possiblePlayerCoordinates[35])) {
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
