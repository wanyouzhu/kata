package com.example.kata.anagram;

import com.google.common.collect.HashMultiset;
import lombok.Value;

import java.util.stream.Collectors;

@Value
class Anagram {
    String word1;
    String word2;

    boolean match(String source) {
        HashMultiset<Integer> sourceSet = source.chars().boxed().collect(Collectors.toCollection(HashMultiset::create));
        return (word1 + word2).chars().boxed().allMatch(sourceSet::remove);
    }
}
