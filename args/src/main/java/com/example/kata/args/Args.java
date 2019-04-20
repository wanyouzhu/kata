package com.example.kata.args;

import com.google.common.collect.Maps;

import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.trim;

class Args {
    private final Schema schema;
    private final Map<Character, Value> values;
    private char currentFlag = 0;

    Args(String schema, String command) {
        this.schema = new Schema(schema);
        this.values = Maps.newHashMap();
        parseValues(command);
    }

    Value getOptionValue(char flag) {
        return values.getOrDefault(flag, schema.getOptionDefaultValue(flag));
    }

    private void parseValues(String command) {
        new Tokenizer(command).getTokens().forEach(this::collect);
    }

    private void endArgument(String token) {
        values.put(currentFlag, parseValue(token));
        currentFlag = 0;
    }

    private Value parseValue(String token) {
        if (schema.getOptionType(currentFlag).equals(ValueType.BOOLEAN) && isBlank(token)) return Value.ofBoolean(true);
        return new ValueParser().parse(token, schema.getOptionType(currentFlag));
    }

    private boolean isArgumentStarted() {
        return currentFlag != 0;
    }

    private void startArgument(String token) {
        this.currentFlag = trim(token).charAt(1);
    }

    private boolean isFlag(String token) {
        String normalized = trim(token);
        return normalized.length() == 2 && normalized.charAt(0) == '-';
    }

    private void collect(String token) {
        if (collectArgumentStart(token) || collectArgumentDelimiter(token) || collectEmptyArgument(token)) return;
        endArgument(token);
    }

    private boolean collectEmptyArgument(String token) {
        return !isArgumentStarted() && isBlank(token);
    }

    private boolean collectArgumentDelimiter(String token) {
        if (!isFlag(token) || !isArgumentStarted()) return false;
        endArgument("");
        startArgument(token);
        return true;
    }

    private boolean collectArgumentStart(String token) {
        if (!isFlag(token) || isArgumentStarted()) return false;
        startArgument(token);
        return true;
    }
}
