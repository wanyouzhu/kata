package com.example.kata.args;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.trim;

class Argument {
    private final char flag;
    private final String type;
    private Object value;

    Argument(String segment) {
        this.flag = parseFlag(segment);
        this.type = parseType(segment);
        this.value = parseValue(extractValuePart(segment));
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

    private int parseInteger(String valuePart) {
        return Integer.parseInt(trim(valuePart));
    }

    private Object parseStrings(String valuePart) {
        return splitList(valuePart).map(this::parseString).collect(Collectors.toList());
    }

    private Object parseIntegers(String valuePart) {
        return splitList(valuePart).map(this::parseInteger).collect(Collectors.toList());
    }

    private Object parseBoolean(String valuePart) {
        if (StringUtils.equals(valuePart, "false")) return false;
        if (StringUtils.equals(valuePart, "true")) return true;
        throw new ArgsException("Malformed boolean value: " + valuePart);
    }

    private Object parseString(String valuePart) {
        return trim(valuePart);
    }

    private String extractValuePart(String segment) {
        return trim(splitIntParts(segment)[2]);
    }

    private String[] splitIntParts(String segment) {
        return segment.split(":");
    }

    private Stream<String> splitList(String valuePart) {
        return Arrays.stream(valuePart.split(","));
    }

    private String parseType(String segment) {
        return trim(splitIntParts(segment)[1]);
    }

    private char parseFlag(String segment) {
        return trim(splitIntParts(segment)[0]).charAt(0);
    }

    char getFlag() {
        return flag;
    }

    <T> T getValue() {
        return (T) value;
    }

    void mergeValueFromCommandLine(String commandLine) {
        Pattern pattern = Pattern.compile("^.*(-" + flag + "(?:\\s+((?:[^-]|-\\d)+))?).*$");
        Matcher matcher = pattern.matcher(commandLine);
        if (!matcher.matches()) return;
        this.value = isMissingValueBooleanArgument(matcher) ? Boolean.valueOf(true) : parseValue(trim(matcher.group(2)));
    }

    private boolean isMissingValueBooleanArgument(Matcher matcher) {
        return StringUtils.equals(type, "boolean") && isBlank(matcher.group(2));
    }
}
