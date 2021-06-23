package com.MainPackage;


import java.util.Scanner;

public class Main {
    public static Scanner playerInput = new Scanner(System.in);

    private static int playerCount = 1;

    public static int[][] gameBoard = new int[6][6];

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

            printPlayerDirections(currentPlayer);


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
        int numberOfGameBoardRows = 6;
        int numberOfGameBoardColumns = 6;

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
        int numberOfGameBoardRows = 6;
        int numberOfGameBoardColumns = 6;

        for (int row = 0; row < numberOfGameBoardRows; ++row) {
            for (int column = 0; column < numberOfGameBoardColumns; ++column) {
                printCell(gameBoard[row][column]);
                if (column != numberOfGameBoardColumns - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (row != numberOfGameBoardRows - 1) {
                System.out.println("-----------------------");
            }
        }
        System.out.println();
    }

    public static void clearGameBoard() {
        int numberOfGameBoardRows = 6;
        int numberOfGameBoardColumns = 6;

        for (int row = 0; row < numberOfGameBoardRows; ++row) {
            for (int col = 0; col < numberOfGameBoardColumns; ++col) {
                gameBoard[row][col] = 3;
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

    public static boolean winConditions() {

        //horizontal rows are true for X/1
        if (gameBoard[0][0] == 1 && gameBoard[0][1] == 1 && gameBoard[0][2] == 1 && gameBoard[0][3] == 1 && gameBoard[0][4] == 1 && gameBoard[0][5] == 1
                || gameBoard[1][0] == 1 && gameBoard[1][1] == 1 && gameBoard[1][2] == 1 && gameBoard[1][3] == 1 && gameBoard[1][4] == 1 && gameBoard[1][5] == 1
                || gameBoard[2][0] == 1 && gameBoard[2][1] == 1 && gameBoard[2][2] == 1 && gameBoard[2][3] == 1 && gameBoard[2][4] == 1 && gameBoard[2][5] == 1
                || gameBoard[3][0] == 1 && gameBoard[3][1] == 1 && gameBoard[3][2] == 1 && gameBoard[3][3] == 1 && gameBoard[3][4] == 1 && gameBoard[3][5] == 1
                || gameBoard[4][0] == 1 && gameBoard[4][1] == 1 && gameBoard[4][2] == 1 && gameBoard[4][3] == 1 && gameBoard[4][4] == 1 && gameBoard[4][5] == 1
                || gameBoard[5][0] == 1 && gameBoard[5][1] == 1 && gameBoard[5][2] == 1 && gameBoard[5][3] == 1 && gameBoard[5][4] == 1 && gameBoard[5][5] == 1) {

            setWinningPlayer(1);
            return true;
        }
        //vertical columns are true for X/1
        else if (gameBoard[0][0] == 1 && gameBoard[1][0] == 1 && gameBoard[2][0] == 1 && gameBoard[3][0] == 1 && gameBoard[4][0] == 1 && gameBoard[5][0] == 1
                || gameBoard[0][1] == 1 && gameBoard[1][1] == 1 && gameBoard[2][1] == 1 && gameBoard[3][1] == 1 && gameBoard[4][1] == 1 && gameBoard[5][1] == 1
                || gameBoard[0][2] == 1 && gameBoard[1][2] == 1 && gameBoard[2][2] == 1 && gameBoard[3][2] == 1 && gameBoard[4][2] == 1 && gameBoard[5][2] == 1
                || gameBoard[0][3] == 1 && gameBoard[1][3] == 1 && gameBoard[2][3] == 1 && gameBoard[3][3] == 1 && gameBoard[4][3] == 1 && gameBoard[5][3] == 1
                || gameBoard[0][4] == 1 && gameBoard[1][4] == 1 && gameBoard[2][4] == 1 && gameBoard[3][4] == 1 && gameBoard[4][4] == 1 && gameBoard[5][4] == 1
                || gameBoard[0][5] == 1 && gameBoard[1][5] == 1 && gameBoard[2][5] == 1 && gameBoard[3][5] == 1 && gameBoard[4][5] == 1 && gameBoard[5][5] == 1) {

            setWinningPlayer(1);
            return true;
        }
        //diagonal are true for X/1
        else if (gameBoard[0][0] == 1 && gameBoard[1][1] == 1 && gameBoard[2][2] == 1 && gameBoard[3][3] == 1 && gameBoard[4][4] == 1 && gameBoard[5][5] == 1
                || gameBoard[0][5] == 1 && gameBoard[1][4] == 1 && gameBoard[2][3] == 1 && gameBoard[3][2] == 1 && gameBoard[4][1] == 1 && gameBoard[5][0] == 1) {

            setWinningPlayer(1);
            return true;
        }

        //horizontal rows are true for O/2
        else if (gameBoard[0][0] == 0 && gameBoard[0][1] == 0 && gameBoard[0][2] == 0 && gameBoard[0][3] == 0 && gameBoard[0][4] == 0 && gameBoard[0][5] == 0
                || gameBoard[1][0] == 0 && gameBoard[1][1] == 0 && gameBoard[1][2] == 0 && gameBoard[1][3] == 0 && gameBoard[1][4] == 0 && gameBoard[1][5] == 0
                || gameBoard[2][0] == 0 && gameBoard[2][1] == 0 && gameBoard[2][2] == 0 && gameBoard[2][3] == 0 && gameBoard[2][4] == 0 && gameBoard[2][5] == 0
                || gameBoard[3][0] == 0 && gameBoard[3][1] == 0 && gameBoard[3][2] == 0 && gameBoard[3][3] == 0 && gameBoard[3][4] == 0 && gameBoard[3][5] == 0
                || gameBoard[4][0] == 0 && gameBoard[4][1] == 0 && gameBoard[4][2] == 0 && gameBoard[4][3] == 0 && gameBoard[4][4] == 0 && gameBoard[4][5] == 0
                || gameBoard[5][0] == 0 && gameBoard[5][1] == 0 && gameBoard[5][2] == 0 && gameBoard[5][3] == 0 && gameBoard[5][4] == 0 && gameBoard[5][5] == 0) {

            setWinningPlayer(0);
            return true;
        }
        //vertical columns are true for O/2
        else if (gameBoard[0][0] == 0 && gameBoard[1][0] == 0 && gameBoard[2][0] == 0 && gameBoard[3][0] == 0 && gameBoard[4][0] == 0 && gameBoard[5][0] == 0
                || gameBoard[0][1] == 0 && gameBoard[1][1] == 0 && gameBoard[2][1] == 0 && gameBoard[3][1] == 0 && gameBoard[4][1] == 0 && gameBoard[5][1] == 0
                || gameBoard[0][2] == 0 && gameBoard[1][2] == 0 && gameBoard[2][2] == 0 && gameBoard[3][2] == 0 && gameBoard[4][2] == 0 && gameBoard[5][2] == 0
                || gameBoard[0][3] == 0 && gameBoard[1][3] == 0 && gameBoard[2][3] == 0 && gameBoard[3][3] == 0 && gameBoard[4][3] == 0 && gameBoard[5][3] == 0
                || gameBoard[0][4] == 0 && gameBoard[1][4] == 0 && gameBoard[2][4] == 0 && gameBoard[3][4] == 0 && gameBoard[4][4] == 0 && gameBoard[5][4] == 0
                || gameBoard[0][5] == 0 && gameBoard[1][5] == 0 && gameBoard[2][5] == 0 && gameBoard[3][5] == 0 && gameBoard[4][5] == 0 && gameBoard[5][5] == 0) {

            setWinningPlayer(0);
            return true;
        }
        //diagonal are true for O/2
        else if (gameBoard[0][0] == 0 && gameBoard[1][1] == 0 && gameBoard[2][2] == 0 && gameBoard[3][3] == 0 && gameBoard[4][4] == 0 && gameBoard[5][5] == 0
                || gameBoard[0][5] == 0 && gameBoard[1][4] == 0 && gameBoard[2][3] == 0 && gameBoard[3][2] == 0 && gameBoard[4][1] == 0 && gameBoard[5][0] == 0) {

            setWinningPlayer(0);
            return true;
        }
        else if (gameBoard[0][0] != 3 && gameBoard[0][1] != 3 && gameBoard[0][2] != 3 && gameBoard[0][3] != 3 && gameBoard[0][4] != 3 && gameBoard[0][5] != 3
                && gameBoard[1][0] != 3 && gameBoard[1][1] != 3 && gameBoard[1][2] != 3 && gameBoard[1][3] != 3 && gameBoard[1][4] != 3 && gameBoard[1][5] != 3
                && gameBoard[2][0] != 3 && gameBoard[2][1] != 3 && gameBoard[2][2] != 3 && gameBoard[2][3] != 3 && gameBoard[2][4] != 3 && gameBoard[2][5] != 3
                && gameBoard[3][0] != 3 && gameBoard[3][1] != 3 && gameBoard[3][2] != 3 && gameBoard[3][3] != 3 && gameBoard[3][4] != 3 && gameBoard[3][5] != 3
                && gameBoard[4][0] != 3 && gameBoard[4][1] != 3 && gameBoard[4][2] != 3 && gameBoard[4][3] != 3 && gameBoard[4][4] != 3 && gameBoard[4][5] != 3
                && gameBoard[5][0] != 3 && gameBoard[5][1] != 3 && gameBoard[5][2] != 3 && gameBoard[5][3] != 3 && gameBoard[5][4] != 3 && gameBoard[5][5] != 3
                && gameBoard[0][0] != 3 && gameBoard[1][0] != 3 && gameBoard[2][0] != 3 && gameBoard[3][0] != 3 && gameBoard[4][0] != 3 && gameBoard[5][0] != 3
                && gameBoard[0][1] != 3 && gameBoard[1][1] != 3 && gameBoard[2][1] != 3 && gameBoard[3][1] != 3 && gameBoard[4][1] != 3 && gameBoard[5][1] != 3
                && gameBoard[0][2] != 3 && gameBoard[1][2] != 3 && gameBoard[2][2] != 3 && gameBoard[3][2] != 3 && gameBoard[4][2] != 3 && gameBoard[5][2] != 3
                && gameBoard[0][3] != 3 && gameBoard[1][3] != 3 && gameBoard[2][3] != 3 && gameBoard[3][3] != 3 && gameBoard[4][3] != 3 && gameBoard[5][3] != 3
                && gameBoard[0][4] != 3 && gameBoard[1][4] != 3 && gameBoard[2][4] != 3 && gameBoard[3][4] != 3 && gameBoard[4][4] != 3 && gameBoard[5][4] != 3
                && gameBoard[0][5] != 3 && gameBoard[1][5] != 3 && gameBoard[2][5] != 3 && gameBoard[3][5] != 3 && gameBoard[4][5] != 3 && gameBoard[5][5] != 3) {

            setWinningPlayer(3);
            return true;
        }
        else {
            return false;
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
            playerDirectionsString = "Player 1! Please enter the coordinate of your move (row[1-6] column[1-6]): \n";
            System.out.print(playerDirectionsString);
        }
        else if (currentPlayer == OCell) {
            playerDirectionsString = "Player 2! Please enter the coordinate of your move (row[1-6] column[1-6]): \n";
            System.out.println(playerDirectionsString);
        }
    }

}
