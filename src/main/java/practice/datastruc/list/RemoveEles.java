package practice.datastruc.list;

import java.util.LinkedList;

/**
 * @author shiLong
 * @version 1.0
 * @desc list移除元素
 * @date 2021/10/21
 */
public class RemoveEles {
    public static void main(String[] args) {
        LinkedList<Integer> integers = new LinkedList<>();
        integers.add(2);
        integers.add(3);
        integers.add(5);
        integers.add(3);
        integers.add(3);
        integers.add(3);
        integers.add(7);
        integers.add(3);
        //remove ele which == 3
        for (int i=0;i<integers.size();i++){
            if (integers.get(i)==3) {
                //先执行remove 再将i索引减1
                integers.remove(i--);
            }
        }
        System.out.println(integers);
    }
}
