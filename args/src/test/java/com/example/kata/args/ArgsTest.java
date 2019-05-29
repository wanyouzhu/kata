package com.example.kata.args;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArgsTest {
    @Test
    void can_parse_true_boolean_arguments() {
        Schema schema = new Schema(new Option('l', new BooleanType()));
        String[] input = {"-l", "true"};
        Args args = new Args(schema, input);
        assertEquals(true, args.getValue('l'));
    }

    @Test
    void can_parse_false_boolean_arguments() {
        Schema schema = new Schema(new Option('l', new BooleanType()));
        String[] input = {"-l", "false"};
        Args args = new Args(schema, input);
        assertEquals(false, args.getValue('l'));
    }

    @Test
    void can_parse_missing_value_boolean_arguments() {
        Schema schema = new Schema(new Option('l', new BooleanType()));
        String[] input = {"-l"};
        Args args = new Args(schema, input);
        assertEquals(true, args.getValue('l'));
    }

    @Test
    void should_reject_malformed_boolean_arguments() {
        Schema schema = new Schema(new Option('l', new BooleanType()));
        String[] input = {"-l", "not-a-boolean"};
        ArgsException exception = assertThrows(ArgsException.class, () -> new Args(schema, input));
        assertEquals("Malformed boolean value for flag 'l': not-a-boolean", exception.getMessage());
    }
}
