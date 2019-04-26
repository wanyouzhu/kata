package com.example.kata.args;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

class Args {
    private List<Argument> arguments;

    Args(String schema, String commandLine) {
        this.arguments = Arrays.stream(schema.split(";")).map(Argument::new).collect(Collectors.toList());
        readValuesFromCommandLine(commandLine);
    }

    private void readValuesFromCommandLine(String commandLine) {
        String modified = commandLine;
        for (Argument argument : arguments) {
            modified = argument.readValue(modified);
        }
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
        Supplier<ArgsException> argsException = () -> new ArgsException("Argument not exist: " + flag);
        return arguments.stream().filter(x -> x.getFlag() == flag).findFirst().orElseThrow(argsException);
    }
}
