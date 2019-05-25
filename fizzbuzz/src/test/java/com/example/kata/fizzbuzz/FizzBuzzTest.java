package com.example.kata.fizzbuzz;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FizzBuzzTest {
    @Test
    public void should_report_100_turns() {
        ReportList reportList = new FizzBuzz().run();
        assertEquals(100, reportList.getNumberOfReports());
    }
}
