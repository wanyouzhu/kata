package com.example.kata.anagram;

import com.google.common.collect.HashMultiset;
import lombok.Value;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Value
class Anagram {
    private final String word1;
    private final String word2;

    Anagram(String word1, String word2) {
        this.word1 = word1;
        this.word2 = word2;
    }

    boolean match(String source) {
        HashMultiset<Integer> set = getCodePoints(source).collect(Collectors.toCollection(HashMultiset::create));
        return getCodePoints(word1 + word2).allMatch(set::remove);
    }

    private Stream<Integer> getCodePoints(String s) {
        return (s).codePoints().boxed();
    }
}
