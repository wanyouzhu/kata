package com.example.kata.anagrams;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DictionaryTest {
    @Test
    public void should_group_words_by_indices() {
        Dictionary dictionary = new Dictionary();
        dictionary.add(new Word("ab"));
        dictionary.add(new Word("ac"));
        dictionary.add(new Word("ba"));
        assertThat(dictionary.getNumberOfEntries(), is(2));
    }

    @Test
    public void should_return_correct_word_set_when_lookup_with_existing_key() {
        Dictionary dictionary = new Dictionary();
        dictionary.add(new Word("ab"));
        dictionary.add(new Word("ac"));
        WordSet expect = new WordSet();
        expect.add(new Word("ab"));
        assertThat(dictionary.lookup(new Word("ba")), is(expect));
    }

    @Test
    public void should_return_empty_word_set_when_lookup_with_non_existing_key() {
        Dictionary dictionary = new Dictionary();
        dictionary.add(new Word("ab"));
        assertThat(dictionary.lookup(new Word("bc")), is(new WordSet()));
    }
}
