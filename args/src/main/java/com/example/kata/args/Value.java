package com.example.kata.args;

import com.google.common.collect.Lists;

@lombok.Value
class Value {
    private final ValueType type;
    private final Object value;

    static Value ofInteger(int value) {
        return new Value(ValueType.INTEGER, value);
    }

    static Value ofBoolean(boolean value) {
        return new Value(ValueType.BOOLEAN, value);
    }

    static Value ofString(String value) {
        return new Value(ValueType.STRING, value);
    }

    static Value ofIntegers(Integer... value) {
        return new Value(ValueType.INTEGERS, Lists.newArrayList(value));
    }

    static Value ofStrings(String... value) {
        return new Value(ValueType.STRINGS, Lists.newArrayList(value));
    }
}
