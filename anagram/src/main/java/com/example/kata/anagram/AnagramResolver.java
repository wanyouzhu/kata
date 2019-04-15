package com.example.kata.anagram;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class AnagramResolver {
    private final AnagramSource anagramSource;

    AnagramResolver(AnagramSource anagramSource) {
        this.anagramSource = anagramSource;
    }

    Set<Anagram> resolve(String source) {
        Stream<Anagram> candidates = anagramSource.generateCandidates();
        return candidates.filter(anagram -> anagram.match(source)).collect(Collectors.toSet());
    }
}
