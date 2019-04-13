package com.example.kata.args;

import java.util.Optional;

abstract class ValueParser {
    static ValueParser forBooleanWithDefault(boolean defaultValue) {
        return new BooleanValueParser(defaultValue);
    }

    static ValueParser forStringWithDefault(String defaultValue) {
        return new StringValueParser(defaultValue);
    }

    static ValueParser forNumberWithDefault(int defaultValue) {
        return new NumberValueParser(defaultValue);
    }

    static ValueParser forListWithDefault(ValueParser elementParser) {
        return new ListValueParser(elementParser);
    }

    abstract Optional<Value> parse(CharStream input);

    abstract Value getDefaultValue();

}
