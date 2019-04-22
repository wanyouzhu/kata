package com.example.kata.fizzbuzz;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class FizzBuzz {
    private List<String> result;

    FizzBuzz() {
        this.result = IntStream.range(1, 101).mapToObj(FizzBuzz::resolve).collect(Collectors.toList());
    }

    private static String resolve(int turn) {
        if (isFizz(turn) && isBuzz(turn)) return "fizzbuzz";
        if (isFizz(turn)) return "fizz";
        if (isBuzz(turn)) return "buzz";
        return String.valueOf(turn);
    }

    private static boolean isFizz(int turn) {
        return isMultiplesOfNOrContainsN(turn, 3);
    }

    private static boolean isBuzz(int turn) {
        return isMultiplesOfNOrContainsN(turn, 5);
    }

    private static boolean isMultiplesOfNOrContainsN(int turn, int n) {
        return turn % n == 0 || turn / 10 == n || turn % 10 == n;
    }

    List<String> getResult() {
        return result;
    }

    String report(int turn) {
        return result.get(turn - 1);
    }
}
