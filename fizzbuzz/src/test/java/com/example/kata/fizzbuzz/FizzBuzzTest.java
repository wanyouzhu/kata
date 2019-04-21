package com.example.kata.fizzbuzz;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FizzBuzzTest {
    @Test
    public void should_report_one_in_turn_one() {
        int turn = 1;
        String report = new FizzBuzz().report(turn);
        assertThat(report, is("1"));
    }

    @Test
    public void should_report_fizz_in_turn_three() {
        int turn = 3;
        String report = new FizzBuzz().report(turn);
        assertThat(report, is("fizz"));
    }

    @Test
    public void should_report_fizz_in_turn_six() {
        int turn = 6;
        String report = new FizzBuzz().report(turn);
        assertThat(report, is("fizz"));
    }

    @Test
    public void should_report_seven_in_turn_seven() {
        int turn = 7;
        String report = new FizzBuzz().report(turn);
        assertThat(report, is("7"));
    }

    @Test
    public void should_report_fizz_in_turn_nine() {
        int turn = 9;
        String report = new FizzBuzz().report(turn);
        assertThat(report, is("fizz"));
    }

    @Test
    public void should_report_fizz_if_turn_is_multiples_of_three() {
        assertThat(new FizzBuzz().report(99), is("fizz"));
        assertThat(new FizzBuzz().report(27), is("fizz"));
        assertThat(new FizzBuzz().report(63), is("fizz"));
    }

    @Test
    public void should_report_turn_number_if_turn_is_NOT_multiples_of_three() {
        assertThat(new FizzBuzz().report(13), is("13"));
        assertThat(new FizzBuzz().report(23), is("23"));
        assertThat(new FizzBuzz().report(98), is("98"));
    }

    @Test
    public void should_report_buzz_in_turn_five() {
        int turn = 5;
        String report = new FizzBuzz().report(turn);
        assertThat(report, is("buzz"));
    }

    @Test
    public void should_report_buzz_in_turn_twenty() {
        int turn = 20;
        String report = new FizzBuzz().report(turn);
        assertThat(report, is("buzz"));
    }

    @Test
    public void should_report_buzz_if_turn_is_multiples_of_file() {
        assertThat(new FizzBuzz().report(10), is("buzz"));
        assertThat(new FizzBuzz().report(55), is("buzz"));
        assertThat(new FizzBuzz().report(100), is("buzz"));
    }

    @Test
    public void should_report_turn_number_if_turn_is_NOT_multiples_of_three_or_file() {
        assertThat(new FizzBuzz().report(88), is("88"));
        assertThat(new FizzBuzz().report(44), is("44"));
        assertThat(new FizzBuzz().report(67), is("67"));
    }

    @Test
    public void should_report_fizzbuzz_if_turn_is_fizz_and_buzz() {
        assertThat(new FizzBuzz().report(15), is("fizzbuzz"));
        assertThat(new FizzBuzz().report(45), is("fizzbuzz"));
        assertThat(new FizzBuzz().report(90), is("fizzbuzz"));
    }
}