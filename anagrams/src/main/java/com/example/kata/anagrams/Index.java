package com.example.kata.anagrams;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Index {
    private final String letters;

    public Index(String letters) {
        this.letters = index(letters);
    }

    private String index(String letters) {
        return constructString(getSortedLetters(letters));
    }

    private String constructString(int[] letters) {
        return new String(letters, 0, letters.length);
    }

    private int[] getSortedLetters(String letters) {
        return letters.codePoints().sorted().toArray();
    }
}
