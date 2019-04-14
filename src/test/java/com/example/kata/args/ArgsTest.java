package com.example.kata.args;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ArgsTest {
    @Test
    public void can_parse_true_boolean_option_value() {
        String schemaText = "t:bool:false";
        String commandLine = "-t true";
        Args args = new Args(new Schema(schemaText), commandLine);
        assertThat(args.getArgument('t'), is(new Argument(new Option('t', Value.ofBool(false)), Value.ofBool(true))));
    }

    @Test
    public void can_parse_false_boolean_option_value() {
        String schemaText = "t:bool:true";
        String commandLine = "-t false";
        Args args = new Args(new Schema(schemaText), commandLine);
        assertThat(args.getArgument('t'), is(new Argument(new Option('t', Value.ofBool(true)), Value.ofBool(false))));
    }

    @Test
    public void can_parse_empty_boolean_option_value() {
        String schemaText = "t:bool:false";
        String commandLine = "-t";
        Args args = new Args(new Schema(schemaText), commandLine);
        assertThat(args.getArgument('t'), is(new Argument(new Option('t', Value.ofBool(false)), Value.ofBool(true))));
    }

    @Test
    public void can_parse_int_option_value() {
        String schemaText = "p:int:1080";
        String commandLine = "-p 8080";
        Args args = new Args(new Schema(schemaText), commandLine);
        assertThat(args.getArgument('p'), is(new Argument(new Option('p', Value.ofInt(1080)), Value.ofInt(8080))));
    }

    @Test
    public void can_parse_int_option_value_prefixed_with_sign_mark() {
        String schemaText = "n:int:0; m:int:0";
        String commandLine = "-n -1 -m +2";
        Args args = new Args(new Schema(schemaText), commandLine);
        assertThat(args.getArgumentValue('n'), is(Value.ofInt(-1)));
        assertThat(args.getArgumentValue('m'), is(Value.ofInt(2)));
    }

    @Test
    public void can_parse_string_option_value() {
        String schemaText = "f:string:a.txt";
        String commandLine = "-f b.txt";
        Args args = new Args(new Schema(schemaText), commandLine);
        assertThat(args.getArgument('f'), is(new Argument(new Option('f', Value.ofString("a.txt")), Value.ofString("b.txt"))));
    }

    @Test
    public void can_parse_int_list_option_value() {
        String schemaText = "l:[int]:1";
        String commandLine = "-l 5,7,8";
        Args args = new Args(new Schema(schemaText), commandLine);
        assertThat(args.getArgument('l'), is(new Argument(new Option('l', Value.ofIntList(1)), Value.ofIntList(5, 7, 8))));
    }

    @Test
    public void can_parse_string_list_option_value() {
        String schemaText = "l:[string]:that";
        String commandLine = "-l that,this,those";
        Args args = new Args(new Schema(schemaText), commandLine);
        assertThat(args.getArgument('l'), is(new Argument(new Option('l', Value.ofStringList("that")), Value.ofStringList("that", "this", "those"))));
    }

    @Test
    public void can_parse_multiple_option_values() {
        String schemaText = "t:bool:false; n:int:0; o:string:test.log; i:[string]:; l:[int]:0";
        String commandLine = "-t -n 100 -o main.log -i a.txt,b.txt -l 0,-1,+2,3";
        Args args = new Args(new Schema(schemaText), commandLine);
        assertThat(args.getArgumentValue('t'), is(Value.ofBool(true)));
        assertThat(args.getArgumentValue('n'), is(Value.ofInt(100)));
        assertThat(args.getArgumentValue('o'), is(Value.ofString("main.log")));
        assertThat(args.getArgumentValue('i'), is(Value.ofStringList("a.txt", "b.txt")));
        assertThat(args.getArgumentValue('l'), is(Value.ofIntList(0, -1, 2, 3)));
    }

    @Test
    public void can_parse_option_values_that_contain_whitespaces() {
        String schemaText = "t:bool:false; n:int:0; o:string:test.log; i:[string]:; l:[int]:0";
        String commandLine = " -t -n 100 -o main.log -i a.txt , b.txt -l 0 , 1 , 2 , 3 ";
        new Args(new Schema(schemaText), commandLine);
    }
}
