package com.example.kata.fizzbuzz;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReportListTest {
    @Test
    public void should_return_the_correct_number_of_reports() {
        assertEquals(1, new ReportList(new Report(1)).getNumberOfReports());
        assertEquals(2, new ReportList(new Report(1), new Report(3)).getNumberOfReports());
    }

    @Test
    public void should_print_report_list_correctly() {
        assertEquals("1 1", new ReportList(new Report(1)).toString());
        assertEquals("1 1\n2 2", new ReportList(new Report(1), new Report(2)).toString());
    }
}
