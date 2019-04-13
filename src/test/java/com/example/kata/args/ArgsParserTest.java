package com.example.kata.args;

import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static com.example.kata.args.Option.*;
import static com.example.kata.args.ValueParser.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ArgsParserTest {
    private ArgsParser parser;

    @Before
    public void setup() {
        parser = new ArgsParser(
                OptionParser.of('b', booleanWithDefault(false)),
                OptionParser.of('p', numberWithDefault(0)),
                OptionParser.of('l', listWithDefault(numberWithDefault(0))),
                OptionParser.of('s', stringWithDefault(""))
        );
    }

    @Test
    public void testParse() {
        assertThat(parse("-b -p 5 -l 3,5,8"), is(setOf(ofBoolean('b', true), ofNumber('p', 5), ofString('s', ""), ofList('l', 3, 5, 8))));
        assertThat(parse("-b"), is(setOf(ofBoolean('b', true), ofNumber('p', 0), ofString('s', ""), ofList('l'))));
        assertThat(parse(""), is(setOf(ofBoolean('b', false), ofNumber('p', 0), ofString('s', ""), ofList('l'))));
    }

    @SafeVarargs
    private final <T> Set<T> setOf(T... elements) {
        return Sets.newHashSet(elements);
    }

    private Set<Option> parse(String source) {
        return parser.parse(CharStream.of(source));
    }
}
