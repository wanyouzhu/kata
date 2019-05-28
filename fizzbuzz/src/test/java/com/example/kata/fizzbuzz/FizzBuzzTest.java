package com.example.kata.fizzbuzz;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class FizzBuzzTest {
    @Test
    void should_print_100_lines() {
        assertThat(getOutput().length).isEqualTo(100);
    }

    @ParameterizedTest(name = "line content should be {1} for turn {0}")
    @CsvSource({"1,1", "3,Fizz", "5,Buzz", "15,FizzBuzz", "13,Fizz", "52,Buzz", "53,FizzBuzz"})
    void line_content_should_be_correct(int turn, String expect) {
        assertThat(getOutput()[turn - 1]).isEqualTo(expect);
    }

    private String[] getOutput() {
        return new FizzBuzz().print().split("\n");
    }
}
