package com.example.kata.anagram;

import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnagramsTest {
    @Test
    public void should_return_all_words_have_the_same_letters_except_itself() {
        StringWriter output = new StringWriter();
        Anagrams anagrams = new Anagrams(new WordReader(new StringReader("ab\nba")));
        anagrams.resolve(new WordReader(new StringReader("ab")), new WordWriter(output));
        assertThat(output.toString(), is("ab, ba\n"));
    }

    @Test
    public void should_return_empty_word_set_if_no_anagram_found() {
        StringWriter output = new StringWriter();
        Anagrams anagrams = new Anagrams(new WordReader(new StringReader("bb")));
        anagrams.resolve(new WordReader(new StringReader("ab")), new WordWriter(output));
        assertThat(output.toString(), is(""));
    }

    @Test
    public void test_against_huge_samples() throws IOException {
        String inputFilename = "/workspace/projects/test/kata/anagrams/wordlist.txt";
        String outputFilename = "/workspace/projects/test/kata/anagrams/output.txt";
        try (Reader input = new FileReader(inputFilename)) {
            try (Reader words = new FileReader(inputFilename)) {
                try (Writer output = new FileWriter(outputFilename)) {
                    Anagrams anagrams = new Anagrams(new WordReader(words));
                    anagrams.resolve(new WordReader(input), new WordWriter(output));
                    output.flush();
                    assertThat(Files.lines(Paths.get(outputFilename)).count(), is(48162L));
                }
            }
        }
    }
}
