package com.example.kata.fizzbuzz;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FizzBuzzTest {
    @Test
    public void should_report_100_turns() {
        assertEquals(100, new FizzBuzz().run().getNumberOfReports());
    }
}
