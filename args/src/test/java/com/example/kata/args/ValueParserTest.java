package com.example.kata.args;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ValueParserTest {
    @Test
    public void can_parse_integer_value() {
        String input = "5";
        Value value = new ValueParser().parse(input, ValueType.INTEGER);
        assertThat(value, is(Value.ofInteger(5)));
    }

    @Test
    public void can_parse_integer_value_with_minus_sign() {
        String input = "-5";
        Value value = new ValueParser().parse(input, ValueType.INTEGER);
        assertThat(value, is(Value.ofInteger(-5)));
    }

    @Test
    public void can_parse_integer_value_with_plus_sign() {
        String input = "+5";
        Value value = new ValueParser().parse(input, ValueType.INTEGER);
        assertThat(value, is(Value.ofInteger(5)));
    }

    @Test
    public void can_parse_true_boolean_value() {
        String input = "true";
        Value value = new ValueParser().parse(input, ValueType.BOOLEAN);
        assertThat(value, is(Value.ofBoolean(true)));
    }

    @Test
    public void can_parse_false_boolean_value() {
        String input = "false";
        Value value = new ValueParser().parse(input, ValueType.BOOLEAN);
        assertThat(value, is(Value.ofBoolean(false)));
    }

    @Test
    public void can_parse_single_word_string_value() {
        String input = "word";
        Value value = new ValueParser().parse(input, ValueType.STRING);
        assertThat(value, is(Value.ofString("word")));
    }

    @Test
    public void can_parse_multi_word_string_value() {
        String input = "multiple words";
        Value value = new ValueParser().parse(input, ValueType.STRING);
        assertThat(value, is(Value.ofString("multiple words")));
    }

    @Test
    public void can_parse_double_quoted_string_value() {
        String input = "\"that is a 'string' value\"";
        Value value = new ValueParser().parse(input, ValueType.STRING);
        assertThat(value, is(Value.ofString("that is a 'string' value")));
    }

    @Test
    public void can_parse_single_quoted_string_value() {
        String input = "'this is a \"string\" value'";
        Value value = new ValueParser().parse(input, ValueType.STRING);
        assertThat(value, is(Value.ofString("this is a \"string\" value")));
    }

    @Test
    public void can_parse_single_value_from_quoted_string_that_contains_double_quotation_mark() {
        String input = "\"this is a \\\"string\\\" value\"";
        Value value = new ValueParser().parse(input, ValueType.STRING);
        assertThat(value, is(Value.ofString("this is a \"string\" value")));
    }

    @Test
    public void can_parse_single_value_from_quoted_string_that_contains_single_quotation_mark() {
        String input = "'this is a \\'string\\' value'";
        Value value = new ValueParser().parse(input, ValueType.STRING);
        assertThat(value, is(Value.ofString("this is a 'string' value")));
    }

    @Test
    public void can_parse_integer_list_value() {
        String input = "1,2,3";
        Value value = new ValueParser().parse(input, ValueType.INTEGERS);
        assertThat(value, is(Value.ofIntegers(1, 2, 3)));
    }

    @Test
    public void can_parse_integer_list_value_with_whitespaces() {
        String input = "1, 2, 3";
        Value value = new ValueParser().parse(input, ValueType.INTEGERS);
        assertThat(value, is(Value.ofIntegers(1, 2, 3)));
    }

    @Test
    public void can_parse_string_list_value() {
        String input = "this,that";
        Value value = new ValueParser().parse(input, ValueType.STRINGS);
        assertThat(value, is(Value.ofStrings("this", "that")));
    }

    @Test
    public void can_parse_string_list_value_with_whitespaces() {
        String input = " this , that ";
        Value value = new ValueParser().parse(input, ValueType.STRINGS);
        assertThat(value, is(Value.ofStrings("this", "that")));
    }

    @Test
    public void can_parse_string_list_value_that_contains_list_delimiters() {
        String input = "'[this, is]', \"[that, is]\"";
        Value value = new ValueParser().parse(input, ValueType.STRINGS);
        assertThat(value, is(Value.ofStrings("[this, is]", "[that, is]")));
    }

    @Test
    public void can_parse_string_value_with_backslash() {
        String input = "C:\\Windows\\System.dll";
        Value value = new ValueParser().parse(input, ValueType.STRING);
        assertThat(value, is(Value.ofString("C:\\Windows\\System.dll")));
    }

    @Test
    public void can_parse_quoted_string_value_with_backslash() {
        String input = "\\'C:\\Windows\\System.dll\\'";
        Value value = new ValueParser().parse(input, ValueType.STRING);
        assertThat(value, is(Value.ofString("\\'C:\\Windows\\System.dll\\'")));
    }

    @Test
    public void can_parse_string_list_value_that_contains_backslashes() {
        String input = "'[this, is \\d]', \"[that \\s, is]\"";
        Value value = new ValueParser().parse(input, ValueType.STRINGS);
        assertThat(value, is(Value.ofStrings("[this, is d]", "[that s, is]")));
    }

    @Test
    public void backslashes_outside_quotation_should_NOT_be_treated_as_escape_chars() {
        String input = "this is a \\string\\ value";
        Value value = new ValueParser().parse(input, ValueType.STRING);
        assertThat(value, is(Value.ofString("this is a \\string\\ value")));
    }

    @Test
    public void backslashes_outside_quotation_should_NOT_be_treated_as_escape_chars_in_list_value() {
        String input = "c:\\a.txt, d:\\b.txt";
        Value value = new ValueParser().parse(input, ValueType.STRINGS);
        assertThat(value, is(Value.ofStrings("c:\\a.txt", "d:\\b.txt")));
    }
}
