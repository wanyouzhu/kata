package com.example.kata.anagram;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class AnagramResolver {
    Set<Anagram> resolveAnagrams(String source, Set<String> words) {
        Stream<Anagram> candidates = new AnagramSource(words).readAnagrams();
        return candidates.filter(anagram -> anagram.match(source)).collect(Collectors.toSet());
    }

    boolean matchAnagram(String source, Anagram anagram) {
        return anagram.match(source);
    }
}