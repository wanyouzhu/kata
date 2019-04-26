package com.example.kata.args;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Args {
    private final List<Argument> arguments;

    Args(String schema, String commandLine) {
        this.arguments = parseArgumentsFromSchema(schema);
        mergeArgumentValuesFromCommandLine(commandLine);
    }

    int getNumberOfArguments() {
        return arguments.size();
    }

    int getIntegerValue(char flag) {
        return getArgument(flag).getIntegerValue();
    }

    boolean getBooleanValue(char flag) {
        return getArgument(flag).getBooleanValue();
    }

    String getStringValue(char flag) {
        return getArgument(flag).getStringValue();
    }

    List<Integer> getIntegersValue(char flag) {
        return getArgument(flag).getIntegersValue();
    }

    List<String> getStringsValue(char flag) {
        return getArgument(flag).getStringsValue();
    }

    private Argument getArgument(char flag) {
        return arguments.stream().filter(x -> x.getFlag() == flag).findFirst().orElseThrow(() -> new ArgsException("Unknown flag: " + flag));
    }

    private void mergeArgumentValuesFromCommandLine(String commandLine) {
        for (Argument argument : arguments) {
            argument.extractValueFromCommand(commandLine);
        }
    }

    private List<Argument> parseArgumentsFromSchema(String schema) {
        return Arrays.stream(schema.split(";")).map(Argument::new).collect(Collectors.toList());
    }
}
