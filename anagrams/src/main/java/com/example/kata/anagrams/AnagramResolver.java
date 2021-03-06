package com.example.kata.anagrams;

public class AnagramResolver {
    private final Dictionary dictionary;

    public AnagramResolver(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public Anagram resolve(Word word) {
        return new Anagram(word, dictionary.lookup(word));
    }
}
