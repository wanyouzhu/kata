package com.example.kata.fizzbuzz;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class FizzBuzzTest {
    @Test
    public void should_report_fizz_if_turn_number_is_multiple_of_3() {
        assertThat(new FizzBuzz().report(3), is("fizz"));
    }

    @Test
    public void should_report_buzz_if_turn_number_is_multiple_of_5() {
        assertThat(new FizzBuzz().report(5), is("buzz"));
    }

    @Test
    public void should_report_fizzbuzz_if_turn_number_is_multiple_of_3_and_5() {
        assertThat(new FizzBuzz().report(15), is("fizzbuzz"));
    }

    @Test
    public void should_report_fizz_if_turn_number_contains_3() {
        assertThat(new FizzBuzz().report(13), is("fizz"));
    }

    @Test
    public void should_report_buzz_if_turn_number_contains_5() {
        assertThat(new FizzBuzz().report(58), is("buzz"));
    }

    @Test
    public void should_report_turn_number_if_turn_is_not_fizz_or_buzz() {
        assertThat(new FizzBuzz().report(1), is("1"));
    }
}
