package com.MainPackage;


import java.util.Scanner;

public class Main {
    public static Scanner playerInput = new Scanner(System.in);

    private static int playerCount = 1;


    public static int[][] gameBoard = new int[3][3];


    private static int winningPlayer;



    public static void main(String[] args) {

        clearGameBoard();

        printWelcomeBanner();

        runGame();

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

    public static boolean validMovement(int row, int column, int currentPlayer) {
        int numberOfGameBoardRows = 3;
        int numberOfGameBoardColumns = 3;

        int emptyCell = 3;

        boolean validInput = false;

        if (row >= 0 && row < numberOfGameBoardRows && column >= 0 && column < numberOfGameBoardColumns && gameBoard[row][column] == emptyCell) {
            gameBoard[row][column] = currentPlayer;
            return true;
        }
        else {
            System.out.println("This move at (" + (row + 1) + "," + (column + 1) + ") is taken.");
        }

        return false;
    }

    public static void printGameBoard() {
        int numberOfGameBoardRows = 3;
        int numberOfGameBoardColumns = 3;

        for (int row = 0; row < numberOfGameBoardRows; ++row) {
            for (int column = 0; column < numberOfGameBoardColumns; ++column) {
                printCell(gameBoard[row][column]);
                if (column != numberOfGameBoardColumns - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (row != numberOfGameBoardRows - 1) {
                System.out.println("-----------");
            }
        }
        System.out.println();
    }

    public static void clearGameBoard() {
        int numberOfGameBoardRows = 3;
        int numberOfGameBoardColumns = 3;

        for (int row = 0; row < numberOfGameBoardRows; ++row) {
            for (int col = 0; col < numberOfGameBoardColumns; ++col) {
                gameBoard[row][col] = 3;
            }
        }
    }

    public static int getCellStatus(int row, int column) {
        return gameBoard[row][column];
    }

    public static void printCell(int gamePiece) {
        final int emptyCell = 3;
        final int XCell = 1;
        final int OCell = 0;


        switch (gamePiece) {
            case emptyCell:  System.out.print("   "); break; //0
            case OCell: System.out.print(" O "); break; //2
            case XCell:  System.out.print(" X "); break; //1
        }
    }

    public static boolean winConditions() {

        //horizontal rows are true for X/1
        if (gameBoard[0][0] == 1 && gameBoard[0][1] == 1 && gameBoard[0][2] == 1
                || gameBoard[1][0] == 1 && gameBoard[1][1] == 1 && gameBoard[1][2] == 1
                || gameBoard[2][0] == 1 && gameBoard[2][1] == 1 && gameBoard[2][2] == 1) {

            winningPlayer = 1;
            return true;
        }
        //vertical columns are true for X/1
        else if (gameBoard[0][1] == 1 && gameBoard[1][1] == 1 && gameBoard[2][1] == 1
                || gameBoard[0][0] == 1 && gameBoard[1][0] == 1 && gameBoard[2][0] == 1
                || gameBoard[0][2] == 1 && gameBoard[1][2] == 1 && gameBoard[2][2] == 1) {

            winningPlayer = 1;
            return true;
        }
        //diagonal are true for X/1
        else if (gameBoard[0][0] == 1 && gameBoard[1][1] == 1 && gameBoard[2][2] == 1
                || gameBoard[0][2] == 1 && gameBoard[1][1] == 1 && gameBoard[2][0] == 1) {

            winningPlayer = 1;
            return true;
        }
        //horizontal rows are true for O/2
        else if (gameBoard[0][0] == 0 && gameBoard[0][1] == 0 && gameBoard[0][2] == 0
                || gameBoard[1][0] == 0 && gameBoard[1][1] == 0 && gameBoard[1][2] == 0
                || gameBoard[2][0] == 0 && gameBoard[2][1] == 0 && gameBoard[2][2] == 0) {

            winningPlayer = 0;
            return true;
        }
        //vertical columns are true for O/2
        else if (gameBoard[0][1] == 0 && gameBoard[1][1] == 0 && gameBoard[2][1] == 0
                || gameBoard[0][0] == 0 && gameBoard[1][0] == 0 && gameBoard[2][0] == 0
                || gameBoard[0][2] == 0 && gameBoard[1][2] == 0 && gameBoard[2][2] == 0) {

            winningPlayer = 0;
            return true;
        }
        //diagonal are true for O/2
        else if (gameBoard[0][0] == 0 && gameBoard[1][1] == 0 && gameBoard[2][2] == 0
                || gameBoard[0][2] == 0 && gameBoard[1][1] == 0 && gameBoard[2][0] == 0) {

            winningPlayer = 0;
            return true;
        }
        else if (gameBoard[0][0] != 3 && gameBoard[0][1] != 3 && gameBoard[0][2] != 3
                && gameBoard[1][0] != 3 && gameBoard[1][1] != 3 && gameBoard[1][2] != 3
                && gameBoard[2][0] != 3 && gameBoard[2][1] != 3 && gameBoard[2][2] != 3) {

            winningPlayer = 3;
            return true;
        }
        else {
            return false;
        }


    }

    public static void printWelcomeBanner() {
        System.out.println("Welcome to a game of Tic-Tac-Toe! (Player 1 is X, and Player 2 is O)");

    }

    public static void printWinningBanner() {
        if (winningPlayer == 1) {
            printGameBoard();
            System.out.println("Player 1 (X) has won! Congrats!");
        } else if (winningPlayer == 0) {
            printGameBoard();
            System.out.println("Player 2 (O) has won! Congrats!");
        } else if (winningPlayer == 3) {
            printGameBoard();
            System.out.println("It's a Draw!");
        }
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
            playerDirectionsString = "Player 1! Please enter the coordinate of your move (row[1-3] column[1-3]): ";
            System.out.println(playerDirectionsString);
        }
        else if (currentPlayer == OCell) {
            playerDirectionsString = "Player 2! Please enter the coordinate of your move (row[1-3] column[1-3]): ";
            System.out.println(playerDirectionsString);
        }
    }






}
