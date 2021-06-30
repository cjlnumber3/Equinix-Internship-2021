package com.MainPackage;

import java.util.Scanner;

public class Main {
    public Scanner playerInput = new Scanner(System.in);

    private int playerCount = 1;

    public static int[][] gameBoard;

    public static int sizeOfGameBoard;

    public int winningPlayer;

    public static void main(String[] args) {

        Main program = new Main();

        program.printWelcomeBanner();

        sizeOfGameBoard = program.inputSizeOfGameBoard();

        gameBoard = new int[sizeOfGameBoard][sizeOfGameBoard];

        program.clearGameBoard();

        program.runGame();

        program.printGameBoard();

        program.printWinningBanner();
    }

    public int inputSizeOfGameBoard() {
        System.out.print("Enter the size of the game board: ");
        Scanner sizeOfGameBoardInput = new Scanner(System.in);
        int sizeOfGameBoard = sizeOfGameBoardInput.nextInt();

        return sizeOfGameBoard;
    }

    public void runGame(){

        initializeGame();

        playerMove(1);

        do {
            playerCount++;
            printGameBoard();
            if (playerCount % 3 == 0) {
                playerMove(3);
            }
            if (playerCount % 3 == 2) {
                playerMove(2);
            }
            if (playerCount % 3 == 1) {
                playerMove(1);
            }
        } while (!winConditions());
    }

    public void playerMove(int currentPlayer) {

        boolean validInput = false;

        do {

            printPlayerDirections(currentPlayer);
            int row = playerInput.nextInt() - 1;
            int column = playerInput.nextInt() - 1;

            validInput = validMovement(row, column, currentPlayer);
        } while (!validInput);
    }

    public boolean getValidMovementReturnsTrueIfFree(int row, int column, int player) {
        int emptyCell = 0;

        if (gameBoard[row][column] != emptyCell) {
            return false;
        }

        return true;
    }

    public boolean validMovement(int row, int column, int currentPlayer) {
        int numberOfGameBoardRows = sizeOfGameBoard;
        int numberOfGameBoardColumns = sizeOfGameBoard;

        int emptyCell = 0;


        if (row >= 0 && row < numberOfGameBoardRows && column >= 0 && column < numberOfGameBoardColumns && gameBoard[row][column] == emptyCell) {
            gameBoard[row][column] = currentPlayer;
            return true;
        } else if (gameBoard[row][column] != 0) {
            System.out.print("This move at (" + (row + 1) + "," + (column + 1) + ") is taken.\n");
        }

        return false;
    }

    public void printGameBoard() {
        int numberOfGameBoardRows = sizeOfGameBoard;
        int numberOfGameBoardColumns = sizeOfGameBoard;

        for (int row = 0; row < numberOfGameBoardRows; ++row) {
            for (int column = 0; column < numberOfGameBoardColumns; ++column) {
                printCell(gameBoard[row][column]);
                if (column != numberOfGameBoardColumns - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (row != numberOfGameBoardRows - 1) {
                System.out.println("------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------" +
                        "-------------------------------------------------------");
            }
        }
        System.out.println();
    }

    public void clearGameBoard() {
        int numberOfGameBoardRows = sizeOfGameBoard;
        int numberOfGameBoardColumns = sizeOfGameBoard;

        for (int row = 0; row < numberOfGameBoardRows; ++row) {
            for (int column = 0; column < numberOfGameBoardColumns; ++column) {
                gameBoard[row][column] = 0;
            }
        }
    }

    public int getCellStatus(int row, int column) {
        return gameBoard[row][column];
    }

    public void setCellStatus(int row, int column, int player) {
        gameBoard[row][column] = player;
    }

    public void printCell(int player) {
        final int emptyCell = 0;
        final int XCell = 1;
        final int OCell = 2;
        final int YCell = 3;

        switch (player) {
            case emptyCell: {
                System.out.print("   ");
            }
            break;
            case OCell: {
                System.out.print(" O ");
            }
            break;
            case XCell: {
                System.out.print(" X ");
            }
            break;
            case YCell: {
                System.out.print(" Y ");
            }
            break;
        }
    }

    public void printWelcomeBanner() {
        System.out.print("Welcome to a game of Tic-Tac-Toe! (Player 1 is X, Player 2 is O, and Player 3 is Y)\n");
    }

    public void printWinningBanner() {
        String winnerBannerPlayer1 = "Player 1 (X) has won! Congrats!\n";
        String winnerBannerPlayer2 = winnerBannerPlayer1.replace("1 (X)", "2 (O)");
        String winnerBannerPlayer3 = winnerBannerPlayer1.replace("1 (X)", "3 (Y)");
        String drawGameBanner = "It's a Draw!\n";

        if (winningPlayer == 1) {
            System.out.print(winnerBannerPlayer1);
        }
        else if (winningPlayer == 2) {
            System.out.print(winnerBannerPlayer2);
        }
        else if (winningPlayer == 3) {
            System.out.print(winnerBannerPlayer3);
        }
        else if (winningPlayer == 0) {
            System.out.print(drawGameBanner);
        }
    }

    public void setWinningPlayer(int player) {
        winningPlayer = player;
    }

    public void initializeGame() {
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public void printPlayerDirections(int currentPlayer) {
        int XCell = 1;
        int OCell = 2;
        int YCell = 3;

        String player1Directions = "Player 1! Please enter the coordinate of your move (row[1-100] column[1-100]): \n";
        String player2Directions = player1Directions.replace("1", "2");
        String player3Directions = player1Directions.replace("1", "3");

        if (currentPlayer == XCell) {
            System.out.print(player1Directions);
        }
        else if (currentPlayer == OCell) {
            System.out.print(player2Directions);
        }
        else if (currentPlayer == YCell) {
            System.out.print(player3Directions);
        }
    }

    public boolean winConditions() {
        Main program = new Main();

        if (program.gameBoardIsFull()) {
            if (program.horizontalWinPlayer1()) {
                return true;
            }
            if (program.horizontalWinPlayer2()) {
                return true;
            }
            if (program.horizontalWinPlayer3()) {
                return true;
            }
            if (program.verticalWinPlayer1()) {
                return true;
            }
            if (program.verticalWinPlayer2()) {
                return true;
            }
            if (program.verticalWinPlayer3()) {
                return true;
            }
            if (program.diagonalWinLeftPlayer1()) {
                return true;
            }
            if (program.diagonalWinRightPlayer1()) {
                return true;
            }
            if (program.diagonalWinLeftPlayer2()) {
                return true;
            }
            if (program.diagonalWinRightPlayer2()) {
                return true;
            }
            if (program.diagonalWinLeftPlayer3()) {
                return true;
            }
            if (program.diagonalWinRightPlayer3()) {
                return true;
            }
            if (drawGame()) {
                return true;
            }
        }
        return false;
    }

    public boolean gameBoardIsFull() {
        for (int row = 0; row < gameBoard.length; row++) {
            for (int column = 0; column < gameBoard[row].length; column++) {
                if (gameBoard[row][column] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean horizontalWinPlayer1() {
        Main program = new Main();

        int numberOfGameBoardRows = sizeOfGameBoard;
        int numberOfGameBoardColumns = sizeOfGameBoard;
        int numberToWin = sizeOfGameBoard;

        for (int row = 0; row < numberOfGameBoardRows; row++) {
            if (gameBoard[row][0] == 1) {

                int column;
                for (column = 1; column < numberOfGameBoardColumns; column++) {
                    if (gameBoard[row][column] != 1) {
                        break;
                    }
                }
                if (column == numberToWin) {
                    program.setWinningPlayer(1);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean verticalWinPlayer1() {
        int numberOfGameBoardRows = sizeOfGameBoard;
        int numberOfGameBoardColumns = sizeOfGameBoard;
        int numberToWin = sizeOfGameBoard;

        for (int row = 0; row < numberOfGameBoardRows; row++) {
            if (gameBoard[0][row] == 1) {

                int column;
                for (column = 1; column < numberOfGameBoardColumns; column++) {
                    if (gameBoard[column][row] != 1) {
                        break;
                    }
                }
                if (column == numberToWin) {
                    setWinningPlayer(1);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean horizontalWinPlayer2() {
        int numberOfGameBoardRows = sizeOfGameBoard;
        int numberOfGameBoardColumns = sizeOfGameBoard;
        int numberToWin = sizeOfGameBoard;

        for (int row = 0; row < numberOfGameBoardRows; row++) {
            if (gameBoard[row][0] == 0) {

                int column;
                for (column = 1; column < numberOfGameBoardColumns; column++) {
                    if (gameBoard[row][column] != 2) {
                        break;
                    }
                }
                if (column == numberToWin) {
                    setWinningPlayer(2);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean verticalWinPlayer2() {
        int numberOfGameBoardRows = sizeOfGameBoard;
        int numberOfGameBoardColumns = sizeOfGameBoard;
        int numberToWin = sizeOfGameBoard;

        for (int row = 0; row < numberOfGameBoardRows; row++) {
            if (gameBoard[0][row] == 0) {

                int column;
                for (column = 1; column < numberOfGameBoardColumns; column++) {
                    if (gameBoard[column][row] != 2) {
                        break;
                    }
                }
                if (column == numberToWin) {
                    setWinningPlayer(2);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean horizontalWinPlayer3() {
        int numberOfGameBoardRows = sizeOfGameBoard;
        int numberOfGameBoardColumns = sizeOfGameBoard;
        int numberToWin = sizeOfGameBoard;

        for (int row = 0; row < numberOfGameBoardRows; row++) {
            if (gameBoard[row][0] == 0) {

                int column;
                for (column = 1; column < numberOfGameBoardColumns; column++) {
                    if (gameBoard[row][column] != 3) {
                        break;
                    }
                }
                if (column == numberToWin) {
                    setWinningPlayer(3);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean verticalWinPlayer3() {
        int numberOfGameBoardRows = sizeOfGameBoard;
        int numberOfGameBoardColumns = sizeOfGameBoard;
        int numberToWin = sizeOfGameBoard;

        for (int row = 0; row < numberOfGameBoardRows; row++) {
            if (gameBoard[0][row] == 0) {

                int column;
                for (column = 1; column < numberOfGameBoardColumns; column++) {
                    if (gameBoard[column][row] != 3) {
                        break;
                    }
                }
                if (column == numberToWin) {
                    setWinningPlayer(3);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean diagonalWinRightPlayer1() {
        int numberOfGameBoardRows = sizeOfGameBoard;
        int numberOfGameBoardColumns = sizeOfGameBoard;
        int numberToWin = sizeOfGameBoard;
        int countToWin = 0;

        for (int row = 0; row < (numberOfGameBoardRows - (numberToWin - 1)); row++) {
            for (int column = 0; column < numberOfGameBoardColumns; column++) {
                for (int diagonalUnit = row; diagonalUnit < numberOfGameBoardRows; diagonalUnit++) {

                    if (gameBoard[diagonalUnit][diagonalUnit] == 0) {
                        setWinningPlayer(0);
                    } else if (gameBoard[diagonalUnit][diagonalUnit] == 1) {
                        countToWin++;

                        if (countToWin >= numberToWin) {
                            setWinningPlayer(1);
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public boolean diagonalWinLeftPlayer1() {
        int numberOfGameBoardRows = sizeOfGameBoard;
        int numberOfGameBoardColumns = sizeOfGameBoard;
        int numberToWin = sizeOfGameBoard;
        int countToWin = 0;

        for (int row = 0; row < (numberOfGameBoardRows - (numberToWin - 1)); row++) {
            for (int column = 0; column < numberOfGameBoardColumns; column++) {
                for (int diagonalUnit = row; diagonalUnit < numberOfGameBoardRows; diagonalUnit++) {

                    if (gameBoard[diagonalUnit][numberOfGameBoardRows - 1 - diagonalUnit] == 0) {
                        setWinningPlayer(0);
                    } else if (gameBoard[diagonalUnit][numberOfGameBoardRows - 1 - diagonalUnit] == 1) {
                        countToWin++;

                        if (countToWin >= numberToWin) {
                            setWinningPlayer(1);
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public boolean diagonalWinRightPlayer2() {
        int numberOfGameBoardRows = sizeOfGameBoard;
        int numberOfGameBoardColumns = sizeOfGameBoard;
        int numberToWin = sizeOfGameBoard;
        int countToWin = 0;

        for (int row = 0; row < (numberOfGameBoardRows - (numberToWin - 1)); row++) {
            for (int column = 0; column < numberOfGameBoardColumns; column++) {
                for (int diagonalUnit = row; diagonalUnit < numberOfGameBoardRows; diagonalUnit++) {

                    if (gameBoard[diagonalUnit][diagonalUnit] == 0) {
                        setWinningPlayer(0);
                    } else if (gameBoard[diagonalUnit][diagonalUnit] == 2) {
                        countToWin++;

                        if (countToWin >= numberToWin) {
                            setWinningPlayer(2);
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public boolean diagonalWinLeftPlayer2() {
        int numberOfGameBoardRows = sizeOfGameBoard;
        int numberOfGameBoardColumns = sizeOfGameBoard;
        int numberToWin = sizeOfGameBoard;
        int countToWin = 0;

        for (int row = 0; row < (numberOfGameBoardRows - (numberToWin - 1)); row++) {
            for (int column = 0; column < numberOfGameBoardColumns; column++) {
                for (int diagonalUnit = row; diagonalUnit < numberOfGameBoardRows; diagonalUnit++) {

                    if (gameBoard[diagonalUnit][numberOfGameBoardRows - 1 - diagonalUnit] == 0) {
                        setWinningPlayer(0);
                    } else if (gameBoard[diagonalUnit][numberOfGameBoardRows - 1 - diagonalUnit] == 2) {
                        countToWin++;

                        if (countToWin >= numberToWin) {
                            setWinningPlayer(2);
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public boolean diagonalWinRightPlayer3() {
        int numberOfGameBoardRows = sizeOfGameBoard;
        int numberOfGameBoardColumns = sizeOfGameBoard;
        int numberToWin = sizeOfGameBoard;
        int countToWin = 0;

        for (int row = 0; row < (numberOfGameBoardRows - (numberToWin - 1)); row++) {
            for (int column = 0; column < numberOfGameBoardColumns; column++) {
                for (int diagonalUnit = row; diagonalUnit < numberOfGameBoardRows; diagonalUnit++) {

                    if (gameBoard[diagonalUnit][diagonalUnit] == 0) {
                        setWinningPlayer(0);
                    } else if (gameBoard[diagonalUnit][diagonalUnit] == 3) {
                        countToWin++;

                        if (countToWin >= numberToWin) {
                            setWinningPlayer(3);
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public boolean diagonalWinLeftPlayer3() {
        int numberOfGameBoardRows = sizeOfGameBoard;
        int numberOfGameBoardColumns = sizeOfGameBoard;
        int numberToWin = sizeOfGameBoard;
        int countToWin = 0;

        for (int row = 0; row < (numberOfGameBoardRows - (numberToWin - 1)); row++) {
            for (int column = 0; column < numberOfGameBoardColumns; column++) {
                for (int diagonalUnit = row; diagonalUnit < numberOfGameBoardRows; diagonalUnit++) {

                    if (gameBoard[diagonalUnit][numberOfGameBoardRows - 1 - diagonalUnit] == 0) {
                        setWinningPlayer(0);
                    } else if (gameBoard[diagonalUnit][numberOfGameBoardRows - 1 - diagonalUnit] == 3) {
                        countToWin++;

                        if (countToWin >= numberToWin) {
                            setWinningPlayer(3);
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public boolean drawGame() {
        Main program = new Main();

        if (program.gameBoardIsFull()
                && !program.horizontalWinPlayer1() && !program.horizontalWinPlayer2()
                && !program.verticalWinPlayer1() && !program.verticalWinPlayer2()
                && !program.diagonalWinLeftPlayer1() && !program.diagonalWinRightPlayer1()
                && !program.diagonalWinLeftPlayer2() && !program.diagonalWinRightPlayer2()
                && !program.diagonalWinLeftPlayer3() && !program.diagonalWinRightPlayer3()) {
            program.setWinningPlayer(0);
            return true;
        }
        return false;
    }
}

