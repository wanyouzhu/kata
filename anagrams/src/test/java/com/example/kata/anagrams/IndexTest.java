package com.example.kata.anagrams;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class IndexTest {
    @Test
    public void should_equal_to_the_one_indexed_from_another_word_has_the_same_letters() {
        assertThat(new Index("ab"), is(new Index("ba")));
    }
}
