package com.example.kata.fizzbuzz;

import java.io.PrintStream;
import java.util.stream.IntStream;

class FizzBuzz {
    String report(int turn) {
        if (isFizz(turn) && isBuzz(turn)) return "fizzbuzz";
        if (isBuzz(turn)) return "buzz";
        if (isFizz(turn)) return "fizz";
        return String.valueOf(turn);
    }

    void reportAll(PrintStream output) {
        IntStream.range(1, 101).mapToObj(this::report).forEach(output::println);
    }

    private boolean isBuzz(int turn) {
        return turn % 5 == 0 || turn % 10 == 5 || turn / 10 == 5;
    }

    private boolean isFizz(int turn) {
        return turn % 3 == 0 || turn % 10 == 3 || turn / 10 == 3;
    }
}