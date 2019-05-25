package com.example.kata.fizzbuzz;

public class Report {
    private final int turn;

    Report(int turn) {
        this.turn = turn;
    }

    @Override
    public String toString() {
        return turn + " " + tag();
    }

    private String tag() {
        if (isDividableByOrContains(3) && isDividableByOrContains(5)) return "FizzBuzz";
        if (isDividableByOrContains(3)) return "Fizz";
        if (isDividableByOrContains(5)) return "Buzz";
        return String.valueOf(turn);
    }

    private boolean isDividableByOrContains(int i) {
        return contains(i) || isDividableBy(i);
    }

    private boolean contains(int n) {
        return turn % 10 == n || turn / 10 == n;
    }

    private boolean isDividableBy(int i) {
        return turn % i == 0;
    }
}
