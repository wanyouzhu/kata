package com.example.kata.anagram;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class AnagramSource {
    private final List<String> words;

    AnagramSource(String... words) {
        this.words = Lists.newArrayList(words);
    }

    Stream<Anagram> generateCandidates() {
        return generateIndices().filter(this::isMeaningful).map(this::getAnagram);
    }

    private Anagram getAnagram(Pair<Integer, Integer> wordIndices) {
        return new Anagram(words.get(wordIndices.getLeft()), words.get(wordIndices.getRight()));
    }

    private boolean isMeaningful(Pair<Integer, Integer> wordIndices) {
        return !wordIndices.getLeft().equals(wordIndices.getRight());
    }

    private Stream<Pair<Integer, Integer>> generateIndices() {
        return IntStream.range(0, words.size() * words.size()).mapToObj(i -> Pair.of(i / words.size(), i % words.size()));
    }
}
