package com.example.kata.anagram;

public class Anagrams {
    private final AnagramResolver anagramResolver;

    public Anagrams(WordReader wordListFile) {
        anagramResolver = new AnagramResolver(new Dictionary(wordListFile));
    }

    public void resolve(WordReader input, WordWriter output) {
        input.readAll(word -> resolveWord(word, output));
    }

    private void resolveWord(Word word, WordWriter output) {
        anagramResolver.resolve(word).write(output);
    }
}