package practice.topics.arr.matrix;

public class YangHuiTri {
	static int i,j;
	public static void main(String[] args) {
		yangHuiTriangle(5);
	}
	static void yangHuiTriangle(int N) {
		int arr[][] = new int[N][N];
		for (i = 0; i < N; i++) {
			arr[i][0] = 1;
			arr[i][i] = 1;
		}
		for (i = 2; i < N; i++) {
			for (j = 1; j < i; j++)
				arr[i][j] = arr[i - 1][j] + arr[i - 1][j - 1];//CORE
		}
		//print
		for (i = 0; i < N; i++) {
			for (j = 0; j <= i; j++) {
				System.out.printf("%-3d", arr[i][j]);
//                if (j == i) System.out.printf("\n");//等价于下面的控制
			}
			System.out.printf("\n");
		}
	}
}