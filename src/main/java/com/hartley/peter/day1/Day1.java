package com.hartley.peter.day1;

import com.hartley.peter.InputReader;

import java.io.IOException;

public class Day1 {

    public static void main(String[] args) throws IOException {
        InputReader reader = new InputReader("/day1");

        int result = 0;

        for (String line; (line = reader.readLine()) != null;) {
            result += getCalibrationValue(line);;
        }

        System.out.println("Result = " + result);
    }

    private static Integer getCalibrationValue(String value) {
        String cleanedString = value.replaceAll("[A-Za-z]+", "");
        return Integer.parseInt("" + (cleanedString.charAt(0)) + (cleanedString.charAt(cleanedString.length() - 1)));
    }

}
