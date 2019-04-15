package com.example.kata.anagram;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class AnagramGenerator {
    private List<String> wordList;

    AnagramGenerator(Set<String> wordList) {
        this.wordList = Lists.newArrayList(wordList);
    }

    Stream<Anagram> generate() {
        return getIndexPairs().filter(this::isMeaningful).map(this::makeAnagram);
    }

    private boolean isMeaningful(Pair<Integer, Integer> pair) {
        return !pair.getLeft().equals(pair.getRight());
    }

    private Pair<Integer, Integer> getNthIndexPair(int n) {
        return Pair.of(n / wordList.size(), n % wordList.size());
    }

    private Stream<Pair<Integer, Integer>> getIndexPairs() {
        return IntStream.range(0, wordList.size() * wordList.size()).mapToObj(this::getNthIndexPair);
    }

    private Anagram makeAnagram(Pair<Integer, Integer> i) {
        return new Anagram(wordList.get(i.getLeft()), wordList.get(i.getRight()));
    }
}
