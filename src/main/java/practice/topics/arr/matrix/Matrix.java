package practice.topics.arr.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shiLong
 * @date 8/12/2021
 * @desc 矩阵操作
 */
public class Matrix {
    public static void main(String[] args){
        int[][] matrix={
                {1,2,3,4,5},
                {8,9,10,11,12},
                {15,16,17,18,19},
                {22,23,24,25,26},
                {29,30,31,32,33},
                {36,37,38,39,40},
                {43,44,45,46,47}
        };
        System.out.println("print_matrix(matrix) = " + spiral_output(matrix));

    }
    static int[][] mul(int[][] x , int[][] y)
    {
        int i , j , k;//循环控制变量
        //申请结果数组的空间
        //行长度为x数组的行长度，列长度为y数组第一行的行长度
        int[][] z=new int[x.length][y[0].length];
        for(i = 0 ; i < x.length ; i++)
        {	for(j = 0 ; j < y[0].length ; j++)
        {
            z[i][j] = 0;
            for(k = 0 ; k < x[0].length ; k++)
                z[i][j] = z[i][j] + x[i][k] * y[k][j];
        }
        }
        return z;
    }
    static void print(int[][] x){
        int i , j;
        for(i = 0 ; i < x.length ; i++){
            for(j = 0 ; j < x[i].length ; j++)
                System.out.printf("%5d",x[i][j]);
            System.out.println();
        }
    }

    /**
     * 顺时针遍历
     * @param matrix 矩阵
     * @return list
     */
    static List<Integer> traverse_clockwise(int [][] matrix) {
        //可以把一个矩形分成很多个环形，可以发现每个环形的其实位置都是（i，i）CORE
        List<Integer> aList = new ArrayList<>();
        if(matrix == null || matrix.length == 0){
            return aList;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;

        int start=0;
        while(true){
            int rowStart = start;
            int rowEnd = rows-start;//变量控制
            int colStart = start;
            int colEnd = cols-start;

            if(rowStart>=rowEnd || colStart>=colEnd){
                break;
            }

            //接下来分别把一个环的四个部分添加到aList中
            for(int i = colStart;i<colEnd;i++){
                aList.add(matrix[rowStart][i]);
            }
            for(int i = rowStart+1;i<rowEnd;i++){
                aList.add(matrix[i][colEnd-1]);
            }

            if((rowEnd-rowStart )>1){
                for(int i = colEnd-2 ;i>=colStart;i--){
                    aList.add(matrix[rowEnd-1][i]);
                }
            }
            if((colEnd-colStart )>1){
                for(int i = rowEnd-2;i>rowStart;i--){
                    aList.add(matrix[i][colStart]);
                }
            }

            start++;//
        }
        return aList;
    }
    static List<Integer> spiral_output(int[][] matrix) {
        List<Integer> order = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length, columns = matrix[0].length;
        boolean[][] visited = new boolean[rows][columns];
        int total = rows * columns;
        int row = 0, column = 0;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIndex = 0;
        for (int i = 0; i < total; i++) {
            order.add(matrix[row][column]);
            visited[row][column] = true;
            int nextRow = row + directions[directionIndex][0], nextColumn = column + directions[directionIndex][1];
            if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns || visited[nextRow][nextColumn]) {
                directionIndex = (directionIndex + 1) % 4;
            }
            row += directions[directionIndex][0];
            column += directions[directionIndex][1];
        }
        return order;
    }
    static void matrix_traverse(char[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        //matrix[i][j]
        for (char[] chars : matrix) {
            for (int j = 0; j < col; j++) {
                System.out.println(chars[j]);
            }
        }
    }
    static void matrix_transpose(char[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j!=i && j < col; j++) {
                matrix[i][j]^=matrix[j][i];
                matrix[j][i]^=matrix[i][j];
                matrix[i][j]^=matrix[j][i];
            }
        }
    }

}
