package com.example.kata.args;

@lombok.Value
class Option {
    private final char flag;
    private final Value defaultValue;

    ValueType getType() {
        return defaultValue.getType();
    }
}
