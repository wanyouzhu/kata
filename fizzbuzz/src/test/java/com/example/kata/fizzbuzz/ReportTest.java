package com.example.kata.fizzbuzz;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReportTest {
    @Test
    public void should_report_turn_number_if_is_normal_turn() {
        assertEquals("1 1", new Report(1).toString());
        assertEquals("2 2", new Report(2).toString());
    }

    @Test
    public void should_report_fizz_if_turn_number_is_dividable_by_3() {
        assertEquals("3 Fizz", new Report(3).toString());
        assertEquals("6 Fizz", new Report(6).toString());
    }

    @Test
    public void should_report_buzz_if_turn_number_is_dividable_by_5() {
        assertEquals("5 Buzz", new Report(5).toString());
        assertEquals("10 Buzz", new Report(10).toString());
    }

    @Test
    public void should_report_fizz_buzz_if_turn_number_is_dividable_by_3_and_5() {
        assertEquals("15 FizzBuzz", new Report(15).toString());
        assertEquals("45 FizzBuzz", new Report(45).toString());
    }

    @Test
    public void should_report_fizz_if_turn_number_contains_3() {
        assertEquals("13 Fizz", new Report(13).toString());
        assertEquals("23 Fizz", new Report(23).toString());
        assertEquals("31 Fizz", new Report(31).toString());
    }

    @Test
    public void should_report_buzz_if_turn_number_contains_5() {
        assertEquals("52 Buzz", new Report(52).toString());
        assertEquals("56 Buzz", new Report(56).toString());
    }

    @Test
    public void should_report_fizzbuzz_if_turn_number_contains_3_and_5() {
        assertEquals("35 FizzBuzz", new Report(35).toString());
        assertEquals("53 FizzBuzz", new Report(53).toString());
    }
}
