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
        valueParsers.put(ValueType.BOOLEAN, this::matchBoolValue);
        valueParsers.put(ValueType.INTEGER, this::matchIntValue);
        valueParsers.put(ValueType.STRING, this::matchStringValue);
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
        tokenStream.skipWhitespaces();
        tokenStream.match('-');
        Option option = schema.getOption(tokenStream.getChar());
        if (option == null) throw new ArgsException("Unknown flag near: " + tokenStream.getRemains());
        tokenStream.match(option.getFlag());
        tokenStream.skipWhitespaces();
        result.add(new Argument(option.getFlag(), matchValue(option)));
    }

    private Value matchValue(Option option) {
        Supplier<Value> valueParser = valueParsers.get(option.getType());
        if (valueParser == null) throw new ArgsException("Unknown value type: " + option.getType());
        return valueParser.get();
    }

    private Value matchStringValue() {
        return Value.ofString(tokenStream.readString());
    }

    private Value matchIntValue() {
        return Value.ofInt(tokenStream.readInt());
    }

    private Value matchIntListValue() {
        return Value.ofIntList(matchIntList());
    }

    private List<Integer> matchIntList() {
        List<Integer> result = Lists.newArrayList();
        result.add(tokenStream.readInt());
        while (tokenStream.canMatch(',')) {
            tokenStream.match(',');
            result.add(tokenStream.readInt());
        }
        return result;
    }

    private Value matchStringListValue() {
        return Value.ofStringList(matchStringList());
    }

    private List<String> matchStringList() {
        List<String> result = Lists.newArrayList();
        result.add(tokenStream.readString());
        while (tokenStream.canMatch(',')) {
            tokenStream.match(',');
            result.add(tokenStream.readString());
        }
        return result;
    }

    private Value matchBoolValue() {
        boolean matchedTrue = tokenStream.canMatch("true");
        if (matchedTrue) tokenStream.match("true");
        boolean matchedFalse = tokenStream.canMatch("false");
        if (matchedFalse) tokenStream.match("false");
        return Value.ofBool(matchedTrue || !matchedFalse);
    }

}
