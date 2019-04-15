package com.example.kata.args;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Schema {
    private final Pattern pattern;
    private final String schemaText;
    private final Map<Integer, Option> options;

    Schema(String schemaText) {
        this.pattern = compileSchemaPattern();
        this.schemaText = schemaText;
        this.options = parseSchema();
    }

    private Pattern compileSchemaPattern() {
        return Pattern.compile("^\\s*([a-zA-Z])\\s*:\\s*(bool|int|string|\\[int]|\\[string])\\s*:\\s*([^:]*)\\s*$");
    }

    private Map<Integer, Option> parseSchema() {
        Map<Integer, Option> result = Maps.newLinkedHashMap();
        for (String fragment : StringUtils.split(schemaText, ';')) {
            Option parsed = extractOptions(matchSchema(fragment));
            result.put((int) parsed.getFlag(), parsed);
        }
        return result;
    }

    private Matcher matchSchema(String fragment) {
        Matcher matcher = pattern.matcher(fragment);
        if (!matcher.matches()) throw new ArgsException("The given schema is malformed.");
        return matcher;
    }

    private Option extractOptions(Matcher matcher) {
        char flag = matcher.group(1).charAt(0);
        String type = matcher.group(2);
        String valueString = matcher.group(3);
        return new Option(flag, getDefaultValue(type, StringUtils.trim(valueString)));
    }

    private Value getDefaultValue(String type, String valueString) {
        switch (type) {
            case "bool": return Value.parseBool(valueString);
            case "int": return Value.parseInt(valueString);
            case "string": return Value.ofString(valueString);
            case "[int]": return Value.parseIntList(valueString);
            case "[string]": return Value.parseStringList(valueString);
            default: throw new RuntimeException("Unknown type: " + type);
        }
    }

    Option getOption(int flag) {
        return options.get(flag);
    }
}