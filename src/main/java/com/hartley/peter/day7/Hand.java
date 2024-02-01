package com.hartley.peter.day7;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Hand {

    public static final String LABELS = "AKQJT98765432";

    private String labels;

    private int bid;
    private Types type;

    public Hand(String labels, int bid) {
        this.labels = labels;
        this.bid = bid;
        this.type = getTypes(labels);
    }

    public int getBid() {
        return bid;
    }

    public int compareHands(Hand otherHand) {
        int ordinalDifference = this.type.ordinal() - otherHand.type.ordinal();
        return ordinalDifference == 0 ? compareLabels(otherHand) : ordinalDifference;
    }

    public int compareLabels(Hand otherHand) {
        String labels = this.labels;
        String otherLabels = otherHand.labels;

        int comparision = 0;

        for (int i = 0; i < labels.length(); i++) {
            int diff = Hand.LABELS.indexOf(labels.charAt(i)) - Hand.LABELS.indexOf(otherLabels.charAt(i));
            if(diff != 0) {
                comparision = diff;
                break;
            }
        }
        return comparision;
    }

    enum Types {
        FIVE_KIND,
        FOUR_KIND,
        FULL_HOUSE,
        THREE_KIND,
        TWO_PAIR,
        ONE_PAIR,
        HIGH
    }

    public Types getTypes(String labels) {
        // Maybe change to distinct chars then string analysis
        List<Long> count = Arrays.stream(labels.split(""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values()
                .stream().toList();

        int labelCount = count.size();
        Types result = null;

        switch (labelCount) {
            case 1 -> result = Types.FIVE_KIND;
            case 2 -> {
                Long firstCount = count.get(0);
                result = (firstCount == 4 || firstCount == 1) ? Types.FOUR_KIND : Types.FULL_HOUSE;
            }
            case 3 -> {
                Long maxCount = Collections.max(count);
                result = maxCount == 3 ? Types.THREE_KIND : Types.TWO_PAIR;
            }
            case 4 -> result = Types.ONE_PAIR;
            case 5 -> result = Types.HIGH;
        }

        return result;
    }
}
