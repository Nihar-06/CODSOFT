import java.util.Scanner;

public class TicTacToe {
    static char[] board = new char[9]; // 3x3 board
    static char humanPlayer = 'X';
    static char aiPlayer = 'O';

    public static void main(String[] args) {
        initializeBoard();
        printBoard();
        while (true) {
            humanMove();
            printBoard();
            if (checkWinner(humanPlayer)) {
                System.out.println("You win!");
                break;
            }
            if (isBoardFull()) {
                System.out.println("It's a tie!");
                break;
            }

            aiMove();
            printBoard();
            if (checkWinner(aiPlayer)) {
                System.out.println("AI wins!");
                break;
            }
            if (isBoardFull()) {
                System.out.println("It's a tie!");
                break;
            }
        }
    }

    static void initializeBoard() {
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }
    }

    static void printBoard() {
        System.out.println(board[0] + " | " + board[1] + " | " + board[2]);
        System.out.println("---------");
        System.out.println(board[3] + " | " + board[4] + " | " + board[5]);
        System.out.println("---------");
        System.out.println(board[6] + " | " + board[7] + " | " + board[8]);
    }

    static void humanMove() {
        Scanner scanner = new Scanner(System.in);
        int move;
        do {
            System.out.print("Enter your move (1-9): ");
            move = scanner.nextInt() - 1; // Convert to 0-indexed
        } while (move < 0 || move >= 9 || board[move] != ' '); // Validate move
        board[move] = humanPlayer;
    }

    static void aiMove() {
        int bestScore = Integer.MIN_VALUE;
        int move = -1;

        for (int i = 0; i < 9; i++) {
            if (board[i] == ' ') {
                board[i] = aiPlayer;
                int score = minimax(0, false);
                board[i] = ' ';
                if (score > bestScore) {
                    bestScore = score;
                    move = i;
                }
            }
        }
        board[move] = aiPlayer;
    }

    static int minimax(int depth, boolean isMaximizing) {
        if (checkWinner(aiPlayer)) return 1;
        if (checkWinner(humanPlayer)) return -1;
        if (isBoardFull()) return 0;

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board[i] == ' ') {
                    board[i] = aiPlayer;
                    bestScore = Math.max(bestScore, minimax(depth + 1, false));
                    board[i] = ' ';
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board[i] == ' ') {
                    board[i] = humanPlayer;
                    bestScore = Math.min(bestScore, minimax(depth + 1, true));
                    board[i] = ' ';
                }
            }
            return bestScore;
        }
    }

    static boolean checkWinner(char player) {
        int[][] winCombinations = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
        };

        for (int[] combo : winCombinations) {
            if (board[combo[0]] == player && board[combo[1]] == player && board[combo[2]] == player) {
                return true;
            }
        }
        return false;
    }

    static boolean isBoardFull() {
        for (char spot : board) {
            if (spot == ' ') {
                return false;
            }
        }
        return true;
    }
}
