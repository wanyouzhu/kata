package com.example.kata.fizzbuzz;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

class FizzBuzz {
    String print() {
        return IntStream.range(1, 101).mapToObj(this::tag).collect(Collectors.joining("\n"));
    }

    private String tag(int turn) {
        if (isFizz(turn) && isBuzz(turn)) return "FizzBuzz";
        if (isFizz(turn)) return "Fizz";
        if (isBuzz(turn)) return "Buzz";
        return String.valueOf(turn);
    }

    private boolean isFizz(int turn) {
        return isDividableByOrContains(turn, 3);
    }

    private boolean isBuzz(int turn) {
        return isDividableByOrContains(turn, 5);
    }

    private boolean isDividableByOrContains(int turn, int n) {
        return contains(turn, n) || isDividableBy(turn, n);
    }

    private boolean contains(int turn, int n) {
        return turn / 10 == n || turn % 10 == n;
    }

    private boolean isDividableBy(int turn, int n) {
        return turn % n == 0;
    }
}
