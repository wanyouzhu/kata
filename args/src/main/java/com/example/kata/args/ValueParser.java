package com.example.kata.args;

import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.apache.commons.lang3.StringUtils.*;

class ValueParser {
    Value parse(String input, ValueType type) {
        switch (type) {
            case INTEGER: return Value.ofInteger(parseInteger(input));
            case BOOLEAN: return Value.ofBoolean(parseBoolean(input));
            case STRING: return Value.ofString(parseString(input));
            case INTEGERS: return parseIntegers(input);
            case STRINGS: return parseStrings(input);
            default: throw new ArgsException("Unsupported value type: " + type);
        }
    }

    private int parseInteger(String input) {
        try {
            return Integer.parseInt(trim(input));
        } catch (NumberFormatException e) {
            throw new ArgsException("Invalid value near: " + input, e);
        }
    }

    private boolean parseBoolean(String input) {
        String normalized = lowerCase(trim(input));
        if (!isBooleanLiteral(normalized)) throw new ArgsException("Invalid value near: " + input);
        return "true".equals(normalized);
    }

    private boolean isBooleanLiteral(String normalized) {
        return ImmutableSet.of("true", "false").contains(normalized);
    }

    private Value parseStrings(String input) {
        Stream<String> stream = new Tokenizer(input).getTokens().stream();
        return Value.ofStrings(stream.map(this::parseString).toArray(String[]::new));
    }

    private Value parseIntegers(String input) {
        Stream<String> stream = Arrays.stream(split(input, ','));
        return Value.ofIntegers(stream.map(StringUtils::trim).map(Integer::parseInt).toArray(Integer[]::new));
    }

    private String parseString(String input) {
        String trimmed = trim(input);
        String mark = getQuotationMark(trimmed);
        return mark.length() > 0 ? parseQuotedString(trimmed, mark) : trimmed;
    }

    private String parseQuotedString(String trimmed, String mark) {
        return strip(trimmed, mark).replaceAll("\\\\(.)", "$1");
    }

    private String getQuotationMark(String text) {
        return text.length() > 0 && isQuotationMark(text.charAt(0)) ? substring(text, 0, 1) : "";
    }

    private boolean isQuotationMark(int mark) {
        return mark == '\'' || mark == '"';
    }
}
