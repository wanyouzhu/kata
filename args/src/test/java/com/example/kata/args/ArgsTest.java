package com.example.kata.args;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class ArgsTest {
    @Test
    public void number_of_arguments_should_be_correct() {
        String schema = "n:integer:1; v:integer:5";
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getNumberOfArguments(), is(2));
    }

    @Test
    public void can_parse_integer_arguments_from_schema() {
        String schema = "n:integer:1";
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is(1));
    }

    @Test
    public void can_parse_boolean_arguments_from_schema_that_contains_true_values() {
        String schema = "n:boolean:true";
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is(true));
    }

    @Test
    public void can_parse_boolean_arguments_from_schema_that_contains_false_values() {
        String schema = "n:boolean:false";
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is(false));
    }

    @Test(expected = ArgsException.class)
    public void should_reject_schemas_that_contain_malformed_boolean_value() {
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
    public void can_parse_integers_arguments_from_schema() {
        String schema = "n:integers:5,2";
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is(Arrays.asList(5, 2)));
    }

    @Test
    public void can_parse_strings_arguments_from_schema() {
        String schema = "n:strings:x,y";
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is(Arrays.asList("x", "y")));
    }

    @Test
    public void can_resolve_argument_values_from_command_line() {
        String schema = "n:integer:0";
        String commandLine = "-n 100";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is(100));
    }

    @Test
    public void can_resolve_argument_values_from_flag_only_command_line() {
        String schema = "n:boolean:false";
        String commandLine = "-n";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is(true));
    }

    @Test
    public void can_resolve_argument_values_from_command_line_with_negative_integers() {
        String schema = "n:integer:0";
        String commandLine = "-n -50";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is(-50));
    }

    @Test
    public void can_resolve_multiple_argument_values_from_command_line() {
        String schema = "n:integer:0; x:boolean:false; w:integers:0";
        String commandLine = "-w 50,-30,-88 -n -10 -x";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is(-10));
        assertThat(args.getValue('x'), is(true));
        assertThat(args.getValue('w'), is(Arrays.asList(50, -30, -88)));
    }
}
