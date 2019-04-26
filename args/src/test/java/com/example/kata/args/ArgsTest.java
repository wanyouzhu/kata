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
        assertThat(args.getIntegerValue('n'), is(8));
    }

    @Test
    public void can_parse_true_values_from_schema() {
        String schema = "n:boolean:true";
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getBooleanValue('n'), is(true));
    }

    @Test
    public void can_parse_false_values_from_schema() {
        String schema = "n:boolean:false";
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getBooleanValue('n'), is(false));
    }

    @Test(expected = ArgsException.class)
    public void should_reject_if_schema_contains_malformed_boolean_values() {
        String schema = "n:boolean:test";
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getBooleanValue('n'), is("No matter this!"));
    }

    @Test
    public void can_parse_string_values_from_schema() {
        String schema = "n:string:abc";
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getStringValue('n'), is("abc"));
    }

    @Test
    public void can_parse_integers_values_from_schema() {
        String schema = "n:integers:1,2";
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getIntegersValue('n'), is(ImmutableList.of(1, 2)));
    }

    @Test
    public void can_parse_strings_values_from_schema() {
        String schema = "n:strings:a,c";
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getStringsValue('n'), is(ImmutableList.of("a", "c")));
    }

    @Test
    public void can_parse_integer_values_from_command_line() {
        String schema = "n:integer:0";
        String commandLine = "-n 300";
        Args args = new Args(schema, commandLine);
        assertThat(args.getIntegerValue('n'), is(300));
    }

    @Test
    public void can_parse_flag_only_arguments_from_command_line() {
        String schema = "n:boolean:false";
        String commandLine = "-n";
        Args args = new Args(schema, commandLine);
        assertThat(args.getBooleanValue('n'), is(true));
    }

    @Test
    public void can_parse_negative_integer_values_from_command_line() {
        String schema = "n:integer:0";
        String commandLine = "-n -5";
        Args args = new Args(schema, commandLine);
        assertThat(args.getIntegerValue('n'), is(-5));
    }

    @Test
    public void can_parse_multiple_arguments_from_command_line() {
        String schema = "p:integer:0; t:string:x; s:integers:0";
        String commandLine = "-p -80 -t test -s 1,-2,3";
        Args args = new Args(schema, commandLine);
        assertThat(args.getIntegerValue('p'), is(-80));
        assertThat(args.getStringValue('t'), is("test"));
        assertThat(args.getIntegersValue('s'), is(ImmutableList.of(1, -2, 3)));
    }
}
