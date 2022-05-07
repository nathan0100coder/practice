package practice.topics.maths.calculations;
 
public class Matrix
{
	public static void main(String[] args)
	{ 
		int A[][]={{1,2,3},{4,5,6}};//a矩阵2X3  
		int B[][]={{1,2,3,4},{5,6,7,8},{1,2,3,4}};//b矩阵3X4  
//		int C[M][S];//结果矩阵2X4 
		System.out.println("A=");
		printMatrix(A);
//		System.out.println("}");
		System.out.println("B=");
		printMatrix(B);
//		System.out.println("}");
		System.out.println("C=A*B=");
		int C[][]=mul(A, B);
		printMatrix(C);
//		System.out.println("}");
	}
	static int[][] mul(int A[][],int B[][])
	{
		//矩阵能相乘的条件：A的列数等于B的行数
		for(int i=0;i<A.length;i++)
		{
			if(A[i].length!=B.length)
			{
				System.out.println("不能相乘");
				return null;
			}
		}
		
//		System.out.println("A[0].length="+A[0].length+" B.length"+B.length);
		//生成相乘之后的数组
		int[][] C=new int[A.length][B[0].length];
		
		for(int i=0;i<C.length;i++)//遍历相乘之后的数组的行 ：2,等于A矩阵的行
		{
			for(int j=0;j<C[i].length;j++)//遍历相乘后的数组的列 等于B矩阵的列
			{
				for(int k=0;k<A[0].length;k++)//遍历 A矩阵的列，也是B矩阵的行
				{
					C[i][j]+=A[i][k]*B[k][j];
				}
			}
		}
		return C;
	}
	static void printMatrix(int Matrix[][])
	{
		for(int i=0;i<Matrix.length;i++)
		{
			for(int j=0;j<Matrix[i].length;j++)
			{
				System.out.print("  "+Matrix[i][j]);
			}
			System.out.println();
		}
	}
	// 矩阵乘法
	public static int[][] multiply(int[][] matrix1, int[][] matrix2) {
		int m = matrix1.length;
		int n1 = matrix1[0].length;
		int n2 = matrix2.length;
		if (n1 != n2) {
			return null;
		}
		int s = matrix2[0].length;

		int[][] result = new int[m][s];
		int x = 0;
		int y = 0;
		for (x = 0; x < m; x++) {
			for (y = 0; y < s; y++) {
				int plus = 0;
				for (int count1 = 0; count1 < n1; count1++) {
					plus += matrix1[x][count1] * matrix2[count1][y];
				}
				result[x][y] = plus;
			}
		}

		return result;
	}

	// 求逆矩阵
	public static float[][] InverseMatrix(int[][] data) {
		// 求矩阵的行列式值
		int det = Determination(data);
		// 求伴随矩阵
		int[][] adjoin = Adjointmatrix(data);
		float[][] result = new float[data.length][data[0].length];
		// 求逆
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				result[i][j] = ((float) adjoin[i][j] / (float) det);
			}
		}
		return result;
	}

	// 伴随矩阵方法
	public static int[][] Adjointmatrix(int[][] data) {
		if (data.length != data[0].length) {
			return null;
		}
		if (data.length == 1) {
			return new int[][] { { 1 } };
		}
		int n = data.length;
		int[][] result = new int[n][n];

		// 分别求出data中aij的代数余子式值赋予result中的bji
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// 求出aij的余子式并求值
				int cofactorDet = Determination(Cofactor(data, i, j));
				if ((i + j) % 2 == 1) {
					cofactorDet *= -1;
				}
				// 赋值bji
				result[j][i] = cofactorDet;

			}
		}

		return result;

	}

	// 求余子式的方法
	public static int[][] Cofactor(int[][] data, int i, int j) {
		int m = data.length;
		int n = data[0].length;
		if (m < i || n < j) {
			System.out.println("越界");
			return null;
		}

		int[][] result = new int[m - 1][n - 1];

		for (int row = 0; row < m; row++) {
			if (row == i) {
				// 不做操作
			} else if (row < i) {
				for (int colu = 0; colu < n; colu++) {
					if (colu == j) {
						// 不做操作
					} else if (colu < j) {
						result[row][colu] = data[row][colu];
					} else if (colu > j) {
						result[row][colu - 1] = data[row][colu];
					}
				}
			} else if (row > i) {
				for (int colu = 0; colu < n; colu++) {
					if (colu == j) {
						// 不做操作
					} else if (colu < j) {
						result[row - 1][colu] = data[row][colu];
					} else if (colu > j) {
						result[row - 1][colu - 1] = data[row][colu];
					}
				}
			}

		}

		return result;
	}

	// 矩阵行列式计算
	public static int Determination(int[][] data) {
		int rowcount = data.length;
		int columncount = data[0].length;
		if (rowcount != columncount) {
			return -1;
		}
		if (rowcount == 1) {
			return data[0][0];
		}
		// 创建用于容纳余子式的容器
		int[][] tempMatrix = new int[rowcount - 1][columncount - 1];
		int plus = 0;

		// 确认传入的行列式大于等于2阶，按第一行展开
		for (int i = 0; i < columncount; i++) {

			int a = data[0][i];
			// 通过递归求第一行第i列的余子式的行列式值
			for (int j = 0; j < columncount; j++) {

				// 当i=j时
				if (i == j) {
				}
				// 当i < j的情况
				else if (j < i) {
					for (int c = 0; c < rowcount - 1; c++) {
						tempMatrix[c][j] = data[c + 1][j];
					}
				} else if (j > i) {
					for (int c = 0; c < rowcount - 1; c++) {
						tempMatrix[c][j - 1] = data[c + 1][j];
					}
				}

			}
			// 求代数余子式的-1项
			if (i % 2 == 1) {
				a *= -1;
			}
			int re = Determination(tempMatrix);
			// 累加展开项与代数余子式的乘积
			plus += a * re;
		}

		return plus;

	}
}