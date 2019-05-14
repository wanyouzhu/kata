package com.example.kata.anagrams;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@ToString
@EqualsAndHashCode
public class WordSet {
    private Set<Word> words = new LinkedHashSet<>();

    public WordSet(Word... words) {
        Arrays.stream(words).forEach(this::add);
    }

    private WordSet(Set<Word> words) {
        this.words = words;
    }

    public void add(Word word) {
        words.add(word);
    }

    public WordSet excluded(Word word) {
        return new WordSet(words.stream().filter(x -> !x.equals(word)).collect(Collectors.toSet()));
    }

    public int getNumberOfWords() {
        return words.size();
    }

    public boolean isEmpty() {
        return words.isEmpty();
    }

    public void write(WordWriter output) {
        words.forEach(word -> word.write(output));
    }
}
