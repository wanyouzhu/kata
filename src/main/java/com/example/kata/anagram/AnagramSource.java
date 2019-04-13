package com.example.kata.anagram;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class AnagramSource {
    private final List<String> words;

    AnagramSource(Set<String> words) {
        this.words = Lists.newArrayList(words);
    }

    Stream<Anagram> list() {
        return indexCartesianProduct().filter(x -> !Objects.equals(x.getLeft(), x.getRight())).map(this::getAnagram);
    }

    private Anagram getAnagram(Pair<Integer, Integer> pair) {
        return new Anagram(words.get(pair.getLeft()), words.get(pair.getRight()));
    }

    private Stream<Pair<Integer, Integer>> indexCartesianProduct() {
        IntStream indices = IntStream.range(0, words.size() * words.size());
        return indices.mapToObj(i -> Pair.of(i / words.size(), i % words.size()));
    }
}
