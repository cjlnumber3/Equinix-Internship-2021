package com.MainPackage;

import java.util.Scanner;

public class Main {
    public static Scanner playerInput = new Scanner(System.in);

    private static int playerCount = 1;

    public static int[][] gameBoard = new int[24][24];

    public static int winningPlayer;

    public static void main(String[] args) {

        clearGameBoard();

        printWelcomeBanner();

        runGame();

        printGameBoard();

        printWinningBanner();
    }

    public static void runGame() {

        initializeGame();

        playerMove(playerCount);

        do {
            playerCount++;
            printGameBoard();
            playerMove(playerCount % 2);
        } while (!winConditions());
    }

    public static void playerMove(int currentPlayer) {

        boolean validInput = false;

        do {

            printPlayerDirections(currentPlayer);
            int row = playerInput.nextInt() - 1;
            int column = playerInput.nextInt() - 1;

            validInput = validMovement(row, column, currentPlayer);
        } while (!validInput);
    }

    public static boolean getValidMovementReturnsTrueIfFree(int row, int column, int player) {
        int emptyCell = 3;

        if (gameBoard[row][column] != emptyCell) {
            return false;
        }

        return true;
    }

    public static boolean validMovement(int row, int column, int currentPlayer) {
        int numberOfGameBoardRows = 24;
        int numberOfGameBoardColumns = 24;

        int emptyCell = 3;

        boolean validInput = false;

        if (row >= 0 && row < numberOfGameBoardRows && column >= 0 && column < numberOfGameBoardColumns && gameBoard[row][column] == emptyCell) {
            gameBoard[row][column] = currentPlayer;
            return true;
        }
        else if (gameBoard[row][column] != 3) {
            System.out.print("This move at (" + (row + 1) + "," + (column + 1) + ") is taken.\n");
        }

        return false;
    }

    public static void printGameBoard() {
        int numberOfGameBoardRows = 24;
        int numberOfGameBoardColumns = 24;

        for (int row = 0; row < numberOfGameBoardRows; ++row) {
            for (int column = 0; column < numberOfGameBoardColumns; ++column) {
                printCell(gameBoard[row][column]);
                if (column != numberOfGameBoardColumns - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (row != numberOfGameBoardRows - 1) {
                System.out.println("-----------------------------------------------------------------------------------------------");
            }
        }
        System.out.println();
    }

    public static void clearGameBoard() {
        int numberOfGameBoardRows = 24;
        int numberOfGameBoardColumns = 24;

        for (int row = 0; row < numberOfGameBoardRows; ++row) {
            for (int column = 0; column < numberOfGameBoardColumns; ++column) {
                gameBoard[row][column] = 3;
            }
        }
    }

    public static int getCellStatus(int row, int column) {
        return gameBoard[row][column];
    }

    public static void setCellStatus(int row, int column, int player) {
        gameBoard[row][column] = player;
    }

    public static void printCell(int player) {
        final int emptyCell = 3;
        final int XCell = 1;
        final int OCell = 0;

        switch (player) {
            case emptyCell: {
                System.out.print("   ");
            } break; //0
            case OCell: {
                System.out.print(" O ");
            } break; //2
            case XCell:  {
                System.out.print(" X ");
            } break; //1
        }
    }

    public static void printWelcomeBanner() {
        System.out.print("Welcome to a game of Tic-Tac-Toe! (Player 1 is X, and Player 2 is O)\n");
    }

    public static void printWinningBanner() {
        if (winningPlayer == 1) {
            System.out.print("Player 1 (X) has won! Congrats!\n");
        } else if (winningPlayer == 0) {
            System.out.print("Player 2 (O) has won! Congrats!\n");
        } else if (winningPlayer == 3) {
            System.out.print("It's a Draw!\n");
        }
    }

    public static void setWinningPlayer(int player) {
        winningPlayer = player;
    }

    public static void initializeGame() {
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static void printPlayerDirections(int currentPlayer) {
        int XCell = 1;
        int OCell = 0;

        String playerDirectionsString;

        if (currentPlayer == XCell) {
            playerDirectionsString = "Player 1! Please enter the coordinate of your move (row[1-24] column[1-24]): \n";
            System.out.print(playerDirectionsString);
        }
        else if (currentPlayer == OCell) {
            playerDirectionsString = "Player 2! Please enter the coordinate of your move (row[1-24] column[1-24]): \n";
            System.out.print(playerDirectionsString);
        }
    }

    public static boolean winConditions() {

        if (gameBoardIsFull()) {
            if (horizontalWinPlayer1()) {
                return true;
            }
            if (horizontalWinPlayer2()) {
                return true;
            }
            if (verticalWinPlayer1()) {
                return true;
            }
            if (verticalWinPlayer2()) {
                return true;
            }
            if (diagonalWinLeftPlayer1()) {
                return true;
            }
            if (diagonalWinRightPlayer1()) {
                return true;
            }
            if (diagonalWinLeftPlayer2()) {
                return true;
            }
            if (diagonalWinRightPlayer2()) {
                return true;
            }
            if (drawGame()) {
                return true;
            }
        }
        return false;
    }

    public static boolean gameBoardIsFull() {
        for(int i = 0; i < gameBoard.length; i++) {
            for(int j = 0; j < gameBoard[i].length; j++) {
                if(gameBoard[i][j] == 3) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean horizontalWinPlayer1() {
        int numberOfGameBoardRows = 24;
        int numberOfGameBoardColumns = 24;
        int numberToWin = 24;

        for (int row = 0; row < numberOfGameBoardRows; row++) {
            if (gameBoard[row][0] == 1) {

                int column;
                for (column = 1; column < numberOfGameBoardColumns; column++) {
                    if (gameBoard[row][column] != 1) {
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

    public static boolean verticalWinPlayer1() {
        int numberOfGameBoardRows = 24;
        int numberOfGameBoardColumns = 24;
        int numberToWin = 24;

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

    public static boolean horizontalWinPlayer2() {
        int numberOfGameBoardRows = 24;
        int numberOfGameBoardColumns = 24;
        int numberToWin = 24;

        for (int row = 0; row < numberOfGameBoardRows; row++) {
            if (gameBoard[row][0] == 0) {

                int column;
                for (column = 1; column < numberOfGameBoardColumns; column++) {
                    if (gameBoard[row][column] != 0) {
                        break;
                    }
                }
                if (column == numberToWin) {
                    setWinningPlayer(0);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean verticalWinPlayer2() {
        int numberOfGameBoardRows = 24;
        int numberOfGameBoardColumns = 24;
        int numberToWin = 24;

        for (int row = 0; row < numberOfGameBoardRows; row++) {
            if (gameBoard[0][row] == 0) {

                int column;
                for (column = 1; column < numberOfGameBoardColumns; column++) {
                    if (gameBoard[column][row] != 0) {
                        break;
                    }
                }
                if (column == numberToWin) {
                    setWinningPlayer(0);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean diagonalWinRightPlayer1() {
        int numberOfGameBoardRows = 24;
        int numberOfGameBoardColumns = 24;
        int numberToWin = 24;
        int countToWin = 0;

        for (int row = 0; row < (numberOfGameBoardRows - (numberToWin - 1)); row++) {
            for (int column = 0; column < numberOfGameBoardColumns; column++) {
                for (int diagonalUnit = row; diagonalUnit < numberOfGameBoardRows; diagonalUnit++) {

                    if (gameBoard[diagonalUnit][diagonalUnit] == 3) {
                        setWinningPlayer(3);
                    }
                    else if (gameBoard[diagonalUnit][diagonalUnit] == 1) {
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

    public static boolean diagonalWinLeftPlayer1() {
        int numberOfGameBoardRows = 24;
        int numberOfGameBoardColumns = 24;
        int numberToWin = 24;
        int countToWin = 0;

        for (int row = 0; row < (numberOfGameBoardRows - (numberToWin - 1)); row++) {
            for (int column = 0; column < numberOfGameBoardColumns; column++) {
                for (int diagonalUnit = row; diagonalUnit < numberOfGameBoardRows; diagonalUnit++) {

                    if (gameBoard[diagonalUnit][numberOfGameBoardRows - 1 - diagonalUnit] == 3) {
                        setWinningPlayer(3);
                    }
                    else if (gameBoard[diagonalUnit][numberOfGameBoardRows - 1 - diagonalUnit] == 1) {
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

    public static boolean diagonalWinRightPlayer2() {
        int numberOfGameBoardRows = 24;
        int numberOfGameBoardColumns = 24;
        int numberToWin = 24;
        int countToWin = 0;

        for (int row = 0; row < (numberOfGameBoardRows - (numberToWin - 1)); row++) {
            for (int column = 0; column < numberOfGameBoardColumns; column++) {
                for (int diagonalUnit = row; diagonalUnit < numberOfGameBoardRows; diagonalUnit++) {

                    if (gameBoard[diagonalUnit][diagonalUnit] == 3) {
                        setWinningPlayer(3);
                    }
                    else if (gameBoard[diagonalUnit][diagonalUnit] == 0) {
                        countToWin++;

                        if (countToWin >= numberToWin) {
                            setWinningPlayer(0);
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public static boolean diagonalWinLeftPlayer2() {
        int numberOfGameBoardRows = 24;
        int numberOfGameBoardColumns = 24;
        int numberToWin = 24;
        int countToWin = 0;

        for (int row = 0; row < (numberOfGameBoardRows - (numberToWin - 1)); row++) {
            for (int column = 0; column < numberOfGameBoardColumns; column++) {
                for (int diagonalUnit = row; diagonalUnit < numberOfGameBoardRows; diagonalUnit++) {

                    if (gameBoard[diagonalUnit][numberOfGameBoardRows - 1 - diagonalUnit] == 3) {
                        setWinningPlayer(3);
                    }
                    else if (gameBoard[diagonalUnit][numberOfGameBoardRows - 1 - diagonalUnit] == 0) {
                        countToWin++;

                        if (countToWin >= numberToWin) {
                            setWinningPlayer(0);
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public static boolean drawGame() {
        if (gameBoardIsFull()
                && !horizontalWinPlayer1() && !horizontalWinPlayer2()
                && !verticalWinPlayer1() && !verticalWinPlayer2()
                && !diagonalWinLeftPlayer1() && !diagonalWinRightPlayer1()
                && !diagonalWinLeftPlayer2() && !diagonalWinRightPlayer2()) {
            setWinningPlayer(3);
            return true;
        }

        return false;
    }



































}

