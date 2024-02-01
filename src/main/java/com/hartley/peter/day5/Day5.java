package com.hartley.peter.day5;

import com.hartley.peter.InputReader;

import java.io.IOException;
import java.util.*;

public class Day5 {

    public static void main(String[] args) throws IOException {
        InputReader reader = new InputReader("/day5");

        List<Long> seeds = new ArrayList<>();
        Category category = new Category();

        for (String line; (line = reader.readLine()) != null; ) {
            if(seeds.isEmpty()) {
                seeds = parseSeeds(line);
                continue;
            }

            if(line.isEmpty()) {
                seeds = processSeedsWithCategory(seeds, category);
                category.clear();
                reader.readLine(); // skip next line
                continue;
            }

            category.add(RangeLine.parse(line));
        }

        // Final process
        seeds = processSeedsWithCategory(seeds, category);

        System.out.printf("Min location: %d", Collections.min(seeds));
    }

    public static List<Long> parseSeeds(String value) {
        return Arrays.stream(value.split(":\\s+")[1].split("\\s+")).map(Long::parseLong).toList();
    }

    private static List<Long> processSeedsWithCategory(List<Long> seeds, Category category) {
        List<Long> newSeeds = new ArrayList<>();
        for (Long seed: seeds) {
            newSeeds.add(seed + category.findIntersect(seed));
        }
        return newSeeds;
    }

}
