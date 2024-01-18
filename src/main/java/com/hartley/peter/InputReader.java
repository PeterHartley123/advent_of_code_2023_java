package com.hartley.peter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

public class InputReader extends BufferedReader {
    public InputReader(String path) {
        super(
            new InputStreamReader(
                    Objects.requireNonNull(InputReader.class.getResourceAsStream(path))
            )
        );
    }
}
