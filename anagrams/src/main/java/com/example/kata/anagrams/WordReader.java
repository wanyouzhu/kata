package com.example.kata.anagrams;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.function.Consumer;

public class WordReader {
    private final BufferedReader input;

    public WordReader(Reader input) {
        this.input = new BufferedReader(input);
    }

    public void readAll(Consumer<Word> consumer) {
        input.lines().map(Word::new).forEach(consumer);
    }
}
