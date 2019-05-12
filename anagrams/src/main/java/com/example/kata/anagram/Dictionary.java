package com.example.kata.anagram;

import com.google.common.collect.Maps;

import java.util.Map;

public class Dictionary {
    private final Map<Index, WordSet> wordMap = Maps.newHashMap();

    public Dictionary(WordReader words) {
        words.readAll(this::addWord);
    }

    public WordSet lookup(Word word) {
        return getOrDefault(word).excluded(word);
    }

    private void addWord(Word word) {
        getOrNewWordSet(word).add(word);
    }

    private WordSet getOrNewWordSet(Word word) {
        return wordMap.computeIfAbsent(word.index(), k -> new WordSet());
    }

    private WordSet getOrDefault(Word word) {
        return wordMap.getOrDefault(word.index(), new WordSet());
    }
}
