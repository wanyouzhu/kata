package com.example.kata.args;

import com.google.common.collect.ImmutableSet;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ArgsTest {
    private ArgsParser parser;

    @Before
    public void setup() {
        parser = new ArgsParser(
                OptionParser.create(FlagParser.create('b'), ValueParser.forBoolean(false)),
                OptionParser.create(FlagParser.create('p'), ValueParser.forNumber(0)),
                OptionParser.create(FlagParser.create('l'), ValueParser.forList(ValueParser.forNumber(0)))
        );
    }

    @Test
    public void testParse() {
        assertThat(parse("-b -p 5 -l 3,5,8"), is(ImmutableSet.of(boolOption('b', true), numberOption('p', 5), listOfNumbersOption('l', 3, 5, 8))));
        assertThat(parse(""), is(ImmutableSet.of(boolOption('b', false), numberOption('p', 0), listOfNumbersOption('l'))));
    }

    private Option listOfNumbersOption(char flag, Integer... values) {
        return Option.of(flag, Value.ofList(Arrays.stream(values).map(Value::ofNumber).collect(Collectors.toList())));
    }

    private Option numberOption(char flag, int value) {
        return Option.of(flag, Value.ofNumber(value));
    }

    private Option boolOption(char flag, boolean value) {
        return Option.of(flag, Value.ofBoolean(value));
    }

    private Set<Option> parse(String s) {
        return parser.parse(CharStream.from(s));
    }
}
