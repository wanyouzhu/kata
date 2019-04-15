package com.example.kata.args;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

class Args {
    private static final int EOF = -1;
    private final Schema schema;
    private final Map<Character, Argument> arguments;
    private final PushbackReader input;
    private int token;

    Args(Schema schema, String commandLine) {
        this.schema = requireNonNull(schema);
        this.input = new PushbackReader(new StringReader(commandLine), 256);
        this.arguments = parseArguments().stream().collect(Collectors.toMap(Argument::getFlag, x -> x));
    }

    Argument getArgument(char flag) {
        return arguments.get(flag);
    }

    Value getArgumentValue(char flag) {
        return getArgument(flag).getValue();
    }

    private List<Argument> parseArguments() {
        readNextToken();
        return parseSequenceOfArguments();
    }

    private List<Argument> parseSequenceOfArguments() {
        List<Argument> result = Lists.newArrayList();
        while (token != EOF) {
            result.add(parseArgument());
        }
        return result;
    }

    private Argument parseArgument() {
        skipWhitespaces();
        match('-');
        Option option = matchFlag();
        return new Argument(option, parseValue(option));
    }

    private Option matchFlag() {
        Option option = schema.getOption(token);
        if (option == null) throw new ArgsException("Unknown flag near: " + readAll());
        match(option.getFlag());
        return option;
    }

    private Value parseValue(Option option) {
        skipWhitespaces();
        switch (option.getType()) {
            case "bool": return matchBoolValue();
            case "int": return matchIntValue();
            case "string": return matchStringValue();
            case "[int]": return matchIntListValue();
            case "[string]": return matchStringListValue();
            default: throw new ArgsException("Unrecognized value near: " + readAll());
        }
    }

    private Value matchStringListValue() {
        List<String> valueTexts = Lists.newArrayList();
        valueTexts.add(matchString());
        skipWhitespaces();
        while (token == ',') {
            match(',');
            skipWhitespaces();
            valueTexts.add(matchString());
            skipWhitespaces();
        }
        return Value.ofStringList(valueTexts.toArray(new String[0]));
    }

    private Value matchIntListValue() {
        List<String> valueTexts = Lists.newArrayList();
        valueTexts.add(matchInt());
        skipWhitespaces();
        while (token == ',') {
            match(',');
            skipWhitespaces();
            valueTexts.add(matchInt());
            skipWhitespaces();
        }
        return Value.ofIntList(valueTexts.stream().map(Integer::parseInt).toArray(Integer[]::new));
    }

    private Value matchStringValue() {
        return Value.ofString(matchString());
    }

    private String matchString() {
        StringBuilder builder = new StringBuilder();
        while (token != EOF && token != ',' && !Character.isWhitespace(token)) {
            builder.append((char) token);
            match(token);
        }
        return builder.toString();
    }

    private Value matchIntValue() {
        return Value.parseInt(matchInt());
    }

    private String matchInt() {
        StringBuilder builder = new StringBuilder();
        while ((builder.length() == 0 && (token == '+' || token == '-')) || Character.isDigit(token)) {
            builder.append((char) token);
            match(token);
        }
        return builder.toString();
    }

    private Value matchBoolValue() {
        if (tryMatch("true")) return Value.ofBool(true);
        if (tryMatch("false")) return Value.ofBool(false);
        return Value.ofBool(true);
    }

    private boolean tryMatch(String text) {
        putBackPartialToken();
        char[] codePoints = text.toCharArray();
        char[] codePointsFromReader = read(codePoints.length);
        boolean result = Arrays.equals(codePointsFromReader, codePoints);
        if (!result) putBack(codePointsFromReader);
        readNextToken();
        return result;
    }

    private void putBack(char[] chars) {
        try {
            input.unread(chars);
        } catch (IOException e) {
            throw new RuntimeException("Can NOT read from input string!", e);
        }
    }

    private char[] read(int length) {
        try {
            char[] result = new char[length];
            int numberOfReadChars = input.read(result);
            if (numberOfReadChars == length) return result;
            return numberOfReadChars == -1 ? new char[0] : Arrays.copyOfRange(result, 0, numberOfReadChars);
        } catch (IOException e) {
            throw new RuntimeException("Can NOT read from input string!", e);
        }
    }

    private void match(int expected) {
        checkToken(expected);
        readNextToken();
    }

    private void checkToken(int expected) {
        if (token != expected) {
            throw new ArgsException("Bad token near: " + readAll());
        }
    }

    private void readNextToken() {
        try {
            token = input.read();
        } catch (IOException e) {
            throw new RuntimeException("Can NOT read from input string!", e);
        }
    }

    private void putBackPartialToken() {
        try {
            if (token != EOF) {
                input.unread(token);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can NOT read from input string!", e);
        }
    }

    private String readAll() {
        StringBuilder builder = new StringBuilder();
        putBackPartialToken();
        while (true) {
            readNextToken();
            if (token == EOF) break;
            builder.appendCodePoint(token);
        }
        return builder.toString();
    }

    private void skipWhitespaces() {
        while (Character.isWhitespace(token)) {
            readNextToken();
        }
    }
}

