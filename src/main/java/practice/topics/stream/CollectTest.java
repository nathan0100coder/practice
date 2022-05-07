package practice.topics.stream;

import practice.datastruc.obj.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author Nathan Wang
 * @Date 2021/9/22 6:49
 * @Version 1.0
 */
public class CollectTest {
    public static void main(String[] args) {
        collectDemo2();
    }

    private static void collectDemo1() {
        String[] arr = {"eve", "eve", "lisa", "nathan", "cui-hua", "eve", "ben", "ben", "eve"};
        Arrays
                .stream(arr)
                .collect(Collectors.toCollection(HashSet::new))
                .forEach(System.out::println);
    }

    private static void collectDemo2() {
        String[] arr = {"eve", "lisa", "nathan", "cui-hua"};
        List<String> list = Arrays.asList(arr);
        list
                .stream()
                .collect(Collectors.toMap(Function.identity(), String::length))
                .forEach((k, v) -> {
                    System.out.println(k + ":" + v);
                });

    }

    private static void collectDemo3() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("The Fellowship of the Ring", 1954, "0395489318"));
        bookList.add(new Book("The Two Towers", 1954, "0345339711"));
        bookList.add(new Book("The Return of the King", 1955, "0618129111"));
        bookList
                .stream()
                .collect(Collectors.toMap(Function.identity(),Book::getReleaseYear))
                .forEach((k, v) -> {
                    System.out.println(k + ":" + v);
                });
    }
}
