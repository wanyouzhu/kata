package com.example.kata.args;

import java.util.List;

abstract class Value {
    static Value ofNumber(int value) {
        return new NumberValue(value);
    }

    static Value ofBoolean(boolean value) {
        return new BooleanValue(value);
    }

    static Value ofString(String value) {
        return new StringValue(value);
    }

    static Value ofList(List<Value> value) {
        return new ListValue(value);
    }
}
