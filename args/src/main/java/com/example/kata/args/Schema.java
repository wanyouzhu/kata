package com.example.kata.args;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

class Schema {
    private final Map<Character, Option> options;

    Schema(Option... options) {
        this.options = Arrays.stream(options).collect(Collectors.toMap(Option::getFlag, option -> option));
    }

    void walkOptions(Consumer<Option> walker) {
        options.values().forEach(walker);
    }

    Option getOption(char flag) {
        return options.get(flag);
    }
}
