package practice.topics.maths.combinatorics;

/**
 * 拉丁方阵
 */
public class LatinSquare {
    static int i,j,k,temp;
    static void printLatin(int n) {
         k = n + 1;
        // row
        for ( i = 1; i <= n; i++) {
              temp = k;
            while (temp <= n) {
                System.out.print(temp + "  ");
                temp++;
            }
            // 每行后面补 1...k-1
            for ( j = 1; j < k; j++) {
                System.out.print(j + "  ");
            }
            k--;
            System.out.println();
        }
    }
    public static void main(String[] args) {
        printLatin(17);
    }
}