package com.example.kata.anagram;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnagramTest {
    @Test
    public void test_generated_anagram_candidates() {
        assertThat(generate("a", "b"), is(setOf(anagram("a", "b"), anagram("b", "a"))));
        assertThat(generate("a"), is(Collections.emptySet()));
        assertThat(generate(""), is(Collections.emptySet()));
        assertThat(generate("a", "b", "c"), is(setOf(
                anagram("a", "b"), anagram("a", "c"), anagram("b", "a"),
                anagram("b", "c"), anagram("c", "a"), anagram("c", "b")
        )));
    }

    @Test
    public void test_match_anagram() {
        assertThat(anagram("a", "b").match("ab"), is(true));
        assertThat(anagram("b", "a").match("ab"), is(true));
        assertThat(anagram("b", "c").match("ac"), is(false));
        assertThat(anagram("do", "men").match("documentation"), is(true));
        assertThat(anagram("unit", "to").match("documentation"), is(true));
        assertThat(anagram("document", "at").match("documentation"), is(true));
        assertThat(anagram("do", "dot").match("documentation"), is(false));
        assertThat(anagram("do", "none").match("documentation"), is(true));
        assertThat(anagram("do", "noon").match("documentation"), is(false));
    }

    @Test
    public void test_resolve() {
        assertThat(resolver("do", "none").resolve("documentation"), is(setOf(anagram("do", "none"), anagram("none", "do"))));
        assertThat(resolver("do", "on").resolve("dont"), is(Collections.emptySet()));
        assertThat(resolver("do", "on", "unit").resolve("documentation"), is(setOf(
                anagram("do", "on"), anagram("on", "do"), anagram("do", "unit"),
                anagram("on", "unit"), anagram("unit", "on"), anagram("unit", "do")
        )));
    }

    private AnagramResolver resolver(String... words) {
        return new AnagramResolver(new AnagramSource(words));
    }

    private Anagram anagram(String a, String b) {
        return new Anagram(a, b);
    }

    private Set<Anagram> generate(String... words) {
        return new AnagramSource(words).generateCandidates().collect(Collectors.toSet());
    }

    private Set<Anagram> setOf(Anagram... anagrams) {
        return Sets.newHashSet(anagrams);
    }
}
