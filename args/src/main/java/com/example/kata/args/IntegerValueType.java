package com.example.kata.args;

import static org.apache.commons.lang3.StringUtils.trim;

class IntegerValueType extends ValueType {
    @Override
    Value parseValue(String valuePart) {
        return new Value(Integer.parseInt(trim(valuePart)));
    }
}
