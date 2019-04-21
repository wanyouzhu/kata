package com.example.kata.args;

import com.google.common.collect.Maps;
import com.google.common.collect.Queues;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.trim;

class Args {
    private final Schema schema;
    private Map<Character, Value> arguments = Maps.newHashMap();

    Args(String schema, String command) {
        this.schema = new Schema(schema);
        parseOptionValues(command);
    }

    private void parseOptionValues(String command) {
        if (isBlank(command)) return;
        ArrayDeque<String> queue = Queues.newArrayDeque(Arrays.asList(command.split(" ")));
        while (!queue.isEmpty()) {
            parseSingleOptionValue(queue);
        }
    }

    private void parseSingleOptionValue(ArrayDeque<String> queue) {
        char flag = parseFlag(queue);
        Value value = parseOptionValue(queue, flag);
        arguments.put(flag, value);
    }

    private Value parseOptionValue(ArrayDeque<String> queue, char flag) {
        if (missingBooleanValuePart(queue, flag)) return Value.ofBoolean(true);
        String valueString = queue.pollFirst();
        return parseOptionValue(flag, valueString);
    }

    private boolean missingBooleanValuePart(ArrayDeque<String> queue, char flag) {
        return getOptionType(flag).equals(ValueType.BOOLEAN) && (queue.isEmpty() || isFlag(queue.peekFirst()));
    }

    private char parseFlag(ArrayDeque<String> queue) {
        String token = queue.peekFirst();
        if (!isFlag(token)) throw new ArgsException("Flag required near: " + token);
        return getFlag(queue.pollFirst());
    }

    Value getOptionValue(char flag) {
        return arguments.getOrDefault(flag, schema.getOptionDefaultValue(flag));
    }

    private Value parseOptionValue(char flag, String token) {
        return new ValueParser().parse(token, getOptionType(flag));
    }

    private ValueType getOptionType(char flag) {
        return schema.getOptionType(flag);
    }

    private char getFlag(String token) {
        return trim(token).charAt(1);
    }

    private boolean isFlag(String token) {
        return trim(token).matches("-[a-zA-Z]");
    }
}
