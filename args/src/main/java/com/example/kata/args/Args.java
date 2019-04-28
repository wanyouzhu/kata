package com.example.kata.args;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

class Args {
    private final List<Argument> arguments;

    Args(String schema, String commandLine) {
        this.arguments = parseArguments(schema);
        resolveValues(commandLine);
    }

    int getNumberOfArguments() {
        return arguments.size();
    }

    <T> T getValue(char flag) {
        return getArgument(flag).getValue();
    }

    private List<Argument> parseArguments(String schema) {
        return Arrays.stream(schema.split(";")).map(Argument::new).collect(Collectors.toList());
    }

    private Argument getArgument(char flag) {
        Supplier<ArgsException> exception = () -> new ArgsException("Argument not found: " + flag);
        return arguments.stream().filter(x -> x.getFlag() == flag).findFirst().orElseThrow(exception);
    }

    private void resolveValues(String commandLine) {
        arguments.forEach(argument -> argument.resolveValue(commandLine));
    }
}
