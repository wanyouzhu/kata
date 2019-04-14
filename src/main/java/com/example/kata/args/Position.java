package com.example.kata.args;

class Position {
    private final String text;
    private final int current;

    private Position(String text) {
        this.text = text;
        this.current = 0;
    }

    static Position start(String text) {
        return new Position(text);
    }
}
