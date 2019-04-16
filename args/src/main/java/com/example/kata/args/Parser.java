package com.example.kata.args;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

class Parser {
    private final Schema schema;
    private final TokenStream tokenStream;
    private final Map<ValueType, Supplier<Value>> valueParsers;

    Parser(Schema schema, String commandLine) {
        this.schema = schema;
        this.tokenStream = new TokenStream(commandLine);
        this.valueParsers = Maps.newHashMap();
        initializeValueParsers();
    }

    private void initializeValueParsers() {
        initializeSingleValueParsers();
        initializeListValueParsers();
    }

    private void initializeSingleValueParsers() {
        valueParsers.put(ValueType.BOOLEAN, this::matchBoolValue);
        valueParsers.put(ValueType.INTEGER, this::matchIntValue);
        valueParsers.put(ValueType.STRING, this::matchStringValue);
    }

    private void initializeListValueParsers() {
        valueParsers.put(ValueType.INTEGER_LIST, this::matchIntListValue);
        valueParsers.put(ValueType.STRING_LIST, this::matchStringListValue);
    }

    List<Argument> parse() {
        List<Argument> result = Lists.newArrayList();
        parseSequenceOfArguments(result);
        return result;
    }

    private void parseSequenceOfArguments(List<Argument> result) {
        while (!tokenStream.eof()) {
            parseArgument(result);
        }
    }

    private void parseArgument(List<Argument> result) {
        matchFlagPrefix();
        Option option = matchFlag();
        Value value = matchValue(option);
        result.add(new Argument(option.getFlag(), value));
    }

    private Option matchFlag() {
        Option option = schema.getOption(tokenStream.getChar());
        if (option == null) throw new ArgsException("Unknown flag near: " + tokenStream.getRemains());
        tokenStream.match(option.getFlag());
        return option;
    }

    private void matchFlagPrefix() {
        tokenStream.skipWhitespaces();
        tokenStream.match('-');
    }

    private Value matchValue(Option option) {
        Supplier<Value> valueParser = valueParsers.get(option.getType());
        if (valueParser == null) throw new ArgsException("Unknown value type: " + option.getType());
        tokenStream.skipWhitespaces();
        return valueParser.get();
    }

    private Value matchStringValue() {
        return Value.ofString(tokenStream.readString());
    }

    private Value matchIntValue() {
        return Value.ofInt(tokenStream.readInt());
    }

    private Value matchIntListValue() {
        return Value.ofIntList(matchList(tokenStream::readInt));
    }

    private <T> List<T> matchList(Supplier<T> tokenParser) {
        List<T> result = Lists.newArrayList();
        result.add(tokenParser.get());
        matchListTailElements(tokenParser, result);
        return result;
    }

    private <T> void matchListTailElements(Supplier<T> tokenParser, List<T> result) {
        while (tokenStream.canMatch(',')) {
            tokenStream.match(',');
            result.add(tokenParser.get());
        }
    }

    private Value matchStringListValue() {
        return Value.ofStringList(matchList(tokenStream::readString));
    }

    private Value matchBoolValue() {
        return Value.ofBool(tokenStream.tryMatch("true") || !tokenStream.tryMatch("false"));
    }
}
