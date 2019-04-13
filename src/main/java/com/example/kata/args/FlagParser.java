package com.example.kata.args;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
class FlagParser {
    private final char flag;

    static FlagParser of(char name) {
        return new FlagParser(name);
    }

    Optional<Flag> parse(CharStream input) {
        final int initialPosition = input.getPosition();
        if (input.skipWhiteSpaces() && input.read() == '-' && input.read() == flag && (input.eof() || Character.isWhitespace(input.get()))) {
            return Optional.of(Flag.of(flag));
        }
        input.resetPosition(initialPosition);
        return Optional.empty();
    }
}

