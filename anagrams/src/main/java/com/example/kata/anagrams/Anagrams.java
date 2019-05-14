package com.example.kata.anagrams;

public class Anagrams {
    private final AnagramResolver anagramResolver;

    public Anagrams(WordReader wordlistReader) {
        this.anagramResolver = new AnagramResolver(newDictionary(wordlistReader));
    }

    private Dictionary newDictionary(WordReader wordlistReader) {
        Dictionary dictionary = new Dictionary();
        wordlistReader.readAll(dictionary::add);
        return dictionary;
    }

    public void resolve(WordReader input, WordWriter output) {
        input.readAll(word -> resolveForSingleWord(word, output));
    }

    private void resolveForSingleWord(Word word, WordWriter output) {
        anagramResolver.resolve(word).write(output);
    }
}
