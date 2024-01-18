package com.hartley.peter.day6;

import com.hartley.peter.InputReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Day6 {

    public static void main(String[] args) throws IOException {
        InputReader reader = new InputReader("/day6");

        int[] times = parseLine(reader.readLine());
        int[] distances = parseLine(reader.readLine());

        long aggregatedSolutionCount = 1;

        for (int i = 0; i < times.length; i++) {
            int time = times[i];
            int distance = distances[i];

            aggregatedSolutionCount *= countSolutions(time, distance);
        }

        System.out.println("Part A result= " + aggregatedSolutionCount);

        long bTime = joinIntArrayToLong(times);
        long bDistance = joinIntArrayToLong(distances);

        long bSolutionCount = countSolutions(bTime, bDistance);

        System.out.println("Part B result= " + bSolutionCount);
    }

    public static int[] parseLine(String line) {
        return Arrays.stream(line.split(":\\s+")[1].split("\\s+")).mapToInt(Integer::parseInt).toArray();
    }

    public static long joinIntArrayToLong(int[] arr) {
        return Long.parseLong(String.join("", Arrays.stream(arr).mapToObj(Integer::toString).toList()));
    }

    public static Long countSolutions(long time, long distance) {
        return LongStream.range(1, time - 1)
                .filter(n -> calculateDistance(time, n) > distance)
                .count();
    }

    public static long calculateDistance(long maxTime, long buttonTime) {
        return (maxTime - buttonTime) * buttonTime;
    }
}
