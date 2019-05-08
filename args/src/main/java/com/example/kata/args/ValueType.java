package com.example.kata.args;

import static org.apache.commons.lang3.StringUtils.trim;

abstract class ValueType {
    abstract Value parseValue(String valuePart);

    static ValueType create(String type) {
        switch (trim(type)) {
            case "integer": return new IntegerValueType();
            case "boolean": return new BooleanValueType();
            case "string": return new StringValueType();
            case "integers": return new IntegersValueType();
            case "strings": return new StringsValueType();
            default: throw new ArgsException("Unknown value type: " + type);
        }
    }
}
