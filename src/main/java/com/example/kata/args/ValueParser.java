package com.example.kata.args;

import java.util.Optional;

abstract class ValueParser {
    static ValueParser booleanWithDefault(boolean defaultValue) {
        return new BooleanValueParser(defaultValue);
    }

    static ValueParser stringWithDefault(String defaultValue) {
        return new StringValueParser(defaultValue);
    }

    static ValueParser numberWithDefault(int defaultValue) {
        return new NumberValueParser(defaultValue);
    }

    static ValueParser listWithDefault(ValueParser elementParser) {
        return new ListValueParser(elementParser);
    }

    abstract Optional<Value> parse(CharStream input);

    abstract Value getDefaultValue();

}
