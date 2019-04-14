package com.example.kata.args;

@lombok.Value
class Argument {
    private final Option option;
    private final Value value;

    Argument(Option option, Value value) {
        this.option = option;
        this.value = value;
    }

    char getFlag() {
        return option.getFlag();
    }
}
