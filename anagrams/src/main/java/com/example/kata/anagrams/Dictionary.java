package com.example.kata.anagrams;

import java.util.LinkedHashMap;
import java.util.Map;

public class Dictionary {
    private Map<Index, WordSet> indexedWords = new LinkedHashMap<>();

    public void add(Word word) {
        WordSet wordSet = getWordSet(word);
        wordSet.add(word);
    }

    public WordSet lookup(Word word) {
        return indexedWords.getOrDefault(word.index(), new WordSet());
    }

    public int getNumberOfEntries() {
        return indexedWords.size();
    }

    private WordSet getWordSet(Word word) {
        return indexedWords.computeIfAbsent(word.index(), (k) -> new WordSet());
    }
}
