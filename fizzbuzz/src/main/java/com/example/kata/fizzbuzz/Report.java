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
        if (isBuzz()) return "Buzz";
        if (isFizz()) return "Fizz";
        return String.valueOf(turn);
    }

    private boolean isFizz() {
        return contains(3) || isDividableBy(3);
    }

    private boolean isBuzz() {
        return contains(5) || isDividableBy(5);
    }

    private boolean contains(int n) {
        return turn % 10 == n || turn / 10 == n;
    }

    private boolean isDividableBy(int n) {
        return turn % n == 0;
    }
}
