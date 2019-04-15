package com.example.kata.anagram;

import java.util.Set;
import java.util.stream.Collectors;

class AnagramResolver {
    private final AnagramGenerator generator;

    AnagramResolver(Set<String> wordList) {
        this.generator = new AnagramGenerator(wordList);
    }

    Set<Anagram> resolve(String source) {
        return generator.generate().filter(anagram -> anagram.match(source)).collect(Collectors.toSet());
    }
}
