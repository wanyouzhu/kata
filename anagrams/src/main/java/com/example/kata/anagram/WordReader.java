package com.example.kata.anagram;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.util.function.Consumer;

public class WordReader implements Closeable {
    private final BufferedReader input;

    public WordReader(Reader input) {
        this.input = new BufferedReader(input);
    }

    public void readAll(Consumer<Word> consumer) {
        input.lines().map(Word::new).forEach(consumer);
    }

    @Override
    public void close() {
        try {
            this.input.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
