package com.example.kata.args;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TokenizerTest {
    @Test
    public void can_split_string_by_delimiter() {
        String input = "1,2,3";
        Tokenizer tokenizer = new Tokenizer(input);
        assertThat(tokenizer.getTokens(), is(ImmutableList.of("1", "2", "3")));
    }

    @Test
    public void split_strings_should_reserve_all_whitespaces() {
        String input = " 1 , 2 , 3";
        Tokenizer tokenizer = new Tokenizer(input);
        assertThat(tokenizer.getTokens(), is(ImmutableList.of(" 1 ", " 2 ", " 3")));
    }

    @Test
    public void split_strings_should_reserve_all_escapes() {
        String input = "\"this is \\\"a\\\"\",'that is \\'b\\''";
        Tokenizer tokenizer = new Tokenizer(input);
        assertThat(tokenizer.getTokens(), is(ImmutableList.of("\"this is \\\"a\\\"\"", "'that is \\'b\\''")));
    }

    @Test
    public void can_split_strings_that_contains_delimiter_in_quote() {
        String input = "'test,that',\"foo,bar\"";
        Tokenizer tokenizer = new Tokenizer(input);
        assertThat(tokenizer.getTokens(), is(ImmutableList.of("'test,that'", "\"foo,bar\"")));
    }

    @Test
    public void unclosed_single_quotation_should_be_treated_as_singe_element() {
        String input = "'this\", 'that'";
        Tokenizer tokenizer = new Tokenizer(input);
        assertThat(tokenizer.getTokens(), is(ImmutableList.of("'this\", 'that'")));
    }

    @Test
    public void unclosed_double_quotation_should_be_treated_as_singe_element() {
        String input = "\"this, \"that\"";
        Tokenizer tokenizer = new Tokenizer(input);
        assertThat(tokenizer.getTokens(), is(ImmutableList.of("\"this, \"that\"")));
    }

    @Test
    public void can_specify_delimiter() {
        String input = "'a:c':b";
        Tokenizer tokenizer = new Tokenizer(input, ':');
        assertThat(tokenizer.getTokens(), is(ImmutableList.of("'a:c'", "b")));
    }
}
