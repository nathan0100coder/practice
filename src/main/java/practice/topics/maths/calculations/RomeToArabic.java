package practice.topics.maths.calculations;


import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 *     I  1
 *     V  5
 *     X  10
 *     L  50
 *     C  100
 *     D  500
 *     M  1000
 * 请编写程序，由用户输入若干个罗马数字串，程序输出对应的十进制表示。
 * 
 * 输入格式是：第一行是整数n,表示接下来有n个罗马数字(n<100)。以后每行一个罗马数字。罗马数字大小不超过999。
 * 
 * 要求程序输出n行，就是罗马数字对应的十进制数据。
 * 
 * 例如，用户输入： 3 LXXX XCIII DCCII
 * 
 * 则程序应该输出： 80 93 702
 * 
 * @author Administrator
 **/
public class RomeToArabic {
 
	public static void main(String[] args) {
		List<String> romans=new ArrayList<>();
		for (int i=1;i<100;i++){
			romans.add(a2r(i));
		}
		romans.forEach(System.out::println);
	}

	public static int r2a(String m) {
		int graph[] = new int[400];
		graph['I'] = 1;
		graph['V'] = 5;
		graph['X'] = 10;
		graph['L'] = 50;
		graph['C'] = 100;
		graph['D'] = 500;
		graph['M'] = 1000;
		char[] num = m.toCharArray();
		int sum = graph[num[0]];
		for (int i = 0; i < num.length - 1; i++) {
			if (graph[num[i]] >= graph[num[i + 1]]) {
				sum += graph[num[i + 1]];
			} else {
				sum = sum + graph[num[i + 1]] - 2 * graph[num[i]];
			}
		}
		return sum;
	}

	public static String a2r(int number) {
		String rNumber = "";
		int[] aArray = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		String[] rArray = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X",
				"IX", "V", "IV", "I" };
		if (number < 1 || number > 3999) {
			rNumber = "-1";
		} else {
			for (int i = 0; i < aArray.length; i++) {
				while (number >= aArray[i]) {
					rNumber += rArray[i];
					number -= aArray[i];
				}
			}
		}
		return rNumber;
	}
}