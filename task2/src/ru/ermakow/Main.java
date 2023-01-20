package ru.ermakow;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Circle circle = getCircle(args[0]);
        List<Point> list = getPoints(args[1]);

        printResults(circle, list);
    }

    public static void printResults(Circle circle, List<Point> list) {
        list.forEach(p -> System.out.printf("%d ", circle.isInside(p)));
    }

    public static Circle getCircle(String pathFile) {
        String line = null;
        String radius = null;
        try(FileReader fr = new FileReader(new File(pathFile));
            BufferedReader reader = new BufferedReader(fr)) {
            line = reader.readLine();
            radius = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] centerCoordinates = line.split(" ");
        return new Circle(Float.parseFloat(centerCoordinates[0]), Float.parseFloat(centerCoordinates[1]), Float.parseFloat(radius));
    }

    public static List<Point> getPoints(String pathFile) {
        List<Point> list = new ArrayList<>();
        try(FileReader fr = new FileReader(new File(pathFile));
            BufferedReader reader = new BufferedReader(fr)) {
            String line = reader.readLine();

            while (line != null) {
                String[] coordinates = line.split(" ");
                list.add(new Point(Float.parseFloat(coordinates[0]), Float.parseFloat(coordinates[1])));
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
