package com.example.kata.fizzbuzz;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FizzBuzzTest {
    @Test
    public void should_print_100_lines() {
        assertEquals(100, new FizzBuzz().print().split("\n").length);
    }

    @Test
    public void lines_should_be_turn_number_if_in_simple_turn() {
        assertEquals("1", getPrintedListLine(0));
        assertEquals("2", getPrintedListLine(1));
    }

    @Test
    public void lines_should_be_fizz_if_turn_number_is_dividable_by_3() {
        assertEquals("Fizz", getPrintedListLine(2));
        assertEquals("Fizz", getPrintedListLine(5));
    }

    @Test
    public void lines_should_be_buzz_if_turn_number_is_dividable_by_5() {
        assertEquals("Buzz", getPrintedListLine(4));
        assertEquals("Buzz", getPrintedListLine(9));
    }

    @Test
    public void lines_should_be_fizzbuzz_if_turn_number_is_dividable_by_3_and_5() {
        assertEquals("FizzBuzz", getPrintedListLine(14));
        assertEquals("FizzBuzz", getPrintedListLine(44));
    }

    @Test
    public void lines_should_be_fizz_if_turn_number_contains_3() {
        assertEquals("Fizz", getPrintedListLine(12));
        assertEquals("Fizz", getPrintedListLine(30));
    }

    @Test
    public void lines_should_be_buzz_if_turn_number_contains_5() {
        assertEquals("Buzz", getPrintedListLine(51));
        assertEquals("Buzz", getPrintedListLine(55));
    }

    @Test
    public void lines_should_be_fizzbuzz_if_turn_number_contains_3_and_5() {
        assertEquals("FizzBuzz", getPrintedListLine(34));
        assertEquals("FizzBuzz", getPrintedListLine(52));
    }

    private String getPrintedListLine(int n) {
        return new FizzBuzz().print().split("\n")[n];
    }
}
