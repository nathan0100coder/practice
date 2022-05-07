package practice.topics.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Nathan Wang
 * @Date 2021/9/22 1:03
 * @Version 1.0
 */
public class FlatMap {
    private static final List<List<String>> lists = new ArrayList<>();
    public static void main(String[] args) {
        testFlatMap();
//        arr_2_list();

    }

    /**
     *
     * flatMap测试
     */
    private static void testFlatMap(){
        List<String> list1 = Arrays.asList("eve", "nathan");
        List<String> list2 = Arrays.asList("ben", "lisa", "cui-hua");
        List<String> list3 = Arrays.asList("gou-dan", "zhu-zi", "tom", "jack");
        lists.add(list1);
        lists.add(list2);
        lists.add(list3);
        lists.stream()
                //flat_map整流  多个list混入一个stream
                .flatMap(e -> e.stream())
                //元素操作
                .map(elm -> elm.substring(0, 2))
                .collect(Collectors.toList())
                //遍历删除每一个元素
                .forEach(System.out::println);
    }
    /**
     *
     * map测试
     */
    private static void arr_2_list(){
        String[] arr={"java", "c#", "c++", "python", "javascript", "goLang"};
        new ArrayList<>(Arrays.asList(arr)).stream().forEach(System.out::println);
        System.out.println("---------------------------------------------");
        List<String> arrayList = new ArrayList<>(arr.length);
        Collections.addAll(arrayList,arr);
        arrayList.forEach(System.out::println);

    }
}
