package com.example.kata.anagrams;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Anagram {
    private final Word word;
    private final WordSet wordSet;

    public Anagram(Word word, WordSet wordSet) {
        this.word = word;
        this.wordSet = wordSet.excluded(word);
    }

    public void write(WordWriter output) {
        if (wordSet.isEmpty()) return;
        word.write(output);
        output.writeSeparator();
        wordSet.write(output);
        output.writeEndOfLine();
    }
}
