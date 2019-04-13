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

    private Anagram getAnagram(Pair<Integer, Integer> index) {
        return new Anagram(words.get(index.getLeft()), words.get(index.getRight()));
    }

    private boolean isMeaningful(Pair<Integer, Integer> index) {
        return !index.getLeft().equals(index.getRight());
    }

    private Stream<Pair<Integer, Integer>> generateIndices() {
        return generateLeftParts().flatMap(this::generateRightParts);
    }

    private Stream<Integer> generateLeftParts() {
        return IntStream.range(0, words.size()).boxed();
    }

    private Stream<Pair<Integer, Integer>> generateRightParts(Integer left) {
        return IntStream.range(0, words.size()).mapToObj(right -> Pair.of(left, right));
    }
}
