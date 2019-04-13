package com.example.kata.args;

import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class NumberValueParserTest {
    @Test
    public void testParse() {
        assertThat(parseNumberValue("123"), is(Optional.of(Value.ofNumber(123))));
        assertThat(parseNumberValue("8848"), is(Optional.of(Value.ofNumber(8848))));
        assertThat(parseNumberValue("0"), is(Optional.of(Value.ofNumber(0))));
        assertThat(parseNumberValue("8 "), is(Optional.of(Value.ofNumber(8))));
        assertThat(parseNumberValue(" 8"), is(Optional.of(Value.ofNumber(8))));
        assertThat(parseNumberValue("88t"), is(Optional.of(Value.ofNumber(88))));
        assertThat(parseNumberValue("not-number"), is(Optional.empty()));
    }

    @Test
    public void testStreamConsumption() {
        assertThat(consumeNumberValue("123").getPosition(), is(3));
        assertThat(consumeNumberValue("0").getPosition(), is(1));
        assertThat(consumeNumberValue(" 8").getPosition(), is(2));
        assertThat(consumeNumberValue("not-number").getPosition(), is(0));
        assertThat(consumeNumberValue(" not-number").getPosition(), is(0));
    }

    private CharStream consumeNumberValue(String source) {
        CharStream input = CharStream.of(source);
        ValueParser.numberWithDefault(0).parse(input);
        return input;
    }

    private Optional<Value> parseNumberValue(String source) {
        return ValueParser.numberWithDefault(0).parse(CharStream.of(source));
    }
}
