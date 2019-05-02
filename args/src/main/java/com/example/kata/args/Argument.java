package com.example.kata.args;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.*;

class ValueType {
    private final String type;

    ValueType(String type) {
        this.type = type;
    }

    String getType() {
        return type;
    }

    Object parseValue(String valuePart) {
        switch (getType()) {
            case "integer":
                return parseInteger(valuePart);
            case "boolean":
                return parseBoolean(valuePart);
            case "string":
                return parseString(valuePart);
            case "strings":
                return parseStrings(valuePart);
            case "integers":
                return parseIntegers(valuePart);
            default:
                throw new ArgsException("Unknown value type: " + getType());
        }
    }

    private Object parseInteger(String valuePart) {
        return Integer.parseInt(trim(valuePart));
    }

    private Object parseBoolean(String valuePart) {
        if (isBlank(valuePart)) return true;
        if (StringUtils.equals(trim(valuePart), "true")) return true;
        if (StringUtils.equals(trim(valuePart), "false")) return false;
        throw new ArgsException("Malformed boolean value: " + valuePart);
    }

    private Object parseString(String valuePart) {
        return trim(valuePart);
    }

    private Object parseStrings(String valuePart) {
        return parseList(valuePart, this::parseString);
    }

    private Object parseIntegers(String valuePart) {
        return parseList(valuePart, this::parseInteger);
    }

    private List<Object> parseList(String valuePart, Function<String, Object> elementParser) {
        return Arrays.stream(valuePart.split(",")).map(elementParser).collect(Collectors.toList());
    }
}

class Flag {
    private final char name;

    Flag(char name) {
        this.name = name;
    }

    char getName() {
        return name;
    }
}

class Value {
    private final Object value;

    Value(Object value) {
        this.value = value;
    }

    Object getValue() {
        return value;
    }
}

class Argument {
    private Flag flag;
    private ValueType type;
    private Value value;

    Argument(String segment) {
        this.flag = new Flag(getSchemaPart(segment, 0).charAt(0));
        this.type = new ValueType(getSchemaPart(segment, 1));
        this.value = new Value(parseValue(getSchemaPart(segment, 2)));
    }

    private Object parseValue(String valuePart) {
        return this.type.parseValue(valuePart);
    }

    private String getSchemaPart(String segment, int i) {
        return trim(segment.split(":")[i]);
    }

    char getFlag() {
        return flag.getName();
    }

    <T> T getValue() {
        return (T) value.getValue();
    }

    void resolveValue(String commandLine) {
        matchValuePart(commandLine).ifPresent(x -> this.value = new Value(parseValue(trim(x))));
    }

    private Optional<String> matchValuePart(String commandLine) {
        Matcher matcher = getPattern().matcher(commandLine);
        return matcher.matches() ? Optional.of(defaultString(matcher.group(1))) : Optional.empty();
    }

    private Pattern getPattern() {
        return Pattern.compile("^.*-" + getFlag() + "(?:\\s((?:[^-]|-\\d)+))?.*$");
    }
}
