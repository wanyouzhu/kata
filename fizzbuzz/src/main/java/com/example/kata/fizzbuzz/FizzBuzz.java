package com.example.kata.fizzbuzz;

class FizzBuzz {
    String report(int turn) {
        if (isFizz(turn) && isBuzz(turn)) return "fizzbuzz";
        if (isFizz(turn)) return "fizz";
        if (isBuzz(turn)) return "buzz";
        return String.valueOf(turn);
    }

    private boolean isBuzz(int turn) {
        return isMultiplesOfOrContains(turn, 5);
    }

    private boolean isFizz(int turn) {
        return isMultiplesOfOrContains(turn, 3);
    }

    private boolean isMultiplesOfOrContains(int turn, int n) {
        return turn % n == 0 || turn / 10 == n || turn % 10 == n;
    }
}
