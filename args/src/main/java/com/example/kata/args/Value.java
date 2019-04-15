package com.example.kata.args;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.stream.Stream;

@lombok.Value
class Value {
    private final String type;
    private final Object value;

    private Value(String type, Object value) {
        this.type = type;
        this.value = value;
    }

    static Value ofBool(boolean value) {
        return new Value("bool", value);
    }

    static Value ofInt(int value) {
        return new Value("int", value);
    }

    static Value ofString(String value) {
        return new Value("string", value);
    }

    static Value ofIntList(Integer... value) {
        return new Value("[int]", Lists.newArrayList(value));
    }

    static Value ofStringList(String... value) {
        return new Value("[string]", Lists.newArrayList(value));
    }

    static Value parseBool(String text) {
        return ofBool(Boolean.parseBoolean(text));
    }

    static Value parseInt(String text) {
        return ofInt(Integer.parseInt(text));
    }

    static Value parseStringList(String test) {
        Stream<String> valueStrings = Arrays.stream(StringUtils.split(test, ','));
        return ofStringList(valueStrings.map(StringUtils::trim).toArray(String[]::new));
    }

    static Value parseIntList(String text) {
        Stream<String> valueStrings = Arrays.stream(StringUtils.split(text, ','));
        return ofIntList(valueStrings.map(StringUtils::trim).map(Integer::parseInt).toArray(Integer[]::new));
    }
}
