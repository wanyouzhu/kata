package com.example.kata.fizzbuzz;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FizzBuzzTest {
    @Test
    public void should_report_100_turns() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        assertThat(fizzBuzz.getResult().size(), is(100));
    }

    @Test
    public void should_report_fizz_if_turn_is_multiples_of_3() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        assertThat(fizzBuzz.report(3), is("fizz"));
    }

    @Test
    public void should_report_buzz_if_turn_is_multiples_of_5() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        assertThat(fizzBuzz.report(5), is("buzz"));
    }

    @Test
    public void should_report_fizz_if_turn_contains_3() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        assertThat(fizzBuzz.report(13), is("fizz"));
    }

    @Test
    public void should_report_buzz_if_turn_contains_5() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        assertThat(fizzBuzz.report(58), is("buzz"));
    }

    @Test
    public void should_report_fizzbuzz_if_turn_is_fizz_and_buzz() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        assertThat(fizzBuzz.report(15), is("fizzbuzz"));
    }

    @Test
    public void should_report_turn_number_if_it_is_NOT_fizz_or_buzz() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        assertThat(fizzBuzz.report(1), is("1"));
    }
}
