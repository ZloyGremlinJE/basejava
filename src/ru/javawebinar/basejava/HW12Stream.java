package ru.javawebinar.basejava;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HW12Stream {
    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 3, 2, 3};
        List<Integer> integers = Arrays.stream(ints).boxed().collect(Collectors.toList());
        System.out.println("Исходный массив чисел:");
        for (int i = 0; i < ints.length; i++) {
            int anInt = ints[i];
            System.out.print(anInt + " ");
        }
        System.out.println();
        System.out.println("Результат работы int minValue(int[] values): ");
        System.out.println(minValue(ints));

        System.out.println("Результат работы List<Integer> oddOrEven(List<Integer> integers): ");
        List<Integer> result = oddOrEven(integers);

        for (Integer in : result) {
            System.out.print(in + " ");
        }
    }

    public static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (a, b) -> 10 * a + b);
    }

    public static List<Integer> oddOrEven(List<Integer> integers) {
        int sum = integers.stream().mapToInt(Integer::valueOf).sum();
        System.out.println("Сумма массива чисел: " + sum);
        return integers.stream().filter(i -> isOdd(sum) ? isOdd(i) : isEven(i)).collect(Collectors.toList());

    }

    public static boolean isOdd(int i) {
        return i % 2 != 0;
    }

    public static boolean isEven(int i) {
        return i % 2 == 0;
    }

}
