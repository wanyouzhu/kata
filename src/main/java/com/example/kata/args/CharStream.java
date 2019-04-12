package com.example.kata.args;

import java.util.List;
import java.util.stream.Collectors;

class CharStream {
    private final List<Integer> chars;
    private int position;

    private CharStream(String source) {
        this.chars = source.codePoints().boxed().collect(Collectors.toList());
    }

    static CharStream from(String source) {
        return new CharStream(source);
    }

    boolean eof() {
        return position >= chars.size();
    }

    int read() {
        return chars.get(position++);
    }

    String read(int numberOfChars) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numberOfChars && !eof(); ++i) {
            builder.appendCodePoint(read());
        }
        return builder.toString();
    }

    void resetPosition(int position) {
        this.position = Math.min(Math.max(0, position), chars.size());
    }

    int getPosition() {
        return position;
    }

    boolean skipWhiteSpaces() {
        while (!eof()) {
            if (!Character.isWhitespace(get())) break;
            position++;
        }
        return !eof();
    }

    int get() {
        return chars.get(position);
    }
}
