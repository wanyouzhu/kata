package com.example.kata.args;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ListValueParserTest {
    @Test
    public void testParse() {
        assertThat(parseListValue("3,5,6"), is(Optional.of(Value.ofList(ImmutableList.of(Value.ofNumber(3), Value.ofNumber(5), Value.ofNumber(6))))));
        assertThat(parseListValue("6,8,9"), is(Optional.of(Value.ofList(ImmutableList.of(Value.ofNumber(6), Value.ofNumber(8), Value.ofNumber(9))))));
        assertThat(parseListValue("3,not-a-number,3"), is(Optional.empty()));
        assertThat(parseListValue("3"), is(Optional.of(Value.ofList(ImmutableList.of(Value.ofNumber(3))))));
        assertThat(parseListValue("not-a-number"), is(Optional.empty()));
        assertThat(parseListValue("3 6"), is(Optional.of(Value.ofList(ImmutableList.of(Value.ofNumber(3))))));
        assertThat(parseListValue("3,6,"), is(Optional.empty()));
    }

    @Test
    public void testStreamConsumption() {
        assertThat(consumeListValue("3,5,6").getPosition(), is(5));
        assertThat(consumeListValue("3,not-a-number").getPosition(), is(0));
        assertThat(consumeListValue("3 6").getPosition(), is(1));
        assertThat(consumeListValue("3,6,").getPosition(), is(0));
    }

    private CharStream consumeListValue(String source) {
        CharStream input = CharStream.from(source);
        ValueParser.forListWithDefault(ValueParser.forNumberWithDefault(0)).parse(input);
        return input;
    }

    private Optional<Value> parseListValue(String source) {
        return ValueParser.forListWithDefault(ValueParser.forNumberWithDefault(0)).parse(CharStream.from(source));
    }
}
