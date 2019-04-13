package com.example.kata.args;

import com.google.common.collect.Sets;
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
                OptionParser.create(FlagParser.create('b'), ValueParser.forBooleanWithDefault(false)),
                OptionParser.create(FlagParser.create('p'), ValueParser.forNumberWithDefault(0)),
                OptionParser.create(FlagParser.create('l'), ValueParser.forListWithDefault(ValueParser.forNumberWithDefault(0))),
                OptionParser.create(FlagParser.create('s'), ValueParser.forStringWithDefault(""))
        );
    }

    @Test
    public void testParse() {
        assertThat(parse("-b -p 5 -l 3,5,8"), is(setOf(
                boolOption('b', true),
                numberOption('p', 5),
                stringOption('s', ""),
                listOfNumbersOption('l', 3, 5, 8)))
        );
        assertThat(parse("-l 9, 12 -s this-is-a-string"), is(setOf(
                boolOption('b', false),
                numberOption('p', 0),
                stringOption('s', "this-is-a-string"),
                listOfNumbersOption('l', 9, 12)))
        );
        assertThat(parse(""), is(setOf(
                boolOption('b', false),
                numberOption('p', 0),
                stringOption('s', ""),
                listOfNumbersOption('l')))
        );
    }

    private <T> Set<T> setOf(T... elements) {
        return Sets.newHashSet(elements);
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

    private Option stringOption(char flag, String value) {
        return Option.of(flag, Value.ofString(value));
    }

    private Set<Option> parse(String s) {
        return parser.parse(CharStream.from(s));
    }
}
