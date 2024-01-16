package com.hartley.peter.day4;

import com.hartley.peter.day3.Day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day4 {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = Day4.class.getResourceAsStream("/day4");
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);

        long points = 0;

        for (String line; (line = reader.readLine()) != null;) {
            Card card = Card.parse(line);

            long wins = card.winningNumbers.stream().filter(card.numbers::contains).count();

            if(wins > 0) {
                points += Math.pow(2, (wins - 1));
            }
        }

        System.out.println("Total points = " + points);
    }
}
