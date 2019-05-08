package com.example.kata.args;

import java.util.Arrays;
import java.util.stream.Collectors;

abstract class ListValueType extends ValueType {
    @Override
    Value parseValue(String valuePart) {
        return new Value(Arrays.stream(valuePart.split(",")).map(this::parseElement).collect(Collectors.toList()));
    }

    abstract Object parseElement(String valueString);
}
