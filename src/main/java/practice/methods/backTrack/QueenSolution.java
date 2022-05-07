package practice.methods.backTrack;

import java.util.Arrays;

/**
 * @author colter
 * 2018/3/25
 */
public class QueenSolution {
    static int[][] board = new int[8][8];
    static int solution = 0;

    public static void main(String[] args) {
        putQueen(0);
    }
    
    //放置第K个皇后
    static void putQueen(int k) {
        int max = board.length;
        //放置第8个，说明棋盘已经放置完毕了，输出结果。
        if (k >= max) {
            //找到一个解，打印出来。
            solution++;
            System.out.println(String.format("===========%s===========", solution));
            for (int[] ints : board) {
                System.out.println(Arrays.toString(ints));
            }
            System.out.println();
        } else {
            /*
             * A：
             * 1. 从第0行，0列开始放置一个皇后
             * 2. 如果可以放置，那么就先将该位置置为1，然后放置下一行
             * 3. 如果全部顺利，那么直到找到一个解
             * 4. 然后0行1列放置一个皇后，找到一个解。以此类推
             *
             * B：
             * 1. 从第0行，0列开始放置一个皇后
             * 2. 如果可以放置，那么就先将该位置置为1，然后放置下一行
             * 3. 如果下一列没有可以放置的位置，那么将刚才放置的位置 置为0
             * （也就是皇后不能放在这里，此时会回溯到上一层循环，重新放置）
             * 例子：
             * k = 6 的时候，假如board[6][0]放置了一个皇后(k=6,i=0)，
             * 那么在接下来执行putQueen(6+1)
             * 假如遍历board[7]之后发现没有位置能够放置一个皇后，
             * 那么会执行board[k][i] = 0 (k=6,i=0)
             * 此时第6行第0列的皇后被拿走了，此时i自增为1
             * 然后执行check(k,i) （k=6,i=1）如果能放置的话，重复上边的动作，
             * 如果不能放置的话继续自增i，放置到第6行的下一列。
             */
            for (int i = 0; i < max; i++) {
                if (check(k, i)) {
                    board[k][i] = 1;
                    putQueen(k + 1);
                    board[k][i] = 0;
                }
            }
        }
    }

    /**
     * 皇后放置的时候是从上到下每一行放置的，所以不用检查改行以及之后的行
     * 所以只用检查列以及左上右上对角线
     *
     * @param row 检查的对应行
     * @param col 检查的对应列
     * @return 返回改点是否满足可以放置一个皇后
     */
    static boolean check(int row, int col) {
        //检查列是否有皇后
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) {
                return false;
            }
        }

        //检查左上对角线是否有皇后
        for (int m = row - 1, n = col - 1; m >= 0 && n >= 0; m--, n--) {
            if (board[m][n] == 1) {
                return false;
            }
        }

        //检查右上对角线是否有皇后
        for (int m = row - 1, n = col + 1; m >= 0 && n < board[0].length; m--, n++) {
            if (board[m][n] == 1) {
                return false;
            }
        }
        return true;

    }
}
