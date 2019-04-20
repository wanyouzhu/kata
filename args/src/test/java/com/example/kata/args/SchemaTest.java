package com.example.kata.args;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SchemaTest {
    @Test
    public void can_parse_integer_option() {
        String input = "p:integer:5";
        Schema schema = new Schema(input);
        assertThat(schema.getOption('p'), is(Option.ofInteger('p', 5)));
    }

    @Test(expected = ArgsException.class)
    public void should_throw_exception_while_the_input_string_missing_default_value_part() {
        String input = "t:integer:";
        new Schema(input);
    }

    @Test(expected = ArgsException.class)
    public void should_throw_exception_while_the_input_string_missing_type_part() {
        String input = "t:";
        new Schema(input);
    }

    @Test
    public void can_parse_boolean_option() {
        String input = "p:boolean:true";
        Schema schema = new Schema(input);
        assertThat(schema.getOption('p'), is(Option.ofBoolean('p', true)));
    }

    @Test
    public void can_parse_string_option() {
        String input = "p:string:foo";
        Schema schema = new Schema(input);
        assertThat(schema.getOption('p'), is(Option.ofString('p', "foo")));
    }

    @Test
    public void can_parse_string_option_from_the_input_string_missing_default_value_part() {
        String input = "p:string:";
        Schema schema = new Schema(input);
        assertThat(schema.getOption('p'), is(Option.ofString('p', "")));
    }

    @Test
    public void can_parse_integer_list_option() {
        String input = "p:integers:77,88,99";
        Schema schema = new Schema(input);
        assertThat(schema.getOption('p'), is(Option.ofIntegers('p', 77, 88, 99)));
    }

    @Test
    public void can_parse_string_list_option() {
        String input = "p:strings:test,to,die";
        Schema schema = new Schema(input);
        assertThat(schema.getOption('p'), is(Option.ofStrings('p', "test", "to", "die")));
    }

    @Test
    public void can_parse_option_from_the_input_contains_whitespaces() {
        String input = "t : integer : 8";
        Schema schema = new Schema(input);
        assertThat(schema.getOption('t'), is(Option.ofInteger('t', 8)));
    }

    @Test
    public void can_parse_option_from_input_contains_delimiter() {
        String input = "p:strings:'test:to:die'";
        Schema schema = new Schema(input);
        assertThat(schema.getOption('p'), is(Option.ofStrings('p', "test:to:die")));
    }

    @Test
    public void can_parse_multiple_option() {
        String input = "x:string:three; v:boolean:false";
        Schema schema = new Schema(input);
        assertThat(schema.getOption('x'), is(Option.ofString('x', "three")));
        assertThat(schema.getOption('v'), is(Option.ofBoolean('v', false)));
    }

    @Test(expected = ArgsException.class)
    public void should_throw_exception_while_the_input_string_contains_empty_trailing_segment() {
        String input = "p:integer:3;";
        new Schema(input);
    }

    @Test(expected = ArgsException.class)
    public void should_throw_exception_while_the_input_string_contains_empty_leading_segment() {
        String input = ";p:integer:3";
        new Schema(input);
    }

    @Test
    public void can_parse_multiple_option_from_the_input_contains_segment_delimiter() {
        String input = "i:strings:'a;b',b; t:integers:1";
        Schema schema = new Schema(input);
        assertThat(schema.getOption('i'), is(Option.ofStrings('i', "a;b", "b")));
        assertThat(schema.getOption('t'), is(Option.ofIntegers('t', 1)));
    }
}
