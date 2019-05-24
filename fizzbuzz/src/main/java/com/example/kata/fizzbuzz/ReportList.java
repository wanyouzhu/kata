package com.example.kata.fizzbuzz;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReportList {
    private final List<Report> reports;

    ReportList(Report... reports) {
        this.reports = Arrays.asList(reports);
    }

    int getNumberOfReports() {
        return reports.size();
    }

    @Override
    public String toString() {
        return reports.stream().map(Report::toString).collect(Collectors.joining("\n"));
    }
}
