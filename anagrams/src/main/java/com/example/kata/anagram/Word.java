package com.example.kata.anagram;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Word {
    private final String word;

    public Word(String line) {
        this.word = line;
    }

    public Index index() {
        return new Index(word);
    }

    public void write(WordWriter output) {
        output.write(word);
    }

    public String get() {
        return word;
    }
}
