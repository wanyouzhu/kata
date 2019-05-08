package com.example.kata.args;

import org.apache.commons.lang3.StringUtils;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.trim;

class BooleanValueType extends ValueType {
    @Override
    Value parseValue(String valuePart) {
        if (isBlank(valuePart) || StringUtils.equals(trim(valuePart), "true")) return new Value(true);
        if (StringUtils.equals(trim(valuePart), "false")) return new Value(false);
        throw new ArgsException("Malformed boolean value: " + valuePart);
    }
}
