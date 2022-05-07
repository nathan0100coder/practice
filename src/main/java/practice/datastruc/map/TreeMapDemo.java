package practice.datastruc.map;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author shiLong
 * @version 1.0
 * @desc treeMap   练习
 * @date 2021/12/23
 */
public class TreeMapDemo {
    public static void main(String[] args) {
        //treeMap  的顺序性
        TreeMap<String, String> map = new TreeMap<>();
        map.put("russia","俄罗斯");
        map.put("china","中国");
        map.put("japan","日本");
        map.put("england","英国");
        map.put("america","美国");
        for (Map.Entry<String, String> next : map.entrySet()) {
            System.out.println("next.getValue() = " + next.getValue());
        }
    }
}
