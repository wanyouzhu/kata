package com.example.kata.args;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProgramOptions {
    private final Map<Flag, Object> optionValues;

    public ProgramOptions(List<Argument> arguments) {
        this.optionValues = arguments.stream().collect(Collectors.toMap(Argument::getFlag, Argument::getValue));
    }

    public Object get(char flag) {
        return optionValues.get(Flag.of(flag));
    }
}
