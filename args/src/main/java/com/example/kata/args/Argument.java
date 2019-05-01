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

class Argument {
    private final char flag;
    private final String type;
    private Object value;

    Argument(String segment) {
        this.flag = parseFlag(segment);
        this.type = parseType(segment);
        this.value = parseDefaultValue(segment);
    }

    private char parseFlag(String segment) {
        return getSchemaPart(segment, 0).charAt(0);
    }

    private String parseType(String segment) {
        return getSchemaPart(segment, 1);
    }

    private Object parseDefaultValue(String segment) {
        return parseValue(getSchemaPart(segment, 2));
    }

    private Object parseValue(String valuePart) {
        switch (type) {
            case "integer": return parseInteger(valuePart);
            case "boolean": return parseBoolean(valuePart);
            case "string": return parseString(valuePart);
            case "integers": return parseIntegers(valuePart);
            case "strings": return parseStrings(valuePart);
            default: throw new ArgsException("Unknown value type: " + type);
        }
    }

    private Object parseInteger(String valuePart) {
        return Integer.parseInt(valuePart);
    }

    private Object parseBoolean(String valuePart) {
        if (StringUtils.equals(trim(valuePart), "true")) return true;
        if (StringUtils.equals(trim(valuePart), "false")) return false;
        throw new ArgsException("Malformed boolean value: " + valuePart);
    }

    private Object parseString(String valuePart) {
        return trim(valuePart);
    }

    private Object parseIntegers(String valuePart) {
        Function<String, Object> parseInteger = this::parseInteger;
        return parseList(valuePart, parseInteger);
    }

    private Object parseStrings(String valuePart) {
        Function<String, Object> parseString = this::parseString;
        return parseList(valuePart, parseString);
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
        this.value = isBooleanMissingPart(matcher) ? Boolean.valueOf(true) : parseValue(trim(matcher.group(1)));
    }

    private boolean isBooleanMissingPart(Matcher matcher) {
        return StringUtils.equals(type, "boolean") && isBlank(matcher.group(1));
    }
}
