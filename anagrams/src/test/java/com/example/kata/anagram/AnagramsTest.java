package com.example.kata.anagram;

import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnagramsTest {
    @Test
    public void should_return_all_words_have_the_same_letters_except_itself() throws IOException {
        try (BufferedReader input = new BufferedReader(new StringReader("ab"))) {
            try (BufferedReader words = new BufferedReader(new StringReader("ab\nba"))) {
                Anagrams anagrams = new Anagrams(words);
                StringWriter output = new StringWriter();
                anagrams.resolve(input, output);
                assertThat(output.toString(), is("ab, ba\n"));
            }
        }
    }

    @Test
    public void should_return_empty_word_set_if_no_anagram_found() throws IOException {
        try (BufferedReader input = new BufferedReader(new StringReader("ab"))) {
            try (BufferedReader words = new BufferedReader(new StringReader("bb"))) {
                Anagrams anagrams = new Anagrams(words);
                StringWriter output = new StringWriter();
                anagrams.resolve(input, output);
                assertThat(output.toString(), is(""));
            }
        }
    }

    @Test
    public void test_against_huge_samples() throws IOException {
        String first = "/workspace/projects/test/kata/anagrams/wordlist.txt";
        String fileName = "/workspace/projects/test/kata/anagrams/output.txt";
        try (BufferedReader input = new BufferedReader(new FileReader(first))) {
            try (BufferedReader words = new BufferedReader(new FileReader(first))) {
                Anagrams anagrams = new Anagrams(words);
                Writer output = new BufferedWriter(new FileWriter(fileName));
                anagrams.resolve(input, output);
                output.flush();
                assertThat(Files.lines(Paths.get(fileName)).count(), is(48162L));
            }
        }
    }
}
