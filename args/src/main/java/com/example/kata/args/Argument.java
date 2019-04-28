package com.example.kata.args;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
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

    char getFlag() {
        return flag;
    }

    <T> T getValue() {
        return (T) value;
    }

    void resolveValue(String commandLine) {
        Pattern pattern = Pattern.compile("^.*(-" + flag + "(?:\\s+((?:[^-]|-\\d)+))?).*$");
        Matcher matcher = pattern.matcher(commandLine);
        if (!matcher.matches()) return;
        if (StringUtils.equals(type, "boolean") && isBlank(matcher.group(2))) {
            this.value = true;
            return;
        }
        this.value = parseValue(trim(matcher.group(2)));
    }

    private char parseFlag(String segment) {
        return getPart(segment, 0).charAt(0);
    }

    private String parseType(String segment) {
        return getPart(segment, 1);
    }

    private Object parseDefaultValue(String segment) {
        return parseValue(getPart(segment, 2));
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
        return Integer.parseInt(valuePart);
    }

    private Object parseBoolean(String valuePart) {
        if (StringUtils.equals(valuePart, "false")) return false;
        if (StringUtils.equals(valuePart, "true")) return true;
        throw new ArgsException("Malformed boolean value: " + valuePart);
    }

    private Object parseString(String valuePart) {
        return trim(valuePart);
    }

    private Object parseIntegers(String valuePart) {
        return parseList(valuePart, this::parseInteger);
    }

    private Object parseStrings(String valuePart) {
        return parseList(valuePart, this::parseString);
    }

    private Object parseList(String valuePart, Function<String, Object> parser) {
        return Arrays.stream(valuePart.split(",")).map(parser).collect(Collectors.toList());
    }

    private String getPart(String segment, int i) {
        return trim(segment.split(":")[i]);
    }
}
