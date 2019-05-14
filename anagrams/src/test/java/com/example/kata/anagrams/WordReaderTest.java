package com.example.kata.anagrams;

import com.example.kata.anagrams.Word;
import com.example.kata.anagrams.WordReader;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class WordReaderTest {
    @Test
    public void can_read_words_from_file_line_by_line() {
        WordReader wordReader = new WordReader(new StringReader("a\nb\nc"));
        List<Word> wordsRead = new ArrayList<>();
        wordReader.readAll(wordsRead::add);
        assertThat(wordsRead, is(ImmutableList.of(new Word("a"), new Word("b"), new Word("c"))));
    }
}
