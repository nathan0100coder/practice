package practice.methods.backTrack;

import java.util.*;


public class IslandsCount {
    public static void main(String[] args) {
        char[][] land={
                {'1','1','0','0','0'},
                {'0','1','0','1','1'},
                {'0','0','0','1','1'},
                {'0','0','0','0','0'},
                {'0','0','1','1','1'}

        };
        System.out.println(solve(land));
    }
    /**
     * 判断岛屿数量
     * @param grid char字符型二维数组 
     * @return int整型
     */
    static int solve(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int row = grid.length, columns = grid[0].length, count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < columns; j++) {//遍历所有节点
                if (grid[i][j] == '1') {
                    bfs(grid, i, j, row, columns);
                    count++;//记录岛屿数量
                }
            }
        }
        return count;
    }

    static void bfs(char[][] grid, int i, int j, int row, int columns) {
        Queue<Integer> queue = new LinkedList<>();//队列暂存值为 1 的点
        queue.add(i * columns + j);//暂存该点位置，也可以用一个[i,j]数组表示，不过占用空间也会大一倍
        while (!queue.isEmpty()) {
            int id = queue.remove();//取出位置
            int r = id / columns, c = id % columns;//分解位置得到索引
            if (r - 1 >= 0 && grid[r - 1][c] == '1') {
                queue.add((r - 1) * columns + c);
                grid[r - 1][c] = '0';
            }
            if (r + 1 < row && grid[r + 1][c] == '1') {
                queue.add((r + 1) * columns + c);
                grid[r + 1][c] = '0';
            }
            if (c - 1 >= 0 && grid[r][c - 1] == '1') {
                queue.add(r * columns + c - 1);
                grid[r][c - 1] = '0';
            }
            if (c + 1 < columns && grid[r][c + 1] == '1') {
                queue.add(r * columns + c + 1);
                grid[r][c + 1] = '0';
            }
        }
    }
}