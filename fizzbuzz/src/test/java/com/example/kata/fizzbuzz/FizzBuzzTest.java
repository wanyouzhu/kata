package com.example.kata.fizzbuzz;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FizzBuzzTest {
    @Test
    public void should_output_result_of_100_turns() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        assertThat(fizzBuzz.getResult().size(), is(100));
    }

    @Test
    public void should_be_fizz_if_turn_number_is_multiples_of_3() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        assertThat(fizzBuzz.getResult().get(3 - 1), is("fizz"));
        assertThat(fizzBuzz.getResult().get(6 - 1), is("fizz"));
        assertThat(fizzBuzz.getResult().get(9 - 1), is("fizz"));
        assertThat(fizzBuzz.getResult().get(99 - 1), is("fizz"));
    }

    @Test
    public void should_be_turn_number_if_turn_number_is_NOT_multiples_of_3() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        assertThat(fizzBuzz.getResult().get(1 - 1), is("1"));
        assertThat(fizzBuzz.getResult().get(2 - 1), is("2"));
        assertThat(fizzBuzz.getResult().get(11 - 1), is("11"));
        assertThat(fizzBuzz.getResult().get(28 - 1), is("28"));
    }

    @Test
    public void should_be_buzz_if_turn_number_is_multiples_of_5() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        assertThat(fizzBuzz.getResult().get(5 - 1), is("buzz"));
        assertThat(fizzBuzz.getResult().get(10 - 1), is("buzz"));
        assertThat(fizzBuzz.getResult().get(20 - 1), is("buzz"));
        assertThat(fizzBuzz.getResult().get(100 - 1), is("buzz"));
    }

    @Test
    public void should_be_fizz_if_turn_number_contains_of_3() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        assertThat(fizzBuzz.getResult().get(3 - 1), is("fizz"));
        assertThat(fizzBuzz.getResult().get(13 - 1), is("fizz"));
        assertThat(fizzBuzz.getResult().get(13 - 1), is("fizz"));
        assertThat(fizzBuzz.getResult().get(33 - 1), is("fizz"));
        assertThat(fizzBuzz.getResult().get(37 - 1), is("fizz"));
        assertThat(fizzBuzz.getResult().get(93 - 1), is("fizz"));
    }

    @Test
    public void should_be_buzz_if_turn_number_contains_of_5() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        assertThat(fizzBuzz.getResult().get(5 - 1), is("buzz"));
        assertThat(fizzBuzz.getResult().get(25 - 1), is("buzz"));
        assertThat(fizzBuzz.getResult().get(56 - 1), is("buzz"));
        assertThat(fizzBuzz.getResult().get(58 - 1), is("buzz"));
        assertThat(fizzBuzz.getResult().get(65 - 1), is("buzz"));
        assertThat(fizzBuzz.getResult().get(85 - 1), is("buzz"));
    }

    @Test
    public void should_be_fizzbuzz_if_turn_number_is_fizz_and_buzz() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        assertThat(fizzBuzz.getResult().get(15 - 1), is("fizzbuzz"));
        assertThat(fizzBuzz.getResult().get(35 - 1), is("fizzbuzz"));
        assertThat(fizzBuzz.getResult().get(57 - 1), is("fizzbuzz"));
        assertThat(fizzBuzz.getResult().get(60 - 1), is("fizzbuzz"));
    }
}
