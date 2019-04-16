package com.example.kata.args;

import com.google.common.collect.Lists;

import java.util.List;

@lombok.Value
class Value {
    private final ValueType valueType;
    private final Object value;

    static Value ofBool(boolean value) {
        return new Value(ValueType.BOOLEAN, value);
    }

    static Value ofInt(int value) {
        return new Value(ValueType.INTEGER, value);
    }

    static Value ofString(String value) {
        return new Value(ValueType.STRING, value);
    }

    static Value ofIntList(Integer... value) {
        return new Value(ValueType.INTEGER_LIST, Lists.newArrayList(value));
    }

    static Value ofIntList(List<Integer> value) {
        return new Value(ValueType.INTEGER_LIST, Lists.newArrayList(value));
    }

    static Value ofStringList(String... value) {
        return new Value(ValueType.STRING_LIST, Lists.newArrayList(value));
    }

    static Value ofStringList(List<String> value) {
        return new Value(ValueType.STRING_LIST, Lists.newArrayList(value));
    }
}
