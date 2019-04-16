package com.example.kata.args;

import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

class Args {
    private final Map<Character, Argument> arguments = Maps.newLinkedHashMap();

    Args(Schema schema, String commandLine) {
        resolveDefaultValues(schema);
        mergeParsedArguments(parseOptions(schema, commandLine));
    }

    private void resolveDefaultValues(Schema schema) {
        schema.walkOptions(option -> arguments.put(option.getFlag(), option.makeDefaultArgument()));
    }

    private void mergeParsedArguments(List<Argument> parsedArguments) {
        parsedArguments.forEach(argument -> arguments.put(argument.getFlag(), argument));
    }

    private List<Argument> parseOptions(Schema schema, String commandLine) {
        return new Parser(schema, commandLine).parse();
    }

    Value getOptionValue(char flag) {
        return arguments.get(flag).getValue();
    }
}
