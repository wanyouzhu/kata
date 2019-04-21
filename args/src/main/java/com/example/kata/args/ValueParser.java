package com.example.kata.args;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.apache.commons.lang3.StringUtils.*;

class ValueParser {
    Value parse(String input, ValueType type) {
        switch (type) {
            case INTEGER: return Value.ofInteger(Integer.parseInt(trim(input)));
            case BOOLEAN: return Value.ofBoolean(parseBoolean(input));
            case STRING: return Value.ofString(trim(input));
            case INTEGERS: return Value.ofIntegers(parseIntegers(input));
            case STRINGS: return Value.ofStrings(parseStrings(input));
            default: throw new ArgsException("Unrecognized value type: " + type);
        }
    }

    private boolean parseBoolean(String input) {
        switch (lowerCase(trim(input))) {
            case "true": return true;
            case "false": return false;
            default: throw new ArgsException("Bad boolean value: " + input);
        }
    }

    private Integer[] parseIntegers(String input) {
        return splitString(input).map(Integer::parseInt).toArray(Integer[]::new);
    }

    private String[] parseStrings(String input) {
        return splitString(input).toArray(String[]::new);
    }

    private Stream<String> splitString(String input) {
        return Arrays.stream(input.split(",")).map(StringUtils::trim);
    }
}
