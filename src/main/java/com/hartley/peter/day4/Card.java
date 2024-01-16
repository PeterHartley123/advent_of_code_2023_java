package com.hartley.peter.day4;

import java.util.Arrays;
import java.util.List;

public class Card {

    List<String> winningNumbers;
    List<String> numbers;

    public Card(List<String> winningNumbers, List<String> numbers) {
        this.winningNumbers = winningNumbers;
        this.numbers = numbers;
    }

    public static Card parse(String cardLine) {
        String card = cardLine.split(":\\s+")[1];
        String[] cardHalves = card.split("\\s\\|\\s");

        List<String> winningNumbers = Arrays.stream(cardHalves[0].split("\\s+")).toList();
        List<String> numbers = Arrays.stream(cardHalves[1].split("\\s+")).toList();

        return new Card(winningNumbers, numbers);
    }

}
