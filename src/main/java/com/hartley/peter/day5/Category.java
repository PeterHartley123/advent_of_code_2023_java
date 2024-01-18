package com.hartley.peter.day5;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Category extends ArrayList<RangeLine> {

    public Long findIntersect(Long seed) {
        Optional<RangeLine> rangeLine = this.stream().filter(c -> c.contains(seed)).findFirst();
        return rangeLine.map(RangeLine::getC).orElse(0L);
    }
}
