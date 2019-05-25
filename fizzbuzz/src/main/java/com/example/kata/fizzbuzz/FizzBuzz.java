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

    private boolean isFizz(int turn) {
        return isDividableBy(turn, 3) || contains(turn, 3);
    }

    private boolean isBuzz(int turn) {
        return isDividableBy(turn, 5) || contains(turn, 5);
    }

    private boolean contains(int turn, int n) {
        return turn / 10 == n || turn % 10 == n;
    }

    private boolean isDividableBy(int turn, int n) {
        return turn % n == 0;
    }
}
