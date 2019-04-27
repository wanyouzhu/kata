package com.example.kata.args;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class ArgsTest {
    @Test
    public void number_of_parsed_arguments_should_be_correct() {
        String schema = "n:integer:1; v:integer:5";
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getNumberOfArguments(), is(2));
    }

    @Test
    public void can_parse_integer_values_from_schema() {
        String schema = "n:integer:8";
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is(8));
    }

    @Test
    public void can_parse_true_boolean_values_from_schema() {
        String schema = "n:boolean:true";
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is(true));
    }

    @Test
    public void can_parse_false_boolean_values_from_schema() {
        String schema = "n:boolean:false";
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is(false));
    }

    @Test(expected = ArgsException.class)
    public void should_reject_parsing_if_schema_contains_malformed_boolean_value() {
        String schema = "n:boolean:malformed";
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is("No matter this!"));
    }

    @Test
    public void can_parse_string_values_from_schema() {
        String schema = "n:string:x";
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is("x"));
    }

    @Test
    public void can_parse_integers_values_from_schema() {
        String schema = "n:integers:1,2";
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is(ImmutableList.of(1, 2)));
    }

    @Test
    public void can_parse_strings_values_from_schema() {
        String schema = "n:strings:a,b";
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is(ImmutableList.of("a", "b")));
    }

    @Test
    public void can_parse_values_from_command_line() {
        String schema = "n:integer:0";
        String commandLine = "-n 800";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is(800));
    }

    @Test
    public void can_parse_default_boolean_values_from_flag_only_command_line() {
        String schema = "n:boolean:false";
        String commandLine = "-n";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is(true));
    }

    @Test
    public void can_parse_negative_integer_values_from_schema() {
        String schema = "n:integer:0";
        String commandLine = "-n -100";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is(-100));
    }

    @Test
    public void can_parse_multiple_arguments_from_command_line() {
        String schema = "n:integer:0; v:boolean:false; x:strings:x ";
        String commandLine = "-n -100 -v -x x,y,z";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is(-100));
        assertThat(args.getValue('v'), is(true));
        assertThat(args.getValue('x'), is(ImmutableList.of("x", "y", "z")));
    }
}
