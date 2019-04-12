package com.example.kata.args;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

class ArgsParser {
    private final ArrayList<OptionParser> optionParsers;

    ArgsParser(OptionParser... optionParsers) {
        this.optionParsers = Lists.newArrayList(optionParsers);
    }

    Set<Option> parse(CharStream input) {
        Set<Option> result = mergeWithDefault(parseOptions(input));
        requireAllInputConsumed(input);
        return result;
    }

    private Set<Option> mergeWithDefault(Set<Option> options) {
        Map<Flag, Option> result = optionParsers.stream().map(OptionParser::getDefault).collect(Collectors.toMap(Option::getFlag, x -> x));
        options.forEach(x -> {
            if (result.containsKey(x.getFlag())) result.put(x.getFlag(), x);
        });
        return Sets.newHashSet(result.values());
    }

    private Set<Option> parseOptions(CharStream input) {
        Set<Option> result = Sets.newHashSet();
        parseOptions(input, result);
        return result;
    }

    private void parseOptions(CharStream input, Set<Option> result) {
        while (true) {
            Optional<Option> option = parseOption(input);
            if (!option.isPresent()) break;
            result.add(option.get());
        }
    }

    private Optional<Option> parseOption(CharStream input) {
        return optionParsers.stream().map(it -> it.parse(input)).filter(Optional::isPresent).map(Optional::get).findFirst();
    }

    private void requireAllInputConsumed(CharStream input) {
        if (!input.eof()) {
            throw new ArgsException(String.format("Unprocessable fragment near: '%s'", input.read(10)));
        }
    }
}
