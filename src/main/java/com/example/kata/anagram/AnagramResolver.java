package com.example.kata.anagram;

import com.google.common.collect.HashMultiset;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class AnagramResolver {
    List<Pair<String, String>> resolveAnagrams(String source, List<String> words) {
        return listAnagramCandidates(words).stream().filter(pair -> matchAnagram(source, pair.getLeft(), pair.getRight())).collect(Collectors.toList());
    }

    List<Pair<String, String>> listAnagramCandidates(List<String> words) {
        return IntStream.range(0, words.size() * words.size())
                .mapToObj(i -> Pair.of(i / words.size(), i % words.size()))
                .filter(i -> !Objects.equals(i.getLeft(), i.getRight()))
                .map(i -> Pair.of(words.get(i.getLeft()), words.get(i.getRight())))
                .collect(Collectors.toList());
    }

    boolean matchAnagram(String source, String word1, String word2) {
        HashMultiset<Integer> sourceSet = source.chars().boxed().collect(Collectors.toCollection(HashMultiset::create));
        return (word1 + word2).chars().boxed().allMatch(sourceSet::remove);
    }
}