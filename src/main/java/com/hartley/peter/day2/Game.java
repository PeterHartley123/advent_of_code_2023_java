package com.hartley.peter.day2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Game {

    private int ID;

    private List<Round> rounds;

    public Game(int ID, List<Round> rounds) {
        this.ID = ID;
        this.rounds = rounds;
    }

    public static Game parse(String gameString) {
        String[] split = gameString.split(":\\s+");

        int id = parseID(split[0]);

        String[] roundSplit = split[1].split(";\\s+");
        List<Round> rounds = Arrays.stream(roundSplit).map(Round::parse).toList();

        return new Game(id, rounds);
    }

    private static int parseID(String idString) {
        String[] split = idString.split("\\s+");
        return Integer.parseInt(split[split.length - 1]);
    }

    public Game getMaxGame() {
        Round round = new Round();
        for (Round r: this.rounds) {
            for (Map.Entry<String, Integer> currentEntry: r.entrySet()) {
                if(round.getOrDefault(currentEntry.getKey(), 0) < currentEntry.getValue()) {
                    round.put(currentEntry.getKey(), currentEntry.getValue());
                }
            }
        }

        List<Round> newList = new ArrayList<>();
        newList.add(round);

        return new Game(this.ID, newList);
    }

    public int getID() {
        return ID;
    }

    public List<Round> getRounds() {
        return rounds;
    }

}
