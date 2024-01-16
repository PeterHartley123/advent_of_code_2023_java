package com.hartley.peter.day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Day4b {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = Day4b.class.getResourceAsStream("/day4");
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);

        List<Card> cards = new ArrayList<>();

        for (String line; (line = reader.readLine()) != null;) {
            cards.add(Card.parse(line));
        }

        long total = countCards(cards, 0, cards.size());
        System.out.println("Total = " + total);
    }

    public static long countCards(List<Card> cards, int lowerBouds, int upperBounds) {
        long totalCards = upperBounds - lowerBouds;

        for (int i = lowerBouds; i < upperBounds; i++) {
            Card card = cards.get(i);
            long wins = card.winningNumbers.stream().filter(card.numbers::contains).count();
            int newUpperBounds = Math.min((int) (i + wins + 1), cards.size());

            totalCards += countCards(cards, i+1, newUpperBounds);
        }

        return totalCards;
    }
}
