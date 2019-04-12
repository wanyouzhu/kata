package com.example.kata.args;

import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
class StringValueParser extends ValueParser {
    private final String defaultValue;

    @Override
    Optional<Value> parse(CharStream input) {
        final int initialPosition = input.getPosition();
        StringBuilder builder = new StringBuilder();
        input.skipWhiteSpaces();
        while (!input.eof() && !Character.isWhitespace(input.get())) {
            builder.appendCodePoint(input.read());
        }
        if (builder.length() == 0) {
            input.resetPosition(initialPosition);
            return Optional.empty();
        }
        return Optional.of(Value.ofString(builder.toString()));
    }

    @Override
    Value getDefaultValue() {
        return Value.ofString(defaultValue);
    }
}
