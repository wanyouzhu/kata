package com.example.kata.fizzbuzz;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FizzBuzzTest {
    @Test
    public void should_print_fizz_buzz_list_correctly() {
        assertEquals(100, splitOutput().length);
        assertEquals("1", getOutputLine(1));
        assertEquals("Fizz", getOutputLine(3));
        assertEquals("Buzz", getOutputLine(5));
        assertEquals("FizzBuzz", getOutputLine(15));
        assertEquals("Fizz", getOutputLine(13));
        assertEquals("Buzz", getOutputLine(52));
    }

    private String getOutputLine(int n) {
        return splitOutput()[n - 1];
    }

    private String[] splitOutput() {
        return new FizzBuzz().print().split("\n");
    }
}
