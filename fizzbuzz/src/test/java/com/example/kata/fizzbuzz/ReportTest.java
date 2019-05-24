package com.example.kata.fizzbuzz;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReportTest {
    @Test
    public void should_report_turn_number_for_normal_turns() {
        assertEquals("1 1", new Report(1).toString());
    }

    @Test
    public void should_report_fizz_if_turn_number_can_dividable_by_3() {
        assertEquals("3 Fizz", new Report(3).toString());
        assertEquals("6 Fizz", new Report(6).toString());
    }

    @Test
    public void should_report_buzz_if_turn_number_can_dividable_by_5() {
        assertEquals("5 Buzz", new Report(5).toString());
        assertEquals("10 Buzz", new Report(10).toString());
    }

    @Test
    public void should_report_fizzbuzz_if_turn_number_can_dividable_by_3_and_5() {
        assertEquals("15 FizzBuzz", new Report(15).toString());
        assertEquals("30 FizzBuzz", new Report(30).toString());
    }

    @Test
    public void should_report_buzz_if_turn_number_contains_3() {
        assertEquals("58 Buzz", new Report(58).toString());
        assertEquals("59 Buzz", new Report(59).toString());
    }

    @Test
    public void should_report_fizz_if_turn_number_contains_3() {
        assertEquals("13 Fizz", new Report(13).toString());
        assertEquals("31 Fizz", new Report(31).toString());
    }
}
