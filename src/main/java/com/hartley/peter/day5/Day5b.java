package com.hartley.peter.day5;

import com.hartley.peter.InputReader;

import java.io.IOException;
import java.util.*;
import java.util.stream.LongStream;

public class Day5b {

    public static void main(String[] args) throws IOException {
        InputReader reader = new InputReader("/day5");

        List<Long> seeds = new ArrayList<>();
        Category category = new Category();
        List<Category> categories = new ArrayList<>();

        for (String line; (line = reader.readLine()) != null; ) {
            if(seeds.isEmpty()) {
                seeds = Day5.parseSeeds(line);
                continue;
            }

            if(line.isEmpty()) {
                if(!category.isEmpty()) {
                    categories.add(category);
                    category = new Category();
                }
                reader.readLine(); // skip next line
                continue;
            }

            category.add(RangeLine.parse(line));
        }

        // Add final category
        categories.add(category);
        Long min = getMinLocationSeed(seeds, categories);

        System.out.println("Minimum location: " + min);
    }

    private static Long getMinLocationSeed(List<Long> seeds, List<Category> categories) {
        Long min = null;

        for (int i = 0; i < seeds.size() - 1; i += 2) {
            LongStream stream = LongStream.range(seeds.get(i), seeds.get(i) + seeds.get(i + 1) - 1);
            for (Iterator<Long> iter = stream.iterator(); iter.hasNext(); ) {
                Long seed = iter.next();
                Long location = processSeed(seed, categories);

                min = (min == null)? location : Math.min(location, min);
            }
        }
        return min;
    }

    private static Long processSeed(Long seed, List<Category> categories) {
        Long processed = seed;

        for (Category category: categories) {
            processed = processed + category.findIntersect(processed);
        }

        return processed;
    }
}
