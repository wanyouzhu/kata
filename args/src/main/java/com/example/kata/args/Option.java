package com.example.kata.args;

@lombok.Value
class Option {
    private final char flag;
    private final Value defaultValue;

    static Option ofBool(char flag, boolean defaultValue) {
        return new Option(flag, Value.ofBool(defaultValue));
    }

    static Option ofInt(char flag, int defaultValue) {
        return new Option(flag, Value.ofInt(defaultValue));
    }

    static Option ofString(char flag, String defaultValue) {
        return new Option(flag, Value.ofString(defaultValue));
    }

    static Option ofIntList(char flag, Integer... defaultValue) {
        return new Option(flag, Value.ofIntList(defaultValue));
    }

    static Option ofStringList(char flag, String... defaultValue) {
        return new Option(flag, Value.ofStringList(defaultValue));
    }

    Argument makeDefaultArgument() {
        return new Argument(flag, defaultValue);
    }

    ValueType getType() {
        return defaultValue.getValueType();
    }
}
