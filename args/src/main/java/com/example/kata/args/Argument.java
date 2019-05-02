package com.example.kata.args;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.trim;

class ValueType {
    private final String type;

    ValueType(String type) {
        this.type = type;
    }

    String getType() {
        return type;
    }
}

class Argument {
    private char flag;
    private ValueType type;
    private Object value;

    Argument(String segment) {
        parseFlag(getSchemaPart(segment, 0));
        parseType(getSchemaPart(segment, 1));
        parseDefaultValue(segment);
    }

    private void parseDefaultValue(String segment) {
        this.value = parseValue(getSchemaPart(segment, 2));
    }

    private void parseType(String typePart) {
        this.setType(typePart);
    }

    private void parseFlag(String flagPart) {
        this.flag = flagPart.charAt(0);
    }

    private Object parseValue(String valuePart) {
        switch (getType()) {
            case "integer": return parseInteger(valuePart);
            case "boolean": return parseBoolean(valuePart);
            case "string": return parseString(valuePart);
            case "strings": return parseStrings(valuePart);
            case "integers": return parseIntegers(valuePart);
            default: throw new ArgsException("Unknown value type: " + getType());
        }
    }

    private Object parseInteger(String valuePart) {
        return Integer.parseInt(trim(valuePart));
    }

    private Object parseBoolean(String valuePart) {
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

    private String getSchemaPart(String segment, int i) {
        return trim(segment.split(":")[i]);
    }

    char getFlag() {
        return flag;
    }

    <T> T getValue() {
        return (T) value;
    }

    void resolveValue(String commandLine) {
        Pattern pattern = Pattern.compile("^.*-" + flag + "(?:\\s((?:[^-]|-\\d)+))?.*$");
        Matcher matcher = pattern.matcher(commandLine);
        if (!matcher.matches()) return;
        this.value = isBooleanMissingValue(matcher) ? Boolean.valueOf(true) : parseValue(trim(matcher.group(1)));
    }

    private boolean isBooleanMissingValue(Matcher matcher) {
        return StringUtils.equals(getType(), "boolean") && isBlank(matcher.group(1));
    }

    private String getType() {
        return type.getType();
    }

    private void setType(String type) {
        this.type = new ValueType(type);
    }
}
