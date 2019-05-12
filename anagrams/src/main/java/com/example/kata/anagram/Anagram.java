package com.example.kata.anagram;

public class Anagram {
    private final Word word;
    private final WordSet wordSet;

    public Anagram(Word word, WordSet wordSet) {
        this.word = word;
        this.wordSet = wordSet;
    }

    public void write(WordWriter output) {
        if (wordSet.isEmpty()) return;
        word.write(output);
        wordSet.write(output);
        output.writeEndOfLine();
    }
}
