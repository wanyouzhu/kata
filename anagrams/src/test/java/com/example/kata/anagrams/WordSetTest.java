package com.example.kata.anagrams;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class WordSetTest {
    @Test
    public void can_hold_multiple_words() {
        WordSet wordSet = new WordSet();
        wordSet.add(new Word("a"));
        wordSet.add(new Word("b"));
        assertThat(wordSet.getNumberOfWords(), is(2));
    }

    @Test
    public void should_only_hold_unique_words() {
        WordSet wordSet = new WordSet();
        wordSet.add(new Word("a"));
        wordSet.add(new Word("a"));
        wordSet.add(new Word("b"));
        assertThat(wordSet.getNumberOfWords(), is(2));
    }
}
