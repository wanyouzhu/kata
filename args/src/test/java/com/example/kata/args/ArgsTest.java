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
    public void can_parse_malformed_boolean_values_from_schema() {
        String schema = "n:boolean:malformed";
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getBooleanValue('n'), is(false));
    }

    @Test
    public void can_parse_string_values_from_schema() {
        String schema = "n:string:a";
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getStringValue('n'), is("a"));
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
        String schema = "n:strings:a,b";
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getStringsValue('n'), is(ImmutableList.of("a", "b")));
    }

    @Test
    public void can_parse_missing_boolean_values_from_command_line() {
        String schema = "n:boolean:false";
        String commandLine = "-n";
        Args args = new Args(schema, commandLine);
        assertThat(args.getBooleanValue('n'), is(true));
    }

    @Test
    public void can_parse_values_from_command_line() {
        String schema = "n:integer:0";
        String commandLine = "-n 300";
        Args args = new Args(schema, commandLine);
        assertThat(args.getIntegerValue('n'), is(300));
    }

    @Test
    public void can_parse_multiple_values_from_command_line() {
        String schema = "n:integer:0; b:boolean:false; i:strings:a,b,c";
        String commandLine = "-n 300 -b true";
        Args args = new Args(schema, commandLine);
        assertThat(args.getIntegerValue('n'), is(300));
        assertThat(args.getBooleanValue('b'), is(true));
        assertThat(args.getStringsValue('i'), is(ImmutableList.of("a", "b", "c")));
    }

    @Test
    public void can_parse_negative_integer_values_from_command_line() {
        String schema = "n:integer:0; b:boolean:false";
        String commandLine = "-n -300 -b";
        Args args = new Args(schema, commandLine);
        assertThat(args.getIntegerValue('n'), is(-300));
    }
}
