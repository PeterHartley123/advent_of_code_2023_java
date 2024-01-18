package com.hartley.peter.day3;

import com.hartley.peter.InputReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day3 {

    public static void main(String[] args) throws IOException {
        InputReader reader = new InputReader("/day3");

        List<String[]> matrixCollector = new ArrayList<>();

        for (String line; (line = reader.readLine()) != null;) {
            matrixCollector.add(line.split(""));
        }

        String[][] matrix = matrixCollector.toArray(String[][]::new);

        List<Integer> adjacent = processMatrix(matrix);
        Integer sum = adjacent.stream().reduce(0, Integer::sum);
        System.out.println("Sum = " + sum);

        List<Integer> gears = processMatrixPartb(matrix);
        Integer sumPartB = gears.stream().reduce(0, Integer::sum);
        System.out.println("Sum part b= " + sumPartB);
    }

    private static List<Integer> processMatrix(String[][] matrix) {
        List<Integer> adjacents = new ArrayList<>();
        List<String> used = new ArrayList<>();

        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                String cell = matrix[y][x];
                if(cell.matches("[^A-Za-z0-9.]")) {
                    List<Integer> cells = findTouchingCells(matrix, x, y, used);
                    adjacents.addAll(cells);
                }
            }

        }

        return adjacents;
    }

    private static List<Integer> processMatrixPartb(String[][] matrix) {
        List<Integer> gears = new ArrayList<>();
        List<String> used = new ArrayList<>();

        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                String cell = matrix[y][x];
                if(cell.matches("[*]")) {
                    List<Integer> cells = findTouchingCells(matrix, x, y, used);
                    if(cells.size() == 2) {
                        gears.add(cells.stream().reduce(1, (a, b) -> a * b));
                    }
                }
            }

        }

        return gears;
    }

    public static List<Integer> findTouchingCells(String[][] matrix, int x, int y, List<String> used) {
        List<Integer> numbers = new ArrayList<>();
        Integer[][] coords =
            {{x + 1, y - 1},
            {x + 1, y},
            {x + 1, y + 1},
            {x, y + 1},
            {x - 1, y + 1},
            {x - 1, y},
            {x - 1, y - 1},
            {x, y - 1}};

        for (Integer[] coord: coords) {
            if(used.contains(coord[0] + ":" + coord[1])) {
                continue;
            }

            if(isCoordNumber(matrix, coord)) {
                Integer fullNumber = getFullNumber(matrix, coord, used);
                if(fullNumber != null) {
                    numbers.add(fullNumber);
                }
            }
        }

        return numbers;
    }

    public static boolean isCoordNumber(String[][] matrix, Integer[] coord) {
        return (coord[0] >= 0 && coord[1] >= 0) && (coord[0] < matrix[0].length && coord[1] < matrix.length)
                && matrix[coord[1]][coord[0]].matches("[0-9]");
    }

    public static Integer getFullNumber(String[][] matrix, Integer[] coord, List<String> used) {
        List<String> numberList = new ArrayList<>();
        int i = coord[0];
        while(i >= 0 && matrix[coord[1]][i].matches("[0-9]")) {
            checkIfUsedAndAdd(used, i , coord[1]);

            numberList.add(0, matrix[coord[1]][i]);
            i--;
        }

        i = coord[0] + 1;
        while(i <= matrix.length - 1 && matrix[coord[1]][i].matches("[0-9]")) {
            checkIfUsedAndAdd(used, i , coord[1]);

            numberList.add(matrix[coord[1]][i]);
            i++;
        }

        String number = String.join("", numberList);
        return number.isEmpty() ? null : Integer.valueOf(number);
    }

    private static void checkIfUsedAndAdd(List<String> used, int x, int y) {
        if(!used.contains(x + ":" + y)) {
            used.add(x + ":" + y);
        }
    }

}
