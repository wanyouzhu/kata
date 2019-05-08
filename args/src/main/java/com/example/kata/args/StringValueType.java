package com.example.kata.args;

import static org.apache.commons.lang3.StringUtils.trim;

class StringValueType extends ValueType {
    @Override
    Value parseValue(String valuePart) {
        return new Value(trim(valuePart));
    }
}
