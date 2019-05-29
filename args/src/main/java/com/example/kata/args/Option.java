package com.example.kata.args;

public class Option {
    private final char flag;
    private final BooleanType type;

    public Option(char flag, BooleanType type) {
        this.flag = flag;
        this.type = type;
    }
}
