package com.hartley.peter.day4;

import com.hartley.peter.InputReader;

import java.io.IOException;
import java.util.*;

public class Day4b {

    public static void main(String[] args) throws IOException {
        InputReader reader = new InputReader("/day4");

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
