package com.example.kata.anagram;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class AnagramTests {
    private final AnagramResolver resolver = new AnagramResolver();

    @Test
    public void testMatchAnagram() {
        assertTrue(resolver.matchAnagram("documenting", "do", "men"));
        assertTrue(resolver.matchAnagram("documenting", "men", "do"));
        assertFalse(resolver.matchAnagram("documenting", "as", "bird"));
        assertTrue(resolver.matchAnagram("documenting", "do", "unit"));
        assertFalse(resolver.matchAnagram("documenting", "do", "out"));
        assertTrue(resolver.matchAnagram("documentation", "do", "on"));
        assertTrue(resolver.matchAnagram("documentation", "dot", "out"));
        assertFalse(resolver.matchAnagram("documentation", "dot", "init"));
    }

    @Test
    public void testListAnagramCandidates() {
        assertThat(resolver.listAnagramCandidates(ImmutableList.of("do", "men")), is(ImmutableList.of(Pair.of("do", "men"), Pair.of("men", "do"))));
        assertThat(resolver.listAnagramCandidates(ImmutableList.of("test", "driven")), is(ImmutableList.of(Pair.of("test", "driven"), Pair.of("driven", "test"))));
        assertThat(resolver.listAnagramCandidates(ImmutableList.of("test")), is(ImmutableList.of()));
        assertThat(resolver.listAnagramCandidates(ImmutableList.of("t", "d", "v")), is(ImmutableList.of(
                Pair.of("t", "d"), Pair.of("t", "v"),
                Pair.of("d", "t"), Pair.of("d", "v"),
                Pair.of("v", "t"), Pair.of("v", "d")
        )));
    }

    @Test
    public void testResolveAnagrams() {
        assertThat(resolver.resolveAnagrams("documenting", ImmutableList.of("do", "men")), is(ImmutableList.of(Pair.of("do", "men"), Pair.of("men", "do"))));
        assertThat(resolver.resolveAnagrams("test", ImmutableList.of("do", "men")), is(ImmutableList.of()));
        assertThat(resolver.resolveAnagrams("documenting", ImmutableList.of("do", "in", "ego")), is(ImmutableList.of(
                Pair.of("do", "in"),
                Pair.of("in", "do"), Pair.of("in", "ego"),
                Pair.of("ego", "in")
        )));
    }

    @Test
    public void testResolveAnagramsFromActualWordList() {
        List<Pair<String, String>> resolved = resolver.resolveAnagrams("documenting", AnagramWordSet.loadWordList());
        assertThat(resolved, is(not(empty())));
        System.out.println(resolved);
    }
}