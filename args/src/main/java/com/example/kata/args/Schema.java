package com.example.kata.args;

import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.trim;

class Schema {
    private final Map<Character, Option> options = Maps.newHashMap();

    Schema(String input) {
        splitIntoSegments(input).forEach(this::parseSingleOption);
    }

    Option getOption(char flag) {
        return options.get(flag);
    }

    private List<String> splitIntoSegments(String input) {
        return new Tokenizer(input, ';').getTokens();
    }

    private void parseSingleOption(String segment) {
        collectOption(parseOptionFromTokens(segment, splitIntoTokens(segment)));
    }

    private Option parseOptionFromTokens(String segment, List<String> tokens) {
        if (tokens.size() != 3) throw new ArgsException("Bad schema near: " + segment);
        return new Option(parseFlag(segment, tokens.get(0)), parseValue(trim(tokens.get(1)), tokens.get(2)));
    }

    private Value parseValue(String type, String valueText) {
        switch (type) {
            case "integer": return parseValue(valueText, ValueType.INTEGER);
            case "boolean": return parseValue(valueText, ValueType.BOOLEAN);
            case "string": return parseValue(valueText, ValueType.STRING);
            case "integers": return parseValue(valueText, ValueType.INTEGERS);
            case "strings": return parseValue(valueText, ValueType.STRINGS);
            default: throw new ArgsException("Unrecognized value type: " + type);
        }
    }

    private Value parseValue(String input, ValueType integer) {
        return new ValueParser().parse(input, integer);
    }

    private char parseFlag(String segment, String flagText) {
        String normalized = trim(flagText);
        if (normalized.length() != 1) throw new ArgsException("Bad schema near: " + segment);
        return normalized.charAt(0);
    }

    private void collectOption(Option option) {
        options.put(option.getFlag(), option);
    }

    private List<String> splitIntoTokens(String segment) {
        return new Tokenizer(segment, ':').getTokens();
    }
}
