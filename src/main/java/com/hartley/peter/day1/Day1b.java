package com.hartley.peter.day1;

import com.hartley.peter.InputReader;

import java.io.IOException;

public class Day1b {

    public static void main(String[] args) throws IOException {
        InputReader reader = new InputReader("/day1");

        int result = 0;

        for (String line; (line = reader.readLine()) != null;) {
            result += getCalibrationValue(line);;
        }

        System.out.println("Result = " + result);
    }

    private static Integer getCalibrationValue(String value) {
        String cleanedString = convertCalibrationValue(value);
        return Integer.parseInt("" + (cleanedString.charAt(0)) + (cleanedString.charAt(cleanedString.length() - 1)));
    }

    private static String convertCalibrationValue(String value) {
        String calibration = "";
        String slice = value;

        while(!slice.isEmpty()) {
            calibration += Character.isDigit(slice.charAt(0)) ? slice.charAt(0) : findNumber(slice);
            slice = slice.substring(1);
        }

        return calibration;
    }

    private static final String[] numbers = {
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine"
    };

    private static String findNumber(String value) {
        String number = "";

        for (int i = 0; i < numbers.length; i++) {
            if (value.startsWith(numbers[i])) {
                number = Integer.toString(i + 1);
            }
        }

        return number;
    }



}
