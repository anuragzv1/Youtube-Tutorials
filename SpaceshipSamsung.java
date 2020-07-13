import java.util.Scanner;

class SpaceshipSamsung {

    // this global variable will store our final answer
    public static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // inputting number of rows
        int rows = sc.nextInt();
        int board[][] = new int[rows][5];

        // inputting the matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = sc.nextInt();
            }
        }
        sc.close();
        int isRowSafe = 0;
        getMaxCoins(board, isRowSafe, board.length - 1, 1, false, 0);
        getMaxCoins(board, isRowSafe, board.length - 1, 2, false, 0);
        getMaxCoins(board, isRowSafe, board.length - 1, 3, false, 0);

        System.out.println("Max_coins : " + ans);
    }

    private static void getMaxCoins(int[][] board, int isRowSafe, int cur_row, int cur_col, boolean bombUsed,
            int coins) {
        if (cur_row < 0 || cur_col >= 5 || cur_col < 0) {
            ans = Math.max(ans, coins);
            return;
        }

        //if cell is 1 or zero
        if (board[cur_row][cur_col] == 1 || board[cur_row][cur_col] == 0) {
            int new_coins = coins;
            if (board[cur_row][cur_col] == 1) {
                new_coins++;
            }
            if (bombUsed) {
                isRowSafe--;
            }
            getMaxCoins(board, isRowSafe, cur_row - 1, cur_col, bombUsed, new_coins);
            getMaxCoins(board, isRowSafe, cur_row - 1, cur_col + 1, bombUsed, new_coins);
            getMaxCoins(board, isRowSafe, cur_row - 1, cur_col - 1, bombUsed, new_coins);
        } else if (board[cur_row][cur_col] == 2) {
            if (bombUsed && isRowSafe <= 0) {
                ans = Math.max(ans, coins);
                return;
            } else if (bombUsed && isRowSafe > 0) {
                isRowSafe--;
                getMaxCoins(board, isRowSafe, cur_row - 1, cur_col, bombUsed, coins);
                getMaxCoins(board, isRowSafe, cur_row - 1, cur_col + 1, bombUsed, coins);
                getMaxCoins(board, isRowSafe, cur_row - 1, cur_col - 1, bombUsed, coins);
            } else {
                bombUsed = true;
                isRowSafe = 4;
                getMaxCoins(board, isRowSafe, cur_row - 1, cur_col, bombUsed, coins);
                getMaxCoins(board, isRowSafe, cur_row - 1, cur_col + 1, bombUsed, coins);
                getMaxCoins(board, isRowSafe, cur_row - 1, cur_col - 1, bombUsed, coins);
            }
        }

    }
}
