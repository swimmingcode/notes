package org.youyuan.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @Describe:
 * @Author: youjiancheng
 * @Date: 2021/1/27 16:18
 */
public class MapToIntTest {

    public static void main(String[] args) {
        // Creating a list of Strings
        List<String> list = Arrays.asList("3", "6", "8", "14", "15");

        // Using Stream mapToInt(ToIntFunction mapper)
        // and displaying the corresponding IntStream

        list.stream().map(num -> Integer.parseInt(num)).count();
        List<Integer> collect = list.stream().mapToInt(num -> Integer.parseInt(num)).boxed().collect(Collectors.toList());

    }
}
