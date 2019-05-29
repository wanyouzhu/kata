package com.example.kata.args;

import java.util.Objects;

public class Args {
    private final Schema schema;
    private final String[] input;
    private Object value;

    public Args(Schema schema, String[] input) {
        this.schema = schema;
        this.input = input;
        this.value = parseValue();
    }

    private Object parseValue() {
        if (input.length < 2) return true;
        if (Objects.equals(input[1], "false")) return false;
        if (Objects.equals(input[1], "true")) return true;
        throw new ArgsException(String.format("Malformed boolean value for flag '%s': %s", input[0].charAt(1), input[1]));
    }

    public Object getValue(char flag) {
        return value;
    }
}
