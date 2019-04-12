package com.example.kata.args;

import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class NumberValueParser extends ValueParser {
    private final int defaultValue;

    @Override
    Optional<Value> parse(CharStream input) {
        final int initialPosition = input.getPosition();
        StringBuilder builder = new StringBuilder();
        input.skipWhiteSpaces();
        while (!input.eof() && Character.isDigit(input.get())) {
            builder.appendCodePoint(input.read());
        }

        if (builder.length() == 0) {
            input.resetPosition(initialPosition);
            return Optional.empty();
        }

        return Optional.of(Value.ofNumber(Integer.parseInt(builder.toString())));
    }

    @Override
    Value getDefaultValue() {
        return Value.ofNumber(defaultValue);
    }
}
