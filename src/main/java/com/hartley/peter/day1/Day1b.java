package com.hartley.peter.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Day1b {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = Day1.class.getResourceAsStream("/day1");
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);

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
