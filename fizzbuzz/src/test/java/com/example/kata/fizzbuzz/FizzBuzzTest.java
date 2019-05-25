package com.example.kata.fizzbuzz;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FizzBuzzTest {
    @Test
    public void can_generate_100_turns() {
        ReportList reportList = new FizzBuzz().run();
        assertEquals(100, reportList.getNumberOfReports());
    }
}
