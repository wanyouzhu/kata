package com.example.kata.fizzbuzz;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ReportList {
    private final Report[] reports;

    ReportList(Report... reports) {
        this.reports = reports;
    }

    int getNumberOfReports() {
        return reports.length;
    }

    @Override
    public String toString() {
        return Arrays.stream(reports).map(Report::toString).collect(Collectors.joining("\n"));
    }
}
