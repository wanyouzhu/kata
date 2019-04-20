package com.example.kata.args;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ArgsTest {
    @Test
    public void can_parse_single_argument() {
        String schema = "n:integer:5";
        String command = "-n 2";
        Args args = new Args(schema, command);
        assertThat(args.getOptionValue('n'), is(Value.ofInteger(2)));
    }

    @Test
    public void can_parse_multiple_arguments() {
        String schema = "n:integer:5; p:string:";
        String command = "-p a b -n 2";
        Args args = new Args(schema, command);
        assertThat(args.getOptionValue('n'), is(Value.ofInteger(2)));
        assertThat(args.getOptionValue('p'), is(Value.ofString("a b")));
    }

    @Test
    public void can_parse_empty_input() {
        String schema = "n:integer:5; s:strings:a,b";
        String command = "";
        Args args = new Args(schema, command);
        assertThat(args.getOptionValue('n'), is(Value.ofInteger(5)));
        assertThat(args.getOptionValue('s'), is(Value.ofStrings("a", "b")));
    }

    @Test
    public void can_parse_all() {
        String schema = "p:integer:1080; w:integers:1; v:boolean:false; o:string:b.txt; i:strings:'d:\\a -p name.txt'";
        String command = "-p 8080 -w 1,2,3 -v -o 'c:\\\\program files\\\\a -p file.txt' -i 'a -t file.txt', b.txt";
        Args args = new Args(schema, command);
        assertThat(args.getOptionValue('p'), is(Value.ofInteger(8080)));
        assertThat(args.getOptionValue('w'), is(Value.ofIntegers(1, 2, 3)));
        assertThat(args.getOptionValue('v'), is(Value.ofBoolean(true)));
        assertThat(args.getOptionValue('o'), is(Value.ofString("c:\\program files\\a -p file.txt")));
        assertThat(args.getOptionValue('i'), is(Value.ofStrings("a -t file.txt", "b.txt")));
    }
}
