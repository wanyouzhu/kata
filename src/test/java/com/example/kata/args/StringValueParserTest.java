package com.example.kata.args;

import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StringValueParserTest {
    @Test
    public void testParse() {
        assertThat(parseStringValue("abc"), is(Optional.of(Value.ofString("abc"))));
        assertThat(parseStringValue("123"), is(Optional.of(Value.ofString("123"))));
        assertThat(parseStringValue(" 1"), is(Optional.of(Value.ofString("1"))));
        assertThat(parseStringValue("123 456"), is(Optional.of(Value.ofString("123"))));
        assertThat(parseStringValue(""), is(Optional.empty()));
    }

    @Test
    public void testStreamConsumption() {
        assertThat(consumeStringValue("abc").getPosition(), is(3));
        assertThat(consumeStringValue("a").getPosition(), is(1));
        assertThat(consumeStringValue("abc abc").getPosition(), is(3));
        assertThat(consumeStringValue(" abc").getPosition(), is(4));
        assertThat(consumeStringValue("").getPosition(), is(0));
        assertThat(consumeStringValue(" ").getPosition(), is(0));
    }

    private Optional<Value> parseStringValue(String source) {
        return ValueParser.forString("").parse(CharStream.from(source));
    }

    private CharStream consumeStringValue(String source) {
        CharStream input = CharStream.from(source);
        ValueParser.forString("").parse(input);
        return input;
    }
}
