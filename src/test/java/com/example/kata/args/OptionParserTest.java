package com.example.kata.args;

import org.junit.Test;

import java.util.Optional;

import static com.example.kata.args.ValueParser.numberWithDefault;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class OptionParserTest {
    @Test
    public void testParse() {
        assertThat(OptionParser.of('t', numberWithDefault(6)).parse(CharStream.of("-t 2")), is(Optional.of(Option.of('t', Value.ofNumber(2)))));
        assertThat(OptionParser.of('t', numberWithDefault(6)).parse(CharStream.of("-p 1")), is(Optional.empty()));
        assertThat(OptionParser.of('t', numberWithDefault(6)).parse(CharStream.of("-t")), is(Optional.of(Option.of('t', Value.ofNumber(6)))));
    }
}