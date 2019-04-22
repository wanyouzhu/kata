package com.example.kata.fizzbuzz;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class FizzBuzz {
    List<String> getResult() {
        return IntStream.range(1, 101).mapToObj(this::numberOff).collect(Collectors.toList());
    }

    private String numberOff(int turn) {
        if (isFizzBuzz(turn)) return "fizzbuzz";
        if (isFizz(turn)) return "fizz";
        if (isBuzz(turn)) return "buzz";
        return String.valueOf(turn);
    }

    private boolean isFizzBuzz(int turn) {
        return isFizz(turn) && isBuzz(turn);
    }

    private boolean isBuzz(int turn) {
        return turnNumberIsMultiplesOfNOrContainsN(turn, 5);
    }

    private boolean isFizz(int turn) {
        return turnNumberIsMultiplesOfNOrContainsN(turn, 3);
    }

    private boolean turnNumberIsMultiplesOfNOrContainsN(int turn, int n) {
        return turn / 10 == n || turn % 10 == n || turn % n == 0;
    }
}
