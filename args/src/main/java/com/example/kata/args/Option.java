package com.example.kata.args;

@lombok.Value
class Option {
    private final char flag;
    private final Value defaultValue;

    static Option ofInteger(char flag, int defaultValue) {
        return new Option(flag, Value.ofInteger(defaultValue));
    }

    static Option ofBoolean(char flag, boolean defaultValue) {
        return new Option(flag, Value.ofBoolean(defaultValue));
    }

    static Option ofString(char flag, String defaultValue) {
        return new Option(flag, Value.ofString(defaultValue));
    }

    static Option ofIntegers(char flag, Integer... defaultValue) {
        return new Option(flag, Value.ofIntegers(defaultValue));
    }

    static Option ofStrings(char flag, String... defaultValue) {
        return new Option(flag, Value.ofStrings(defaultValue));
    }
}
