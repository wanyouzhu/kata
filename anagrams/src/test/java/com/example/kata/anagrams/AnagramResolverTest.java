package com.example.kata.anagrams;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

public class AnagramResolverTest {
    @Test
    public void should_return_correct_anagram_when_input_word_can_be_resolved_as_an_anagram() {
        Dictionary dictionary = new Dictionary();
        dictionary.add(new Word("ab"));
        dictionary.add(new Word("ba"));
        AnagramResolver resolver = new AnagramResolver(dictionary);
        Assert.assertThat(resolver.resolve(new Word("ba")), is(new Anagram(new Word("ba"), new WordSet(new Word("ab")))));
    }

    @Test
    public void should_return_empty_anagram_when_input_word_can_NOT_be_resolved_as_an_anagram() {
        Dictionary dictionary = new Dictionary();
        dictionary.add(new Word("ab"));
        AnagramResolver resolver = new AnagramResolver(dictionary);
        Assert.assertThat(resolver.resolve(new Word("ab")), is(new Anagram(new Word("ab"), new WordSet())));
    }
}
