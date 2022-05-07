package practice.topics.maths.calculations.polynomial;
/**
 * 用线性表实现一元多项式的加法运算以及多项式的输出
 * @author asus
 */
public class PolyItemDemo {
public static void main(String[] args) {
	PloyItemList p1=new PloyItemList(4);
	p1.add(3, 3);p1.add(2, 2);p1.add(1, 1);p1.add(1);
	System.out.println("第一个多项式(合并同类项)："+p1);
	p1.add(4, 4);
	p1.add(15);
	p1.add(3, 2);
	System.out.println(p1);
	System.out.println("多项式的项数为(合并同类项)："+p1.size());
	PloyItemList p2=new PloyItemList();
	p2.add(6,4);p2.add(4,4);p2.add(3,2);p2.add(1,0);
	System.out.println(p2);
	p2.add(19);
	System.out.println(p2);
	System.out.println("多项式的项数为："+p2.size());
	System.out.println("多项式的相加：");
	p1.addPloyItem(p2);
	System.out.println("相加的结果为："+p1);
}
 
}