package com.example.kata.anagrams;

import java.io.IOException;
import java.io.Writer;

public class WordWriter {
    private final Writer output;

    public WordWriter(Writer output) {
        this.output = output;
    }

    public void write(String word) {
        try {
            output.write(word);
        } catch (IOException e) {
            throw new AnagramsException("Failed to write word into output file.", e);
        }
    }

    public void writeSeparator() {
        write(", ");
    }

    public void writeEndOfLine() {
        write("\n");
    }
}
