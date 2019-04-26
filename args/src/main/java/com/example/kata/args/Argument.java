package com.example.kata.args;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
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

    char getFlag() {
        return flag;
    }

    int getIntegerValue() {
        return (Integer) value;
    }

    boolean getBooleanValue() {
        return (Boolean) value;
    }

    String getStringValue() {
        return (String) value;
    }

    List<Integer> getIntegersValue() {
        return (List<Integer>) value;
    }

    List<String> getStringsValue() {
        return (List<String>) value;
    }

    private char parseFlag(String segment) {
        return trim(splitIntoParts(segment)[0]).charAt(0);
    }

    private String parseType(String segment) {
        return trim(splitIntoParts(segment)[1]);
    }

    private String extractValuePart(String segment) {
        return trim(splitIntoParts(segment)[2]);
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

    private Object parseStrings(String valuePart) {
        return splitListValues(valuePart).map(this::parseString).collect(Collectors.toList());
    }

    private Object parseIntegers(String valuePart) {
        return splitListValues(valuePart).map(this::parseInteger).collect(Collectors.toList());
    }

    private Stream<String> splitListValues(String valuePart) {
        return Arrays.stream(valuePart.split(","));
    }

    private Object parseString(String valuePart) {
        return trim(valuePart);
    }

    private Object parseBoolean(String valuePart) {
        if (StringUtils.equals(valuePart, "false")) return false;
        if (StringUtils.equals(valuePart, "true")) return true;
        throw new ArgsException("Malformed boolean value: " + valuePart);
    }

    private Object parseInteger(String valuePart) {
        return Integer.parseInt(trim(valuePart));
    }

    private String[] splitIntoParts(String segment) {
        return segment.split(":");
    }

    void extractValueFromCommand(String commandLine) {
        Pattern pattern = Pattern.compile("^.*(-" + flag + "(?:\\s*$|((?:[^-]|-\\d)+))).*$");
        Matcher matcher = pattern.matcher(commandLine);
        if (!matcher.matches()) return;
        this.value = parseValueFromMatchResult(matcher);
    }

    private Object parseValueFromMatchResult(Matcher matcher) {
        return isBooleanFlagOnlyArgument(matcher) ? Boolean.valueOf(true) : parseValue(trim(matcher.group(2)));
    }

    private boolean isBooleanFlagOnlyArgument(Matcher matcher) {
        return StringUtils.equals(type, "boolean") && isBlank(matcher.group(2));
    }
}
