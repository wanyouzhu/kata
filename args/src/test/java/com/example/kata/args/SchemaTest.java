package com.example.kata.args;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SchemaTest {
    @Test
    public void can_parse_boolean_schema_with_default_true() {
        String schemaText = "t:bool:true";
        Option parsed = new Schema(schemaText).getOption('t');
        assertThat(parsed, is(new Option('t', Value.ofBool(true))));
    }

    @Test
    public void can_parse_boolean_schema_with_default_false() {
        String schemaText = "v:bool:false";
        Option parsed = new Schema(schemaText).getOption('v');
        assertThat(parsed, is(new Option('v', Value.ofBool(false))));
    }

    @Test
    public void whitespaces_are_allowed_between_schema_tokens() {
        String schemaText = " v : bool : true    ";
        Option parsed = new Schema(schemaText).getOption('v');
        assertThat(parsed, is(new Option('v', Value.ofBool(true))));
    }

    @Test
    public void can_parse_integer_schema_with_default_value() {
        String schemaText = "p:int:8080";
        Option parsed = new Schema(schemaText).getOption('p');
        assertThat(parsed, is(new Option('p', Value.ofInt(8080))));
    }

    @Test
    public void can_parse_string_schema_with_default_value() {
        String schemaText = "f:string:/var/test/abc.txt";
        Option parsed = new Schema(schemaText).getOption('f');
        assertThat(parsed, is(new Option('f', Value.ofString("/var/test/abc.txt"))));
    }

    @Test
    public void can_parse_int_list_schema_with_default_value() {
        String schemaText = "p:[int]:1,3,5";
        Option parsed = new Schema(schemaText).getOption('p');
        assertThat(parsed, is(new Option('p', Value.ofIntList(1, 3, 5))));
    }
    
    @Test
    public void can_parse_int_list_schema_without_default_value() {
        String schemaText = "p:[int]:";
        Option parsed = new Schema(schemaText).getOption('p');
        assertThat(parsed, is(new Option('p', Value.ofIntList())));
    }

    @Test
    public void can_parse_string_list_schema_with_default_value() {
        String schemaText = "f:[string]:a.txt,b.txt,c.txt";
        Option parsed = new Schema(schemaText).getOption('f');
        assertThat(parsed, is(new Option('f', Value.ofStringList("a.txt", "b.txt", "c.txt"))));
    }

    @Test
    public void can_parse_mix_typed_schemaText() {
        String schemaText = "p:int:8080; b:bool:false; o:string:test.out; l:[int]:3,2; i:[string]:a.txt,b.txt";
        Schema schema = new Schema(schemaText);
        assertThat(schema.getOption('p'), is(new Option('p', Value.ofInt(8080))));
        assertThat(schema.getOption('b'), is(new Option('b', Value.ofBool(false))));
        assertThat(schema.getOption('o'), is(new Option('o', Value.ofString("test.out"))));
        assertThat(schema.getOption('l'), is(new Option('l', Value.ofIntList(3, 2))));
        assertThat(schema.getOption('i'), is(new Option('i', Value.ofStringList("a.txt", "b.txt"))));
    }

    @Test
    public void default_value_of_string_schema_can_contain_whitespaces() {
        String schemaText = "f:string:this is a string contains whitespaces";
        Option parsed = new Schema(schemaText).getOption('f');
        assertThat(parsed, is(new Option('f', Value.ofString("this is a string contains whitespaces"))));
    }

    @Test
    public void trailing_whitespaces_of_string_default_value_should_be_trimmed() {
        String schemaText = "f:string:just a string    ";
        Option parsed = new Schema(schemaText).getOption('f');
        assertThat(parsed, is(new Option('f', Value.ofString("just a string"))));
    }

    @Test(expected = ArgsException.class)
    public void should_reject_malformed_schemaText() {
        String malformedSchema = "this is NOT a valid schema string";
        new Schema(malformedSchema);
    }
}
