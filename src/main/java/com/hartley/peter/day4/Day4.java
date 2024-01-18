package com.hartley.peter.day4;

import com.hartley.peter.InputReader;

import java.io.IOException;

public class Day4 {

    public static void main(String[] args) throws IOException {
        InputReader reader = new InputReader("/day4");

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
