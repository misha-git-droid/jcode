package com;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {
        Integer[] arr = {1,2,3,4,5};
        Function<Integer, Integer> adder = value -> value + 1;
        System.out.println(Arrays.stream(arr).toList());
        Integer[] arrs = filter(arr, adder);
        System.out.println(Arrays.stream(arrs).toList());

        String[] list = {"abc", "abcde", "abcdef"};
        Function<String, Integer> convertToLength = value -> value.length();
        System.out.println(Arrays.stream(list).toList());
        Integer[] result = filter(list, convertToLength);
        System.out.println(Arrays.stream(result).toList());
    }

    public static <T,R> R[] filter(T[] arr, Function<T,R> function) {
        R[] array = (R[]) Array.newInstance(
                function.apply(arr[0]).getClass(), arr.length);

        for (int i = 0; i < arr.length; i++) {
            array[i] = function.apply(arr[i]);
        }
        return array;
    }
}

