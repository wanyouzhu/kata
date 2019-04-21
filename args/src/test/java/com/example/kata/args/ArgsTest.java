package com.example.kata.args;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ArgsTest {
    @Test
    public void can_parse_integer_arguments() {
        String schema = "p:integer:1080";
        String command = "-p 8080";
        Args args = new Args(schema, command);
        assertThat(args.getOptionValue('p'), is(Value.ofInteger(8080)));
    }

    @Test
    public void can_parse_boolean_arguments() {
        String schema = "v:boolean:false";
        String command = "-v true";
        Args args = new Args(schema, command);
        assertThat(args.getOptionValue('v'), is(Value.ofBoolean(true)));
    }

    @Test
    public void should_return_true_if_value_part_of_a_boolean_argument_is_missing() {
        String schema = "v:boolean:false";
        String command = "-v";
        Args args = new Args(schema, command);
        assertThat(args.getOptionValue('v'), is(Value.ofBoolean(true)));
    }

    @Test
    public void should_use_schema_default_value_while_missing_flag_in_command() {
        String schema = "p:integer:1080";
        String command = "";
        Args args = new Args(schema, command);
        assertThat(args.getOptionValue('p'), is(Value.ofInteger(1080)));
    }

    @Test
    public void can_parse_multiple_option_values() {
        String schema = "p:integer:1080; v:boolean:false; i:strings:test";
        String command = "-p 8888 -v true -i a.txt,b.txt";
        Args args = new Args(schema, command);
        assertThat(args.getOptionValue('p'), is(Value.ofInteger(8888)));
        assertThat(args.getOptionValue('v'), is(Value.ofBoolean(true)));
        assertThat(args.getOptionValue('i'), is(Value.ofStrings("a.txt", "b.txt")));
    }
}
