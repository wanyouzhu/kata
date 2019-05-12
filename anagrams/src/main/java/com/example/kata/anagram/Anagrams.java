package com.example.kata.anagram;

import java.io.Reader;
import java.io.Writer;

public class Anagrams {
    private final AnagramResolver anagramResolver;

    public Anagrams(Reader wordListFile) {
        anagramResolver = new AnagramResolver(new Dictionary(new WordReader(wordListFile)));
    }

    public void resolve(Reader input, Writer output) {
        WordWriter wordWriter = new WordWriter(output);
        new WordReader(input).readAll(word -> resolveWord(word, wordWriter));
    }

    private void resolveWord(Word word, WordWriter output) {
        anagramResolver.resolve(word).write(output);
    }
}