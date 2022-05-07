package practice.topics.maths.combinatorics;

import java.util.Scanner;
 
public class NumSplit {
	static int[] a = new int[1000];
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("PLEASE INPUT YOUR NUM TO SPLIT:\n");
		while(in.hasNextInt()) {
			int n = in.nextInt();
			int count = q(n, n, 0);
			System.out.printf("共有%d个解\n",count);
		}
	}
	
	public static int q(int n, int m, int i) {
		if(n < m) {
			return q(n, n, i);
		}
		a[i] = m;
		if(n == 0 || m == 0) {
			//打印下标从0到i
			printPartition(i);
			return 0;
		}
		if(n == 1 || m == 1) {
			if(n == 1) {
				//打印下标从0到i
				printPartition(i);
			}
			else q(n-1, 1, i+1);
			return 1;
		}
		if(n == m) {
			//打印下标从0到i
			printPartition(i);
			return 1 + q(n, n-1, i);
		}
		
		return q(n-m, m, i+1) + q(n, m-1, i);
	}
	
	public static void printPartition(int i) {
		System.out.print("[ ");
		for(int j = 0; j <= i; j++) {
			if(j == i) System.out.print(a[j]);
			else System.out.print(a[j] + "+");
		}
		System.out.println(" ]");
	}
 
}