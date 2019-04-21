package com.example.kata.args;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SchemaTest {
    @Test
    public void can_parse_integer_options() {
        String input = "p:integer:1080";
        Schema schema = new Schema(input);
        assertThat(schema.getOption('p'), is(new Option('p', Value.ofInteger(1080))));
    }

    @Test
    public void can_parse_boolean_options() {
        String input = "v:boolean:false";
        Schema schema = new Schema(input);
        assertThat(schema.getOption('v'), is(new Option('v', Value.ofBoolean(false))));
    }

    @Test
    public void can_parse_string_options() {
        String input = "x:string:a.txt";
        Schema schema = new Schema(input);
        assertThat(schema.getOption('x'), is(new Option('x', Value.ofString("a.txt"))));
    }

    @Test
    public void can_parse_integer_list_options() {
        String input = "x:integers:3,9";
        Schema schema = new Schema(input);
        assertThat(schema.getOption('x'), is(new Option('x', Value.ofIntegers(3, 9))));
    }

    @Test
    public void can_parse_string_list_options() {
        String input = "x:strings:a.txt,b.txt";
        Schema schema = new Schema(input);
        assertThat(schema.getOption('x'), is(new Option('x', Value.ofStrings("a.txt", "b.txt"))));
    }

    @Test
    public void can_parse_multiple_options() {
        String input = "p:integer:1080; v:boolean:false";
        Schema schema = new Schema(input);
        assertThat(schema.getOption('p'), is(new Option('p', Value.ofInteger(1080))));
        assertThat(schema.getOption('v'), is(new Option('v', Value.ofBoolean(false))));
    }
}
