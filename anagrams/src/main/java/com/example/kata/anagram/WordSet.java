package com.example.kata.anagram;

import java.util.LinkedHashSet;
import java.util.Set;

public class WordSet {
    private final Set<Word> words = new LinkedHashSet<>();

    public void add(Word word) {
        words.add(word);
    }

    public WordSet excluded(Word word) {
        WordSet result = new WordSet();
        copyWordsExcept(result, word);
        return result;
    }

    public void write(WordWriter writer) {
        words.forEach(word -> writeWord(word, writer));
    }

    private void writeWord(Word word, WordWriter writer) {
        writer.writeSeparator();
        word.write(writer);
    }

    private void copyWordsExcept(WordSet result, Word except) {
        words.stream().filter(x -> !x.equals(except)).forEach(result::add);
    }

    public boolean isEmpty() {
        return words.isEmpty();
    }
}
