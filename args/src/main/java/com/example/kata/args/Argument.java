package com.example.kata.args;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
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

    Argument(String schema) {
        String[] parts = splitIntoParts(schema);
        this.flag = parseFlag(schema, parts[0]);
        this.type = trim(parts[1]);
        this.value = parseValue(trim(parts[2]));
    }

    private char parseFlag(String schema, String part) {
        if (trim(part).length() != 1) throw new ArgsException("Bad schema segment: " + schema);
        return trim(part).charAt(0);
    }

    private String[] splitIntoParts(String schema) {
        String[] result = schema.split(":");
        if (result.length != 3) throw new ArgsException("Bad schema segment: " + schema);
        return result;
    }

    private Object parseValue(String part) {
        switch (type) {
            case "integer": return Integer.parseInt(part);
            case "boolean": return parseBoolean(part);
            case "string": return part;
            case "integers": return parseIntegers(part);
            case "strings": return parseStrings(part);
            default: throw new ArgsException("Unrecognized value type: " + type);
        }
    }

    private List<String> parseStrings(String part) {
        return splitListValue(part).collect(Collectors.toList());
    }

    private List<Integer> parseIntegers(String part) {
        return splitListValue(part).map(Integer::parseInt).collect(Collectors.toList());
    }

    private Stream<String> splitListValue(String part) {
        return Arrays.stream(part.split(",")).map(StringUtils::trim);
    }

    private boolean parseBoolean(String part) {
        if (StringUtils.equals(part, "false")) return false;
        if (StringUtils.equals(part, "true")) return true;
        throw new ArgsException("Malformed boolean value: " + part);
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
        return Collections.unmodifiableList((List<Integer>) value);
    }

    List<String> getStringsValue() {
        return Collections.unmodifiableList((List<String>) value);
    }

    String readValue(String commandLine) {
        Pattern pattern = Pattern.compile("^.*(-" + flag  + "(?:\\s*((?:[^-]+)|(?:-(?=\\d)[^-]*)+))*).*$");
        Matcher matcher = pattern.matcher(commandLine);
        if (!matcher.matches()) return commandLine;
        readValueFromMatcher(matcher);
        return commandLine.substring(0, matcher.start(1)) + commandLine.substring(matcher.end(1));
    }

    private void readValueFromMatcher(Matcher matcher) {
        this.value = isBooleanValueMissing(matcher) ? Boolean.valueOf(true) : parseValue(trim(matcher.group(2)));
    }

    private boolean isBooleanValueMissing(Matcher matcher) {
        return StringUtils.equals(type, "boolean") && isBlank(matcher.group(2));
    }
}
