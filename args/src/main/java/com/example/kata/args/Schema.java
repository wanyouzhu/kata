package com.example.kata.args;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.trim;

class Schema {
    private Map<Character, Option> options = Maps.newHashMap();

    Schema(String input) {
        Arrays.stream(input.split(";")).forEach(this::parseSingleOption);
    }

    Option getOption(char flag) {
        if (!options.containsKey(flag)) throw new ArgsException("No such option:" + flag);
        return options.get(flag);
    }

    ValueType getOptionType(char flag) {
        return getOption(flag).getType();
    }

    Value getOptionDefaultValue(char flag) {
        return getOption(flag).getDefaultValue();
    }

    private Option parseOption(String input) {
        String[] strings = input.split(":");
        if (strings.length != 3) throw new ArgsException("Bad schema!");
        return new Option(parseFlag(strings[0]), parseDefaultValue(strings[1], strings[2]));
    }

    private char parseFlag(String string) {
        if (trim(string).length() != 1) throw new ArgsException("Bad schema: wrong flag!");
        return trim(string).charAt(0);
    }

    private Value parseDefaultValue(String type, String valueString) {
        switch (trim(type)) {
            case "integer": return new ValueParser().parse(valueString, ValueType.INTEGER);
            case "boolean": return new ValueParser().parse(valueString, ValueType.BOOLEAN);
            case "string": return new ValueParser().parse(valueString, ValueType.STRING);
            case "integers": return new ValueParser().parse(valueString, ValueType.INTEGERS);
            case "strings": return new ValueParser().parse(valueString, ValueType.STRINGS);
            default: throw new ArgsException("Unrecognized option type: " + type);
        }
    }

    private void parseSingleOption(String input) {
        Option option = parseOption(input);
        options.put(option.getFlag(), option);
    }
}
