package com.hartley.peter.day7;

import com.hartley.peter.InputReader;

import java.io.IOException;
import java.util.*;

public class Day7 {

    public static void main(String[] args) throws IOException {
        InputReader reader = new InputReader("/day7");

        List<Hand> hands = new ArrayList<>();

        for (String line; (line = reader.readLine()) != null; ) {
            String[] split = line.split("\\s+");
            Hand currentHand = new Hand(split[0], Integer.parseInt(split[1]));
            hands.add(currentHand);
        }

        hands.sort(Hand::compareHands);
        int totalWinnings = 0;

        for (int i = 0; i < hands.size(); i++) {
            totalWinnings += (hands.get(i).getBid() * (hands.size() - i));
        }

        System.out.println(totalWinnings);
    }

}
