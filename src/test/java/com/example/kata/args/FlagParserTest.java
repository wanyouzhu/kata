package com.example.kata.args;

import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FlagParserTest {
    @Test
    public void testParse() {
        assertThat(parseFlag('t', "-t"), is(Optional.of(Flag.of('t'))));
        assertThat(parseFlag('p', "-p"), is(Optional.of(Flag.of('p'))));
        assertThat(parseFlag('t', "-p"), is(Optional.empty()));
        assertThat(parseFlag('t', "t"), is(Optional.empty()));
        assertThat(parseFlag('t', "+t"), is(Optional.empty()));
        assertThat(parseFlag('t', "-t tt"), is(Optional.of(Flag.of('t'))));
        assertThat(parseFlag('t', "   -t"), is(Optional.of(Flag.of('t'))));
    }

    @Test
    public void testStreamConsumption() {
        assertThat(consumeFlag('t', "-t").getPosition(), is(2));
        assertThat(consumeFlag('t', "-t tt").getPosition(), is(2));
        assertThat(consumeFlag('t', "-ttt").getPosition(), is(0));
        assertThat(consumeFlag('p', "p").getPosition(), is(0));
        assertThat(consumeFlag('s', " -s").getPosition(), is(3));
        assertThat(consumeFlag('s', "   -ttt").getPosition(), is(0));
    }

    private Optional<Flag> parseFlag(char t, String s) {
        return FlagParser.create(t).parse(CharStream.from(s));
    }

    private CharStream consumeFlag(char flag, String source) {
        CharStream stream = CharStream.from(source);
        FlagParser.create(flag).parse(stream);
        return stream;
    }
}