package practice.methods.divideAndConque;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class NearestPointPairs {
	static final int MOUNT=30;
	public static void main(String[] args) {
		Set<Point> testData = new TreeSet<Point>();
        
        Random random = new Random();
          
        int x = 0;  
        int y = 0;  
          
        for(int i = 0;i < 100000;i++){  
            x = random.nextInt(500000);  
            y = random.nextInt(500000);  
            testData.add(new Point(x, y));  
        }  
          
        Point [] points = new Point[testData.size()];  
        points = (Point[]) testData.toArray(points);            
        System.out.println(testData.size());  
		Point[] resultpPoints=new Point[2];
		long startTime=System.currentTimeMillis();   //获取开始时间
		//resultpPoints=boundary(points);
		resultpPoints=findNearest(points);
		long endTime=System.currentTimeMillis(); //获取结束时间
		System.out.println("the nearest point pairs is: ("+resultpPoints[0].getX()+","+resultpPoints[0].getY()
							+")--("+resultpPoints[1].getX()+","+resultpPoints[1].getY()+")");
		System.out.println("their distance is: "+distance(resultpPoints[0], resultpPoints[1]));
		System.out.println("time cost of ctAlgorithm is： "+(endTime-startTime)+"ms");
		startTime=System.currentTimeMillis();   //获取开始时间
		resultpPoints=boundary(points);
		//resultpPoints=findNearest(points);
		 endTime=System.currentTimeMillis(); //获取结束时间
		System.out.println("time cost of nomorl alogtithm is： "+(endTime-startTime)+"ms");
	}
	
	static Point[] findNearest(Point[] p){
		Point[] result=new Point[2];
		//如果小于某一边界条件则用暴力穷举计算
		if (p.length<=MOUNT) {
		 	return boundary(p);
		}
		//分治法核心
		//step1.求所有点在X坐标的中位数  
        int minX = (int) Double.POSITIVE_INFINITY;      //保证假设的初始最小值足够大  
        int maxX = (int) Double.NEGATIVE_INFINITY;      //保证假设的初始最大值足够小 
        for(int i = 0; i < p.length; i++){  
            if(p[i].getX() < minX)  
                minX = (int) p[i].getX();  
            if(p[i].getX() > maxX)  
                maxX = (int) p[i].getX();  
        }  
        int midX = (minX + maxX)/2;
        //step2.以midX为界将所有点分成两组分别存放在两个表中
        ArrayList<Point> T1 = new ArrayList<Point>();
        ArrayList<Point> T2 = new ArrayList<Point>();  
        for(int i = 0; i < p.length; i++){  
            if(p[i].getX() <= midX)       
                T1.add(p[i]);  
            if(p[i].getX() > midX)  
                T2.add(p[i]);  
        }  
        //step3.将两张表转化为数组类型，并分别按X坐标升序排列
        Point [] p1 = new Point[T1.size()];  
        Point [] p2 = new Point[T2.size()];  
          
        T1.toArray(p1);  
        T2.toArray(p2);  
          
        mergeSort(p1, "x");     //按X坐标升序排列  
        mergeSort(p2, "x");     //按X坐标升序排列 
        //step4.求p1、p2中的最近距离的两个点
        Point[] result1 = new Point[2];  
        result1 = findNearest(p1);
        Point[] result2 = new Point[2];  
        result2 = findNearest(p2);
        //step5.求二者中的最小值
        if (distance(result1[0], result1[1])<distance(result2[0], result2[1])) {
			result=result1;
		}else {
			result=result2;
		}
        double d=Math.min(distance(result1[0], result1[1]),distance(result2[0], result2[1]));
        //step6在两个子点集中搜索距离中位线距离小于d的点并存起来
        ArrayList<Point> T3 = new ArrayList<Point>();   
        for(int i = 0; i < p1.length; i++){  
            if(midX - p1[i].getX() < d)  
                T3.add(p1[i]);  
        }  
        for(int i = 0; i < p2.length; i++){  
            if(p2[i].getX() - midX < d){  
                T3.add(p2[i]);  
            }  
        }  
        //step7.将得到的子表转成数组并且按照y的升序排列
        Point [] p3 = new Point [T3.size()];  
        T3.toArray(p3);            
        mergeSort(p3, "y");  
        //step8.根据鸽笼理论，只需检查T3中的每个点的后8个点和d进行比对即可
        if (p3.length<MOUNT) {
			Point[] tempPoints= boundary(p3);
			if (distance(tempPoints[0], tempPoints[1])<d&&distance(tempPoints[0], tempPoints[1])!=0) {
				result=tempPoints;
			}
		}else {
			for(int i=0;i<p3.length-7;i++){
				double tempd;
				for(int j=1;j<8;j++){
					if (i+j>=p3.length) {
						break;
					}else {
						tempd=distance(p3[i], p3[i+j]);
						if (tempd<d&tempd!=0) {
							result[0]=p3[i];
							result[1]=p3[i+j];
						}
					}
					
				}
			}
		}
       
		return result;
	}
	/**
	 * 求两点之间距离
	 * @param p1
	 * @param p2
	 * @return
	 */
	static double distance(Point p1,Point p2){
		return Math.sqrt((p1.getX()-p2.getX())*(p1.getX()-p2.getX())+(p1.getY()-p2.getY())*(p1.getY()-p2.getY()));
	}
	/**
	 * 将点集按照特定顺序升序排列（x坐标或者y坐标）
	 * @param p1
	 * @param string
	 */
	private static void mergeSort(Point[] p, String property) {
		// TODO 自动生成的方法存根
		Point[] tempArray = new Point[p.length];  
        mergeSort(p, tempArray, 0, p.length - 1, property); 
	}
	private static void mergeSort(Point[] a, Point [] tempArray, int left, int right, String property){  
        if(left < right){  
            int center = (left + right) >> 1;  
            //分治  
            mergeSort(a, tempArray, left, center, property);  
            mergeSort(a, tempArray, center + 1, right, property);  
            //合并  
            merge(a, tempArray, left, center + 1, right, property);  
        }  
    } 
	  private static void merge(Point [] a, Point [] tempArray, int leftPos, int rightPos, int rightEnd, String property){  
	        int leftEnd = rightPos - 1;  
	        int numOfElements = rightEnd - leftPos + 1;  
	          
	        int tmpPos = leftPos;       //游标变量, 另两个游标变量分别是leftPos 和 rightPos  
	          
	        while(leftPos <= leftEnd && rightPos <= rightEnd){  
	            if(property.equals("x")){  
	                if(a[leftPos].getX() <= a[rightPos].getX())  
	                    tempArray[tmpPos++] = a[leftPos++];  
	                else  
	                    tempArray[tmpPos++] = a[rightPos++];  
	            }else if(property.equals("y")){  
	                if(a[leftPos].getY() <= a[rightPos].getY())  
	                    tempArray[tmpPos++] = a[leftPos++];  
	                else  
	                    tempArray[tmpPos++] = a[rightPos++];  
	            }else  
	                throw new RuntimeException();  
	        }  
	          
	        while(leftPos <= leftEnd)  
	            tempArray[tmpPos++] = a[leftPos++];  
	        while(rightPos <= rightEnd)  
	            tempArray[tmpPos++] = a[rightPos++];  
	          
	        //将排好序的段落拷贝到原数组中  
	        System.arraycopy(tempArray, rightEnd-numOfElements+1, a, rightEnd-numOfElements+1, numOfElements);  
	    }  
	
	
	/**
	 * 边界和数据较小时候的穷举法求最短距离
	 * @param p 输入点对数组
	 * @return 最小距离
	 */
	static Point[] boundary(Point[] p){
		Point[] resultpPoints=new Point[2];
		if (p.length<=1) {
			resultpPoints[0]=new Point(Double.MIN_VALUE, Double.MIN_VALUE);
			resultpPoints[1]=new Point(Double.MAX_VALUE, Double.MAX_VALUE);
			return resultpPoints;
		}else {
			double min=distance(p[0], p[1]);
			int start=0;
			int end=1;
			for (int i = 0; i < p.length; i++) {
				for (int j = i+1; j < p.length; j++) {
					if (distance(p[i], p[j])<min&&distance(p[i], p[j])!=0) {
						min=distance(p[i], p[j]);
						start=i;
						end=j;
					}
				}
			}
			resultpPoints[0]=p[start];
			resultpPoints[1]=p[end];
			return resultpPoints;
		}
	}
}
//用一个类来表示点
class Point implements  Cloneable,Comparable<Point>{
	double x,y;

	public Point(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	@Override  
    public int compareTo(Point o) {  
        if(x == o.getX() && y == o.getY())  
            return 0;  
        else   
            return 1;  
    }
	
	
	@Override
	public boolean equals(Object p) {
		// TODO 自动生成的方法存根
		if (this.x==((Point) p).getX()&&this.y==((Point) p).getY()) {
			return true;
		}else {
			return false;
		}
	}

//	@Override
//	public int hashCode() {
//		// TODO 自动生成的方法存根
//		return (int) ((x+y)*12345);
//	}

}