package com.example.kata.fizzbuzz;

import java.util.stream.IntStream;

class FizzBuzz {
    ReportList run() {
        return new ReportList(IntStream.range(1, 101).mapToObj(Report::new).toArray(Report[]::new));
    }
}
