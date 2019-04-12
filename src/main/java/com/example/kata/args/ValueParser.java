package com.example.kata.args;

import java.util.Optional;

abstract class ValueParser {
    static ValueParser forBoolean(boolean defaultValue) {
        return new BooleanValueParser(defaultValue);
    }

    static ValueParser forString(String defaultValue) {
        return new StringValueParser(defaultValue);
    }

    static ValueParser forNumber(int defaultValue) {
        return new NumberValueParser(defaultValue);
    }

    static ValueParser forList(ValueParser elementParser) {
        return new ListValueParser(elementParser);
    }

    abstract Optional<Value> parse(CharStream input);

    abstract Value getDefaultValue();

}
