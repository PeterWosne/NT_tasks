package ru.ermakow;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        calculateSteps(args[0]);
    }

    public static void calculateSteps(String filePath) {
        int[] numbers = getArrayFromFile(filePath);

        int average = IntStream.of(numbers).sum() / numbers.length;

        int steps = 0;
        for (int i = 0; i < numbers.length; i++) {
            steps += Math.abs(average - numbers[i]);
        }

        System.out.println(steps + "\n");
    }

    public static int[] getArrayFromFile(String filePath) {
        List<Integer> list = new ArrayList<>();

        try(FileReader fr = new FileReader(new File(filePath));
            BufferedReader reader = new BufferedReader(fr)) {
            String line = reader.readLine();
            while (line != null) {
                list.add(Integer.parseInt(line));
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }

        return array;
    }
}

