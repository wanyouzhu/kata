package com.example.kata.args;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

class Args {
    private final List<Argument> arguments;

    Args(String schema, String commandLine) {
        this.arguments = parseArgumentsFromSchema(schema);
        mergeArgumentValuesFromCommandLine(commandLine);
    }

    private List<Argument> parseArgumentsFromSchema(String schema) {
        return Arrays.stream(schema.split(";")).map(Argument::new).collect(Collectors.toList());
    }

    private void mergeArgumentValuesFromCommandLine(String commandLine) {
        String modified = commandLine;
        for (Argument argument : arguments) {
            modified = argument.mergeValueFromCommandLine(modified);
        }
    }

    boolean getBooleanValue(char flag) {
        return getArgument(flag).getBooleanValue();
    }

    int getNumberOfArguments() {
        return arguments.size();
    }

    int getIntegerValue(char flag) {
        return getArgument(flag).getIntegerValue();
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
        Supplier<ArgsException> argsException = () -> new ArgsException("Flag not exist: " + flag);
        return arguments.stream().filter(x -> x.getFlag() == flag).findFirst().orElseThrow(argsException);
    }
}
