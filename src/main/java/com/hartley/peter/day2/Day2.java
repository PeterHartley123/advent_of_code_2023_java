package com.hartley.peter.day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Day2 {

    private static final Round bag = new Round() {
        {
            put("red", 12);
            put("green", 13);
            put("blue", 14);
        }
    };

    public static void main(String[] args) throws IOException {
        InputStream inputStream = Day2.class.getResourceAsStream("/day2");
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);

        int part1Result = 0;
        int part2Result = 0;

        for (String line; (line = reader.readLine()) != null;) {
            Game game = Game.parse(line);

            Game maxGame = game.getMaxGame();
            boolean isGameValid = bag.isRoundLess(maxGame.getRounds().get(0));

            if(isGameValid) {
                part1Result += maxGame.getID();
            }

            int roundPower = maxGame.getRounds().get(0).getPower();
            part2Result += roundPower;
        }

        System.out.println("Part 1 result = " + part1Result);
        System.out.println("Part 2 result = " + part2Result);
    }
}
