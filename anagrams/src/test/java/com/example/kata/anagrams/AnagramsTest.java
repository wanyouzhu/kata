package com.example.kata.anagrams;

import lombok.Cleanup;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.is;

public class AnagramsTest {
    @Test
    public void should_write_resolved_anagrams_into_output_file() {
        StringReader wordlist = new StringReader("ab\nba");
        StringReader input = new StringReader("ab");
        StringWriter output = new StringWriter();
        Anagrams anagrams = new Anagrams(new WordReader(wordlist));
        anagrams.resolve(new WordReader(input), new WordWriter(output));
        Assert.assertThat(output.toString(), is("ab, ba\n"));
    }

    @Test
    public void should_resolve_48162_anagrams_form_the_kata_wordlist() throws IOException {
        String wordListFilename = "/Volumes/workspace/projects/test/kata/anagrams/wordlist.txt";
        String outputFilename = "/Volumes/workspace/projects/test/kata/anagrams/output.txt";
        @Cleanup Reader wordlist = new FileReader(wordListFilename);
        @Cleanup Reader input = new FileReader(wordListFilename);
        @Cleanup Writer output = new FileWriter(outputFilename);
        Anagrams anagrams = new Anagrams(new WordReader(wordlist));
        anagrams.resolve(new WordReader(input), new WordWriter(output));
        output.flush();
        Assert.assertThat(Files.lines(Paths.get(outputFilename)).count(), is(48162L));
    }
}
