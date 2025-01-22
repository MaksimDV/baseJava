package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainStreams {
    public static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (acc, num) -> acc * 10 + num);
    }

    public static List<Integer> oddOrEven(List<Integer> integers) {

        Map<Boolean, List<Integer>> partitioned = integers.stream()
                .collect(Collectors.partitioningBy(num -> num % 2 == 0));

        return partitioned.get(partitioned.get(false).size() % 2 != 0);
    }

    public static void main(String[] args) {
        int[] array1 = {1,2,3,3,2,3};
        int[] array2 = {9,8};
        List<Integer> list1 = new ArrayList<>(List.of(1, 1, 1, 2, 2, 1));
        List<Integer> list2 = new ArrayList<>(List.of(1, 1, 1, 2, 2));


        System.out.println(minValue(array1));
        System.out.println(minValue(array2));
        System.out.println(oddOrEven(list1));
        System.out.println(oddOrEven(list2));
    }
}
