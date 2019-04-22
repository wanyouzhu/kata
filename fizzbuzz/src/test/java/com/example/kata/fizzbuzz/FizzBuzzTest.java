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
    public void should_report_fizz_if_turn_number_is_multiples_of_3() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        assertThat(fizzBuzz.report(3), is("fizz"));
        assertThat(fizzBuzz.report(6), is("fizz"));
        assertThat(fizzBuzz.report(9), is("fizz"));
    }

    @Test
    public void should_report_buzz_if_turn_number_is_multiples_of_5() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        assertThat(fizzBuzz.report(5), is("buzz"));
        assertThat(fizzBuzz.report(10), is("buzz"));
        assertThat(fizzBuzz.report(25), is("buzz"));
    }

    @Test
    public void should_report_fizzbuzz_if_turn_number_is_multiples_of_3_and_5() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        assertThat(fizzBuzz.report(15), is("fizzbuzz"));
        assertThat(fizzBuzz.report(30), is("fizzbuzz"));
        assertThat(fizzBuzz.report(45), is("fizzbuzz"));
    }

    @Test
    public void should_report_turn_number_if_turn_number_is_NOT_multiples_of_3_or_5() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        assertThat(fizzBuzz.report(1), is("1"));
        assertThat(fizzBuzz.report(2), is("2"));
        assertThat(fizzBuzz.report(7), is("7"));
    }

    @Test
    public void should_report_fizz_if_turn_number_contains_3() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        assertThat(fizzBuzz.report(3), is("fizz"));
        assertThat(fizzBuzz.report(13), is("fizz"));
        assertThat(fizzBuzz.report(23), is("fizz"));
        assertThat(fizzBuzz.report(37), is("fizz"));
    }

    @Test
    public void should_report_buzz_if_turn_number_contains_5() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        assertThat(fizzBuzz.report(5), is("buzz"));
        assertThat(fizzBuzz.report(25), is("buzz"));
        assertThat(fizzBuzz.report(55), is("buzz"));
        assertThat(fizzBuzz.report(58), is("buzz"));
    }
}
