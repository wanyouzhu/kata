package com.example.kata.args;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Schema {
    private final ArrayList<Option> options;

    public Schema(Option... options) {
        this.options = Lists.newArrayList(options);
    }

    public ProgramOptions match(String[] args) {
        CommandLineInput input = new CommandLineInput(args);
        return new ProgramOptions(options.stream().map(o -> o.match(input)).collect(Collectors.toList()));
    }
}
