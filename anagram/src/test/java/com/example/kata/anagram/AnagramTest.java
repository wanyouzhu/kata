package com.example.kata.anagram;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnagramTest {
    @Test
    public void should_be_matched_if_concatenation_equals_to_the_source_word() {
        Anagram anagram = new Anagram("to", "do");
        String source = "todo";
        assertThat(anagram.match(source), is(true));
    }

    @Test
    public void should_be_matched_if_reverse_concatenation_equals_to_the_source_word() {
        Anagram anagram = new Anagram("do", "to");
        String source = "todo";
        assertThat(anagram.match(source), is(true));
    }

    @Test
    public void should_be_matched_if_the_source_word_contains_all_the_characters_in_anagram() {
        Anagram anagram = new Anagram("me", "top");
        String source = "computer";
        assertThat(anagram.match(source), is(true));
    }

    @Test
    public void should_not_be_matched_if_count_of_a_char_from_the_source_word_is_less_than_the_one_from_anagram() {
        Anagram anagram = new Anagram("oo", "op");
        String source = "oop";
        assertThat(anagram.match(source), is(false));
    }

    @Test
    public void can_generate_anagram_candidates_from_a_word_list() {
        Set<String> wordList = Sets.newHashSet("a", "b");
        Set<Anagram> anagrams = new AnagramGenerator(wordList).generate().collect(Collectors.toSet());
        assertThat(anagrams, is(ImmutableSet.of(new Anagram("a", "b"), new Anagram("b", "a"))));
    }

    @Test
    public void should_only_return_matched_anagrams() {
        Set<String> wordList = Sets.newHashSet("do", "men", "good", "test");
        String source = "documenting";
        Set<Anagram> anagrams = new AnagramResolver(wordList).resolve(source);
        assertThat(anagrams, is(ImmutableSet.of(new Anagram("do", "men"), new Anagram("men", "do"))));
    }
}
