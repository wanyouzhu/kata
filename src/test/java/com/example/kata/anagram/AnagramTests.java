package com.example.kata.anagram;

import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class AnagramTests {

    @Test
    public void testMatchAnagram() {
        assertTrue(new Anagram("do", "men").match("documenting"));
        assertTrue(new Anagram("men", "do").match("documenting"));
        assertFalse(new Anagram("as", "bird").match("documenting"));
        assertTrue(new Anagram("do", "unit").match("documenting"));
        assertFalse(new Anagram("do", "out").match("documenting"));
        assertTrue(new Anagram("do", "on").match("documentation"));
        assertTrue(new Anagram("dot", "out").match("documentation"));
        assertFalse(new Anagram("dot", "init").match("documentation"));
    }

    @Test
    public void testListAnagramCandidates() {
        assertThat(listAnagramCandidates(ImmutableSet.of("do", "men")), is(ImmutableSet.of(new Anagram("do", "men"), new Anagram("men", "do"))));
        assertThat(listAnagramCandidates(ImmutableSet.of("test", "driven")), is(ImmutableSet.of(new Anagram("test", "driven"), new Anagram("driven", "test"))));
        assertThat(listAnagramCandidates(ImmutableSet.of("test")), is(ImmutableSet.of()));
        assertThat(listAnagramCandidates(ImmutableSet.of("t", "d", "v")), is(ImmutableSet.of(
                new Anagram("t", "d"), new Anagram("t", "v"),
                new Anagram("d", "t"), new Anagram("d", "v"),
                new Anagram("v", "t"), new Anagram("v", "d")
        )));
    }

    @Test
    public void testResolveAnagrams() {
        assertThat(new AnagramResolver().resolve("documenting", ImmutableSet.of("do", "men")), is(ImmutableSet.of(new Anagram("do", "men"), new Anagram("men", "do"))));
        assertThat(new AnagramResolver().resolve("test", ImmutableSet.of("do", "men")), is(ImmutableSet.of()));
        assertThat(new AnagramResolver().resolve("documenting", ImmutableSet.of("do", "in", "ego")), is(ImmutableSet.of(
                new Anagram("do", "in"),
                new Anagram("in", "do"), new Anagram("in", "ego"),
                new Anagram("ego", "in")
        )));
    }

    @Test
    public void testResolveAnagramsFromActualWordList() {
        Set<Anagram> resolved = new AnagramResolver().resolve("documenting", AnagramWordSet.loadWordList());
        assertThat(resolved, is(not(empty())));
    }

    private Set<Anagram> listAnagramCandidates(ImmutableSet<String> source) {
        return new AnagramSource(source).list().collect(Collectors.toSet());
    }
}