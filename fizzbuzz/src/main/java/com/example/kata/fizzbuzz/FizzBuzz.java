package com.example.kata.fizzbuzz;

class FizzBuzz {
    String report(int turn) {
        if (turn % 3 == 0 && turn % 5 == 0) return "fizzbuzz";
        if (turn % 3 == 0) return "fizz";
        if (turn % 5 == 0) return "buzz";
        return String.valueOf(turn);
    }
}