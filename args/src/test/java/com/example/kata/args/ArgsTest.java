package com.example.kata.args;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ArgsTest {
    @Test
    public void can_parse_true_boolean_arguments_from_command_lines() {
        String[] commandLineArgs = {"-v", "true"};
        ProgramOptions options = new Schema(new BooleanOption('v')).match(commandLineArgs);
        assertThat(options.get('v'), is(true));
    }

    @Test
    public void can_parse_false_boolean_arguments_from_command_lines() {
        String[] commandLineArgs = {"-v", "false"};
        ProgramOptions options = new Schema(new BooleanOption('v')).match(commandLineArgs);
        assertThat(options.get('v'), is(false));
    }

    @Test
    public void can_parse_boolean_arguments_from_empty_value_commands() {
        String[] commandLineArgs = {"-v"};
        ProgramOptions options = new Schema(new BooleanOption('v')).match(commandLineArgs);
        assertThat(options.get('v'), is(true));
    }

    @Test
    public void should_use_default_false_if_input_is_empty() {
        String[] commandLineArgs = {};
        ProgramOptions options = new Schema(new BooleanOption('v')).match(commandLineArgs);
        assertThat(options.get('v'), is(false));
    }

    @Test(expected = ArgsException.class)
    public void should_reject_malformed_boolean_values() {
        String[] commandLineArgs = {"-v", "not a boolean"};
        new Schema(new BooleanOption('v')).match(commandLineArgs);
    }

    @Test
    public void can_parse_integer_arguments_from_command_lines() {
        String[] commandLineArgs = {"-p", "8080"};
        ProgramOptions options = new Schema(new IntegerOption('p')).match(commandLineArgs);
        assertThat(options.get('p'), is(8080));
    }

    @Test
    public void can_parse_negative_integer_arguments_from_command_lines() {
        String[] commandLineArgs = {"-n", "-100"};
        ProgramOptions options = new Schema(new IntegerOption('n')).match(commandLineArgs);
        assertThat(options.get('n'), is(-100));
    }

    @Test
    public void should_use_default_integer_if_input_is_empty() {
        String[] commandLineArgs = {};
        ProgramOptions options = new Schema(new IntegerOption('n')).match(commandLineArgs);
        assertThat(options.get('n'), is(0));
    }

    @Test(expected = ArgsException.class)
    public void integer_option_should_reject_input_that_contains_no_values() {
        String[] commandLineArgs = {"-p"};
        new Schema(new IntegerOption('p')).match(commandLineArgs);
    }

    @Test(expected = ArgsException.class)
    public void should_reject_malformed_integer_values() {
        String[] commandLineArgs = {"-p", "not a integer"};
        new Schema(new IntegerOption('p')).match(commandLineArgs);
    }

    @Test
    public void can_parse_string_arguments_from_command_lines() {
        String[] commandLineArgs = {"-o", "game"};
        ProgramOptions options = new Schema(new StringOption('o')).match(commandLineArgs);
        assertThat(options.get('o'), is("game"));
    }

    @Test
    public void should_use_default_string_if_input_is_empty() {
        String[] commandLineArgs = {};
        ProgramOptions options = new Schema(new StringOption('o')).match(commandLineArgs);
        assertThat(options.get('o'), is(""));
    }

    @Test(expected = ArgsException.class)
    public void string_option_should_reject_input_that_contains_no_values() {
        String[] commandLineArgs = {"-o"};
        new Schema(new StringOption('o')).match(commandLineArgs);
    }

    @Test
    public void can_parse_integer_list_arguments_from_command_lines() {
        String[] commandLineArgs = {"-w", "1,2,3"};
        ProgramOptions options = new Schema(new IntegerListOption('w')).match(commandLineArgs);
        assertThat(options.get('w'), is(ImmutableList.of(1, 2, 3)));
    }

    @Test
    public void can_parse_integer_list_arguments_from_command_lines_that_contains_negative_integers() {
        String[] commandLineArgs = {"-w", "-1,-2,-3"};
        ProgramOptions options = new Schema(new IntegerListOption('w')).match(commandLineArgs);
        assertThat(options.get('w'), is(ImmutableList.of(-1, -2, -3)));
    }

    @Test
    public void should_use_default_integer_list_if_input_is_empty() {
        String[] commandLineArgs = {};
        ProgramOptions options = new Schema(new IntegerListOption('w')).match(commandLineArgs);
        assertThat(options.get('w'), is(Collections.emptyList()));
    }

    @Test(expected = ArgsException.class)
    public void integer_list_option_should_reject_input_that_contains_no_values() {
        String[] commandLineArgs = {"-w"};
        new Schema(new IntegerListOption('w')).match(commandLineArgs);
    }

    @Test
    public void can_parse_string_list_arguments_from_command_lines() {
        String[] commandLineArgs = {"-i", "a.lib,b.lib"};
        ProgramOptions options = new Schema(new StringListOption('i')).match(commandLineArgs);
        assertThat(options.get('i'), is(ImmutableList.of("a.lib", "b.lib")));
    }

    @Test(expected = ArgsException.class)
    public void string_list_option_should_reject_input_that_contains_no_values() {
        String[] commandLineArgs = {"-i"};
        new Schema(new StringListOption('i')).match(commandLineArgs);
    }

    @Test
    public void should_use_default_string_list_if_input_is_empty() {
        String[] commandLineArgs = {};
        ProgramOptions options = new Schema(new StringListOption('i')).match(commandLineArgs);
        assertThat(options.get('i'), is(Collections.emptyList()));
    }

    @Test
    public void can_parse_multiple_arguments() {
        String[] commandLineArgs = {"-v", "-p", "-5", "-w", "-1,-2"};
        Schema schema = new Schema(new IntegerListOption('w'), new IntegerOption('p'), new BooleanOption('v'));
        ProgramOptions options = schema.match(commandLineArgs);
        assertThat(options.get('w'), is(ImmutableList.of(-1, -2)));
        assertThat(options.get('p'), is(-5));
        assertThat(options.get('v'), is(true));
    }
}
