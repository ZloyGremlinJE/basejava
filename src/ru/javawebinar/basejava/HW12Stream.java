package ru.javawebinar.basejava;

import java.util.Arrays;

public class HW12Stream {
    public static void main(String[] args) {
        int[] ints = {1,2,3,3,2,3};
        System.out.println(minValue(ints));

    }

    public static int minValue(int[] values) {
        return  Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0,(a,b)->10*a+b);
    }


}
