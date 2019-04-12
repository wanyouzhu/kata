package com.example.kata.args;

import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
class BooleanValueParser extends ValueParser {
    private final boolean defaultValue;

    @Override
    Optional<Value> parse(CharStream input) {
        return Optional.of(Value.ofBoolean(true));
    }

    @Override
    Value getDefaultValue() {
        return Value.ofBoolean(defaultValue);
    }
}
