package com.example.kata.fizzbuzz;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FizzBuzz {
    public String print() {
        return IntStream.range(1, 101).mapToObj(this::tag).collect(Collectors.joining("\n"));
    }

    private String tag(int turn) {
        if (isFizz(turn) && isBuzz(turn)) return "FizzBuzz";
        if (isFizz(turn)) return "Fizz";
        if (isBuzz(turn)) return "Buzz";
        return String.valueOf(turn);
    }

    private boolean isBuzz(int turn) {
        return isDividableByOrContains(turn, 5);
    }

    private boolean isFizz(int turn) {
        return isDividableByOrContains(turn, 3);
    }

    private boolean isDividableByOrContains(int turn, int i) {
        return contains(turn, i) || isDividableBy(turn, i);
    }

    private boolean contains(int turn, int i) {
        return String.valueOf(turn).contains(String.valueOf(i));
    }

    private boolean isDividableBy(int turn, int i) {
        return turn % i == 0;
    }
}
