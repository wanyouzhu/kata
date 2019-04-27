package com.example.kata.args;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
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
    public void can_parse_true_values_from_schema() {
        String schema = "n:boolean:true";
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is(true));
    }

    @Test
    public void can_parse_false_values_from_schema() {
        String schema = "n:boolean:false";
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is(false));
    }

    @Test(expected = ArgsException.class)
    public void should_reject_schema_with_malformed_boolean_values() {
        String schema = "n:boolean:malformed";
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is("No matter this!"));
    }

    @Test
    public void can_parse_string_values_from_schema() {
        String schema = "n:string:a";
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is("a"));
    }

    @Test
    public void can_parse_integers_values_from_schema() {
        String schema = "n:integers:1,2";
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is(Lists.newArrayList(1, 2)));
    }

    @Test
    public void can_parse_strings_values_from_schema() {
        String schema = "n:strings:a,b";
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is(Lists.newArrayList("a", "b")));
    }

    @Test
    public void can_parse_values_from_command_line() {
        String schema = "n:integer:0";
        String commandLine = "-n 8080";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is(8080));
    }

    @Test
    public void can_parse_boolean_values_from_flag_only_command_line() {
        String schema = "n:boolean:false";
        String commandLine = "-n";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is(true));
    }

    @Test
    public void can_parse_negative_integer_values_from_command_line() {
        String schema = "n:integer:0; t:boolean:true";
        String commandLine = "-n -5 -t";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is(-5));
    }

    @Test
    public void can_parse_multiple_arguments_in_single_command_line() {
        String schema = "n:integer:0; t:boolean:false; v:strings:a,b";
        String commandLine = "-n -5 -t -v x,y,z";
        Args args = new Args(schema, commandLine);
        assertThat(args.getValue('n'), is(-5));
        assertThat(args.getValue('t'), is(true));
        assertThat(args.getValue('v'), is(ImmutableList.of("x", "y", "z")));
    }
}
