package com.example.kata.args;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ValueParserTest {
    @Test
    public void can_parse_integers() {
        String input = "8080";
        Value parsed = new ValueParser().parse(input, ValueType.INTEGER);
        assertThat(parsed, is(Value.ofInteger(8080)));
    }

    @Test
    public void can_parse_true_values() {
        String input = "true";
        Value parsed = new ValueParser().parse(input, ValueType.BOOLEAN);
        assertThat(parsed, is(Value.ofBoolean(true)));
    }

    @Test
    public void can_parse_false_values() {
        String input = "false";
        Value parsed = new ValueParser().parse(input, ValueType.BOOLEAN);
        assertThat(parsed, is(Value.ofBoolean(false)));
    }

    @Test
    public void can_parse_string_values() {
        String input = "a.txt";
        Value parsed = new ValueParser().parse(input, ValueType.STRING);
        assertThat(parsed, is(Value.ofString("a.txt")));
    }

    @Test
    public void can_parse_integer_list_values() {
        String input = "1,2,3";
        Value parsed = new ValueParser().parse(input, ValueType.INTEGERS);
        assertThat(parsed, is(Value.ofIntegers(1, 2, 3)));
    }

    @Test
    public void can_parse_string_list_values() {
        String input = "a.log,b.log";
        Value parsed = new ValueParser().parse(input, ValueType.STRINGS);
        assertThat(parsed, is(Value.ofStrings("a.log", "b.log")));
    }
}