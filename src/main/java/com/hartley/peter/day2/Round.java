package com.hartley.peter.day2;

import java.util.HashMap;
import java.util.Map;

public class Round extends HashMap<String, Integer> {

    public static Round parse(String roundString) {
        String[] split = roundString.split(",\\s+");
        Round round = new Round();

        for (String s: split) {
            String[] cubes = s.split("\\s+");
            round.put(cubes[1], Integer.parseInt(cubes[0]));
        }

        return round;
    }

    public boolean isRoundLess(Round round) {
        boolean result = true;
        for (Map.Entry<String, Integer> currentEntry: this.entrySet()) {
            if(currentEntry.getValue() < round.getOrDefault(currentEntry.getKey(), 0)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public int getPower() {
        return this.values().stream().reduce(1, (sub, el) -> sub * el);
    }

}
