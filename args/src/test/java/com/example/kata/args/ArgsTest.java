package com.example.kata.args;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class ArgsTest {
    @Test
    public void number_of_arguments_should_be_correct() {
        String schema = "n:integer:5; t:integer:1";
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getNumberOfArguments(), is(2));
    }

    @Test
    public void can_parse_integer_arguments_from_schema() {
        String schema = "n:integer:5";
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is(5));
    }

    @Test
    public void can_parse_boolean_arguments_from_schema_that_contains_true() {
        String schema = "n:boolean:true";
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is(true));
    }

    @Test
    public void can_parse_boolean_arguments_from_schema_that_contains_false() {
        String schema = "n:boolean:false";
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is(false));
    }

    @Test(expected = ArgsException.class)
    public void should_reject_schemas_that_contains_malformed_boolean_values() {
        String schema = "n:boolean:malformed";
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is("NO MATTER THIS!"));
    }

    @Test
    public void can_parse_string_arguments_from_schema() {
        String schema = "n:string:x";
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is("x"));
    }

    @Test
    public void can_parse_strings_arguments_from_schema() {
        String schema = "n:strings:x,y";
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is(Arrays.asList("x", "y")));
    }

    @Test
    public void can_parse_integers_arguments_from_schema() {
        String schema = "n:integers:5,1";
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is(Arrays.asList(5, 1)));
    }

    @Test
    public void can_resolve_values_from_command_line() {
        String schema = "n:integers:0";
        String commandLine = "-n 8,2";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is(Arrays.asList(8, 2)));
    }

    @Test
    public void can_resolve_values_from_command_line_that_contains_negative_integers() {
        String schema = "n:integers:0";
        String commandLine = "-n -5,2";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is(Arrays.asList(-5, 2)));
    }

    @Test
    public void can_resolve_values_from_flag_only_command_line() {
        String schema = "n:boolean:false";
        String commandLine = "-n";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is(true));
    }

    @Test
    public void can_resolve_multiple_values_from_command_line() {
        String schema = "n:integers:0; t:boolean:false; x:strings:x";
        String commandLine = "-t -n -8,2 -x 5-5,6+6";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is(Arrays.asList(-8, 2)));
        assertThat(args.getValue('t'), is(true));
        assertThat(args.getValue('x'), is(Arrays.asList("5-5", "6+6")));
    }
}
