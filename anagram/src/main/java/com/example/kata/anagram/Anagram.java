package com.example.kata.anagram;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import lombok.Value;

import java.util.stream.Collectors;

@Value
class Anagram {
    private final String word1;
    private final String word2;

    Anagram(String word1, String word2) {
        this.word1 = word1;
        this.word2 = word2;
    }

    boolean match(String source) {
        Multiset sourceCharSet = source.chars().boxed().collect(Collectors.toCollection(HashMultiset::create));
        return (word1 + word2).chars().allMatch(sourceCharSet::remove);
    }
}
