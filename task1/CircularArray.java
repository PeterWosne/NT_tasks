package ru.ermakow;

import java.util.Arrays;

public class CircularArray {

    public static void main(String[] args) {

        calculatePath(args[0], args[1]);
    }

    public static void calculatePath(String arg_1, String arg_2) {
        int n = Integer.parseInt(arg_1);
        int m = Integer.parseInt(arg_2);

        int[] arr = new int[n];
        Arrays.setAll(arr, i -> ++i);

        int current = 0;

        do {
            System.out.printf("%d", arr[current]);
            current = (current + m - 1) % n;
        } while (current != 0);
        System.out.println();
    }
}
