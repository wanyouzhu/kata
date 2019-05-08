package com.example.kata.args;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.defaultString;
import static org.apache.commons.lang3.StringUtils.trim;

class Argument {
    private Flag flag;
    private ValueType type;
    private Value value;

    Argument(String segment) {
        this.flag = new Flag(getSchemaPart(segment, 0).charAt(0));
        this.type = ValueType.create(getSchemaPart(segment, 1));
        this.value = type.parseValue(getSchemaPart(segment, 2));
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
        extractValuePart(commandLine).map(type::parseValue).ifPresent(value -> this.value = value);
    }

    private Optional<String> extractValuePart(String commandLine) {
        Matcher matcher = getPattern().matcher(commandLine);
        return matcher.matches() ? Optional.of(defaultString(matcher.group(1))) : Optional.empty();
    }

    private Pattern getPattern() {
        return Pattern.compile("^.*-" + getFlag() + "(?:\\s((?:[^-]|-\\d)+))?.*$");
    }
}
