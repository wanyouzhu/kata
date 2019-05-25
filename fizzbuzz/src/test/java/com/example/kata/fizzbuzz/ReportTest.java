package com.example.kata.fizzbuzz;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReportTest {
    @Test
    public void should_return_turn_number_if_is_normal_turn() {
        assertEquals("1 1", new Report(1).toString());
    }

    @Test
    public void should_return_fizz_if_turn_number_is_dividable_by_3() {
        assertEquals("3 Fizz", new Report(3).toString());
        assertEquals("6 Fizz", new Report(6).toString());
    }

    @Test
    public void should_return_buzz_if_turn_number_is_dividable_by_5() {
        assertEquals("5 Buzz", new Report(5).toString());
        assertEquals("10 Buzz", new Report(10).toString());
    }

    @Test
    public void should_return_fizzbuzz_if_turn_number_is_dividable_by_3_and_5() {
        assertEquals("15 FizzBuzz", new Report(15).toString());
    }

    @Test
    public void should_return_fizz_if_turn_number_contains_3() {
        assertEquals("13 Fizz", new Report(13).toString());
        assertEquals("31 Fizz", new Report(31).toString());
    }

    @Test
    public void should_return_buzz_if_turn_number_contains_5() {
        assertEquals("52 Buzz", new Report(52).toString());
        assertEquals("56 Buzz", new Report(56).toString());
    }

    @Test
    public void should_return_fizzbuzz_if_turn_number_contains_3_and_5() {
        assertEquals("53 FizzBuzz", new Report(53).toString());
        assertEquals("35 FizzBuzz", new Report(35).toString());
    }
}
