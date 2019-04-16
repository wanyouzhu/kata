package com.example.kata.args;

import org.apache.commons.lang3.StringUtils;

class TokenStream {
    private final String source;
    private int position;

    TokenStream(String source) {
        this.source = source;
    }

    boolean eof() {
        return position >= source.length();
    }

    boolean canMatch(String expect) {
        return !eof() && StringUtils.equals(expect, getString(expect.length()));
    }

    boolean canMatch(char expect) {
        return !eof() && getChar() == expect;
    }

    boolean tryMatch(String expect) {
        if (!canMatch(expect)) return false;
        match(expect);
        return true;
    }

    void match(String expect) {
        if (!canMatch(expect)) throw new ArgsException(generateBadTokenMessage());
        position += expect.length();
    }

    void match(char expect) {
        if (expect != getChar()) throw new ArgsException(generateBadTokenMessage());
        ++position;
    }

    char getChar() {
        return source.charAt(position);
    }

    int readInt() {
        StringBuilder stringBuilder = new StringBuilder();
        if (getChar() == '-' || getChar() == '+') stringBuilder.append(readChar());
        collectDigits(stringBuilder);
        return Integer.parseInt(stringBuilder.toString());
    }

    String readString() {
        StringBuilder stringBuilder = new StringBuilder();
        collectStringElements(stringBuilder);
        return stringBuilder.toString();
    }

    void skipWhitespaces() {
        while (!eof() && Character.isWhitespace(getChar())) {
            ++position;
        }
    }

    String getRemains() {
        return source.substring(position);
    }

    private void collectStringElements(StringBuilder builder) {
        while (!eof() && getChar() != ',' && !Character.isWhitespace(getChar())) {
            builder.append(readChar());
        }
    }

    private void collectDigits(StringBuilder builder) {
        while (!eof() && Character.isDigit(getChar())) {
            builder.append(readChar());
        }
    }

    private char readChar() {
        char result = getChar();
        ++position;
        return result;
    }

    private String getString(int length) {
        if (eof()) return StringUtils.EMPTY;
        return source.substring(position, Math.min(position + length, source.length()));
    }

    private String generateBadTokenMessage() {
        return "Bad token near: " + getRemains();
    }
}
