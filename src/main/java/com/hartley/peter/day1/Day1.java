package com.hartley.peter.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Day1 {

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
        String cleanedString = value.replaceAll("[A-Za-z]+", "");
        return Integer.parseInt("" + (cleanedString.charAt(0)) + (cleanedString.charAt(cleanedString.length() - 1)));
    }

}
