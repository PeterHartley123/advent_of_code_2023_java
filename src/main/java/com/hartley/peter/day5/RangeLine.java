package com.hartley.peter.day5;

import java.util.Arrays;

public class RangeLine {

    private long lower;
    private long upper;

    private long c;

    public RangeLine(long lower, long upper, long c) {
        this.lower = lower;
        this.upper = upper;
        this.c = c;
    }

    public static RangeLine parse(String line) {
        long[] values = Arrays.stream(line.split("\\s+")).mapToLong(Long::parseLong).toArray();

        return new RangeLine(
                values[1],
                values[1] + values[2] - 1,
                values[0] - values[1]);
    }

    public boolean contains(long number) {
        return lower <= number && number <= upper;
    }


    public long getC() {
        return c;
    }

}
