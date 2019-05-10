package com.example.kata.args;

public class Argument {
    private final Flag flag;
    private final Object value;

    public Argument(Flag flag, Object value) {
        this.flag = flag;
        this.value = value;
    }

    public Flag getFlag() {
        return flag;
    }

    public Object getValue() {
        return value;
    }
}
