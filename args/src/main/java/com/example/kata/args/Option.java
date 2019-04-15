package com.example.kata.args;

@lombok.Value
class Option {
    private final char flag;
    private final Value defaultValue;

    Option(char flag, Value defaultValue) {
        this.flag = flag;
        this.defaultValue = defaultValue;
    }

    char getFlag() {
        return flag;
    }

    String getType() {
        return defaultValue.getType();
    }
}

