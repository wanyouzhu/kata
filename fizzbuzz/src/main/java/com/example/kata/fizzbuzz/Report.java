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
        if (isFizz() && isBuzz()) return "FizzBuzz";
        if (isFizz()) return "Fizz";
        if (isBuzz()) return "Buzz";
        return String.valueOf(turn);
    }

    private boolean isBuzz() {
        return isDividableByOrContains(5);
    }

    private boolean isFizz() {
        return isDividableByOrContains(3);
    }

    private boolean isDividableByOrContains(int n) {
        return contains(n) || isDividableBy(n);
    }

    private boolean contains(int n) {
        return turn % 10 == n || turn / 10 == n;
    }

    private boolean isDividableBy(int n) {
        return turn % n == 0;
    }
}
