package practice.topics.stream;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @Author shiLong
 * @Desc reduce
 * @Date 2021/10/6 18:34
 * @Version 1.0
 */
public class ReduceTest {
    public static void main(String[] args) {
        System.out.println(Arrays.asList(2, 3, 5, 7).stream().reduce(Integer::sum).get());
        System.out.println(Stream.of(2, 3, 5, 7).reduce((e1, e2) -> e1 + e2).get());
    }
}
