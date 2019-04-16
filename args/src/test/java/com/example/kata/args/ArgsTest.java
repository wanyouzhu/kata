package com.example.kata.args;

import org.junit.Test;

import static com.example.kata.args.Option.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ArgsTest {
    @Test
    public void can_parse_true_boolean_value() {
        Schema schema = new Schema(ofBool('b', false));
        String commandLine = "-b true";
        Args args = new Args(schema, commandLine);
        assertThat(args.getOptionValue('b'), is(Value.ofBool(true)));
    }

    @Test
    public void can_parse_false_boolean_value() {
        Schema schema = new Schema(ofBool('b', true));
        String commandLine = "-b false";
        Args args = new Args(schema, commandLine);
        assertThat(args.getOptionValue('b'), is(Value.ofBool(false)));
    }

    @Test
    public void can_parse_empty_boolean_value() {
        Schema schema = new Schema(ofBool('b', false));
        String commandLine = "-b";
        Args args = new Args(schema, commandLine);
        assertThat(args.getOptionValue('b'), is(Value.ofBool(true)));
    }

    @Test
    public void can_parse_integer_value() {
        Schema schema = new Schema(ofInt('n', 0));
        String commandLine = "-n 5";
        Args args = new Args(schema, commandLine);
        assertThat(args.getOptionValue('n'), is(Value.ofInt(5)));
    }

    @Test
    public void can_parse_negative_integer_value() {
        Schema schema = new Schema(ofInt('n', 0));
        String commandLine = "-n -5";
        Args args = new Args(schema, commandLine);
        assertThat(args.getOptionValue('n'), is(Value.ofInt(-5)));
    }

    @Test
    public void can_parse_integer_with_plus_sign() {
        Schema schema = new Schema(ofInt('n', 0));
        String commandLine = "-n +5";
        Args args = new Args(schema, commandLine);
        assertThat(args.getOptionValue('n'), is(Value.ofInt(5)));
    }

    @Test
    public void can_parse_string_value() {
        Schema schema = new Schema(ofString('f', "/var/test.txt"));
        String commandLine = "-f /srv/nginx/test.conf";
        Args args = new Args(schema, commandLine);
        assertThat(args.getOptionValue('f'), is(Value.ofString("/srv/nginx/test.conf")));
    }

    @Test
    public void can_parse_integer_list_value() {
        Schema schema = new Schema(ofIntList('w'));
        String commandLine = "-w 1,-2,+3";
        Args args = new Args(schema, commandLine);
        assertThat(args.getOptionValue('w'), is(Value.ofIntList(1, -2, 3)));
    }

    @Test
    public void can_parse_string_list_value() {
        Schema schema = new Schema(ofStringList('i'));
        String commandLine = "-i a.txt,b.txt,c.txt";
        Args args = new Args(schema, commandLine);
        assertThat(args.getOptionValue('i'), is(Value.ofStringList("a.txt", "b.txt", "c.txt")));
    }

    @Test
    public void can_parse_multiple_arguments() {
        Schema schema = new Schema(ofInt('n', 0), ofBool('t', false), ofString('f', ""), ofIntList('k'), ofStringList('v'));
        String commandLine = "-n 5 -t -f a.txt -k 2,9 -v foo,bar";
        Args args = new Args(schema, commandLine);
        assertThat(args.getOptionValue('n'), is(Value.ofInt(5)));
        assertThat(args.getOptionValue('t'), is(Value.ofBool(true)));
        assertThat(args.getOptionValue('f'), is(Value.ofString("a.txt")));
        assertThat(args.getOptionValue('k'), is(Value.ofIntList(2, 9)));
        assertThat(args.getOptionValue('v'), is(Value.ofStringList("foo", "bar")));
    }

    @Test
    public void can_parse_empty_command_line_with_default_values() {
        Schema schema = new Schema(ofInt('n', 2), ofBool('t', true), ofString('f', "a.txt"), ofIntList('k', 1), ofStringList('v', "foo"));
        String commandLine = "";
        Args args = new Args(schema, commandLine);
        assertThat(args.getOptionValue('n'), is(Value.ofInt(2)));
        assertThat(args.getOptionValue('t'), is(Value.ofBool(true)));
        assertThat(args.getOptionValue('f'), is(Value.ofString("a.txt")));
        assertThat(args.getOptionValue('k'), is(Value.ofIntList(1)));
        assertThat(args.getOptionValue('v'), is(Value.ofStringList("foo")));
    }
}
