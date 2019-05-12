package com.example.kata.anagram;

import java.io.Closeable;
import java.io.IOException;
import java.io.Writer;

public class WordWriter implements Closeable {
    private final Writer writer;

    public WordWriter(Writer writer) {
        this.writer = writer;
    }

    public void write(String word) {
        try {
            this.writer.write(word);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeSeparator() {
        write(", ");
    }

    public void writeEndOfLine() {
        write("\n");
    }

    @Override
    public void close() {
        try {
            this.writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
