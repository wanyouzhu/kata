package com.example.kata.fizzbuzz;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FizzBuzzTest {
    @Test
    public void should_play_100_turns() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        assertThat(fizzBuzz.getResult().size(), is(100));
    }

    @Test
    public void report_should_be_fizz_if_turn_number_is_multiples_of_3() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        assertThat(fizzBuzz.report(3), is("fizz"));
        assertThat(fizzBuzz.report(6), is("fizz"));
        assertThat(fizzBuzz.report(9), is("fizz"));
    }

    @Test
    public void report_should_be_buzz_if_turn_number_is_multiples_of_5() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        assertThat(fizzBuzz.report(5), is("buzz"));
        assertThat(fizzBuzz.report(10), is("buzz"));
        assertThat(fizzBuzz.report(20), is("buzz"));
    }

    @Test
    public void report_should_be_turn_number_if_turn_number_is_not_multiples_of_3_or_5() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        assertThat(fizzBuzz.report(1), is("1"));
        assertThat(fizzBuzz.report(2), is("2"));
        assertThat(fizzBuzz.report(11), is("11"));
    }

    @Test
    public void report_should_be_fizzbuzz_if_turn_number_is_fizz_and_buzz() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        assertThat(fizzBuzz.report(15), is("fizzbuzz"));
        assertThat(fizzBuzz.report(30), is("fizzbuzz"));
        assertThat(fizzBuzz.report(45), is("fizzbuzz"));
    }

    @Test
    public void report_should_be_fizz_if_turn_number_contains_3() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        assertThat(fizzBuzz.report(3), is("fizz"));
        assertThat(fizzBuzz.report(13), is("fizz"));
        assertThat(fizzBuzz.report(33), is("fizz"));
        assertThat(fizzBuzz.report(36), is("fizz"));
    }

    @Test
    public void report_should_be_buzz_if_turn_number_contains_of_5() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        assertThat(fizzBuzz.report(5), is("buzz"));
        assertThat(fizzBuzz.report(20), is("buzz"));
        assertThat(fizzBuzz.report(55), is("buzz"));
        assertThat(fizzBuzz.report(56), is("buzz"));
    }
}
