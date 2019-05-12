package com.example.kata.anagram;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Index {
    private final String index;

    public Index(String word) {
        this.index = indexWord(word);
    }

    private String indexWord(String word) {
        int[] ints = word.codePoints().sorted().toArray();
        return new String(ints, 0, ints.length);
    }
}
