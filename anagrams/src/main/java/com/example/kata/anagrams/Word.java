package com.example.kata.anagrams;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Word {
    private final String word;

    public Word(String word) {
        this.word = word;
    }

    public Index index() {
        return new Index(word);
    }

    public void write(WordWriter output) {
        output.write(word);
    }
}
