package gr.aueb.cf.xmas.project4;


import java.util.Scanner;


/**
 * This app uses an array of characters to simulate the
 * game of tic-tac-toe between two players through user
 * input.
 */

public class Triliza {

    public static final char X = 'X';
    public static final char O = 'O';
    public static char whoIsPlaying ;
    public static char[][] gameBoard = new char[3][3];
    public static char[][] carbonBoard = new char[3][3];
    public static boolean used[] = {false, false, false, false, false, false, false, false, false};
    public static final int[][] positions = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    public static int exit = -1;
    public static int iterations = 0;


    public static void main(String[] args) {


        int choice = 0;
        boolean go = true;

        startGame();//Game On!

        while (!(exit == 1) && (iterations <9)) {

            whoIsPlaying = newRound();
            do{  
                gameStatus(carbonBoard);
                choice = choiceOfPlace();
                go = choiceCheck(choice);
            }while(!go);
            used[choice - 1] = true ;
            gameBoard = playerMove(gameBoard,choice,whoIsPlaying);
            carbonBoard =  playerMove(carbonBoard,choice,whoIsPlaying);
            exit = endOfGame(gameBoard);
            iterations++;

        }
        System.out.println();  // just for pretty printing to the console.
        finalStatus(carbonBoard);
        System.out.println();   // just for pretty printing to the console.

        switch(exit) {
            case 1:
                System.out.printf("Tic-Tac-Toe! Player '%s' won!! ", whoIsPlaying);
                break;
            default: System.out.print("It's a tie!");
        }


    }



    /**
     * This method initializes a new tic-tac-toe game board.
     * We keep two arrays at the same time, one to display the players'
     * game status, and one to actually keep track of the
     * winning condition as the cell comparison method we use below
     * wouldn't work with cells filled with dashes - so in the @carbonBoard,
     * we opt to use numbers instead.
     */


    public static void startGame() {
        int count = 1;
        for( int i = 0 ; i < 3 ; i++) {
            for( int j = 0; j < 3; j++){
                gameBoard[i][j] = (char)(count + '0');
                carbonBoard[i][j] = '-';
                count++;
            }
        }

    }

    /**
     *  Takes a board as a parameter and prints it
     *  for the user to see. Also prints a numpad next to it
     *  to remind the player of their choices.
     * @param board
     */

    public static void gameStatus(char[][] board) {

        System.out.println();  // just for pretty printing to the console.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print((board[i][j] + " "));
            }
            System.out.print("     ");
            for (int j = 0; j < 3; j++) {
                System.out.print((positions[i][j] + " "));
            }
            System.out.println();

        }
    }

    /**
     * Similar to the method above. The difference here is
     * we don't print a num pad, as the game is over.
     */

    public static void finalStatus(char[][] board) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print((board[i][j] + " "));
            }
            System.out.println();
        }

    }

    /**    This one takes the player's move and reconfigures the game board.
     *
     * @param board
     * @param choice
     * @param whoIsPlaying
     * @return
     */

    public static char[][] playerMove(char[][] board, int choice, char whoIsPlaying ) {


        switch(choice){
            case 1:
            case 2:
            case 3:
                    board[0][choice -1] = whoIsPlaying;
                    break;

            case 4:
            case 5:
            case 6:
                board[1][choice -4] = whoIsPlaying;
                break;

            case 7:
            case 8:
            case 9:
                board[2][choice - 7] = whoIsPlaying;
                break;


        }
        return board;


    }


    /**  Since the initialization of the game differs,
     *   we offer the players the option to choose between
     *   X and O.
     *
     * @return   the first player's letter of choice
     */

    public static char roundOne() {

        Scanner in = new Scanner(System.in);

        System.out.println("Who plays first? Choose between X and O");
        whoIsPlaying = in.next().charAt(0);
        while(whoIsPlaying!= X && whoIsPlaying!= O) {
            System.out.println("Na-ah! It's gotta be a capital X or a capital O!");
            whoIsPlaying = in.next().charAt(0);
        }
        return whoIsPlaying;

    }


    /**    Reads the player's choice of placement for
     *     their next move.
     *
     * @return   an integer with the position of their move for this turn.
     */

    public static int choiceOfPlace() {

        Scanner in = new Scanner(System.in);
        int choice = -1;

        System.out.println();
        System.out.println("Choose your next move! Look at the number pad next to the game board and type the number you want" +
                " to place an " + whoIsPlaying + " to that spot!");

        while(!in.hasNextInt()) {
            System.out.println("That's not a position xD. Try again with a number.");
            System.out.println();  // just for pretty printing to the console.
            in.next();
            gameStatus(carbonBoard);
        }
        choice = in.nextInt();
        in.nextLine();

        return choice;

    }

    /** Checks everyone is playing within the scope of the game!
     *
     * @param choice    the player's current placement
     */

    public static boolean choiceCheck(int choice){

        boolean go = true;

        String usedUp = "Place already used-up! Use something available:)";
        String outOfBounds = "Hey! Don't cheat! This is a 3X3 Tic-Tac-Toe game :)" ;

        if( choice < 1 || choice > 9 ) {
            System.out.println(outOfBounds);
            System.out.println();
            go = false;
            return go;
        }

        if(used[choice-1]) {
                System.out.println(usedUp);
                System.out.println();
                go = false;
            }

        return go;
    }

    /**      Initiates a new round. Calls a different
     *       method for the very first round!
     *
     * @return     the next player( X or O)
     */

    public static char newRound() {
        if (exit == -1)  {
            whoIsPlaying =  roundOne();
            return whoIsPlaying;
        }
        whoIsPlaying = (whoIsPlaying == X) ? O : X;
        System.out.println();
        System.out.printf("Next player's turn: %s \n", whoIsPlaying);
        return  whoIsPlaying;
    }


    /**
     * Checks for a series of three consecutive Xs or Os
     * diagonally, vertically and horizontally
     *
     * @param gameBoard      the game board
     * @return               a boolean which signals the end of the game when true
     */

    public static int endOfGame(char[][] gameBoard) {

        boolean endOfGame = false;

        char temp1 = gameBoard[0][0];
        char temp2 = gameBoard[0][2];
        char temp3 = gameBoard[1][0];
        char temp4 = gameBoard[0][1];

        int count[] = {0, 0, 0, 0, 0, 0};

        for(int v = 0 ; v < 3; v++){
            count[0] += (temp1== gameBoard[0][v]) ? 1 :-1;
            count[1] += (temp1 == gameBoard[v][0]) ? 1 :-1;
            count[2] += (temp1 == gameBoard[v][v]) ? 1 :-1;
            count[3] += (temp2 == gameBoard[v][2 - v]) ? 1 :-1;
            count[4] += (temp3 == gameBoard[1][v] ) ? 1 :-1;
            count[5] += (temp4 == gameBoard[v][1]) ? 1: -1;
            }
        for( int c:count){
                if(c == 3) {endOfGame = true;}
        }
        return (endOfGame)? 1  : 0;
    }


}
