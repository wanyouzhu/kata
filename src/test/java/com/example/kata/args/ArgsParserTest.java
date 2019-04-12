package com.example.kata.args;

import com.google.common.collect.ImmutableSet;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

public class ArgsParserTest {
    public @Rule ExpectedException exceptionRule = ExpectedException.none();
    private CharStream input;
    private Option optionT;
    private Option optionP;
    private Option defaultOption;

    @Before
    public void setUp() {
        input = Mockito.mock(CharStream.class);
        Mockito.when(input.eof()).thenReturn(true);
        optionT = Option.of(Flag.of('t'), Value.ofNumber(8));
        optionP = Option.of(Flag.of('p'), Value.ofNumber(4));
        defaultOption = Option.of(Flag.of('x'), Value.ofNumber(0));
    }

    @Test
    public void testParseAllOptions() {
        assertThat(new ArgsParser(mockOptionParser(optionT)).parse(input), is(ImmutableSet.of(optionT)));
        assertThat(new ArgsParser(mockOptionParser(optionT), mockOptionParser(optionP)).parse(input), is(ImmutableSet.of(optionT, optionP)));
    }

    @Test
    public void testDefaultValue() {
        assertThat(new ArgsParser(noResultElementParserWithDefault(optionT), noResultElementParserWithDefault(optionP)).parse(input), is(ImmutableSet.of(optionP, optionT)));
    }

    private OptionParser noResultElementParserWithDefault(Option defaultOption) {
        OptionParser result = Mockito.mock(OptionParser.class);
        Mockito.when(result.parse(any())).thenReturn(Optional.empty());
        Mockito.when(result.getDefault()).thenReturn(defaultOption);
        return result;
    }

    @Test
    public void testParseUnknownOptions() {
        CharStream nonEofInput = Mockito.mock(CharStream.class);
        Mockito.when(nonEofInput.eof()).thenReturn(false);
        Mockito.when(nonEofInput.read(anyInt())).thenReturn("abc");
        exceptionRule.expect(ArgsException.class);
        exceptionRule.expectMessage("Unprocessable fragment near: 'abc'");
        new ArgsParser(mockOptionParser(optionT)).parse(nonEofInput);
    }

    @SuppressWarnings("unchecked")
    private OptionParser mockOptionParser(Option option) {
        OptionParser result = Mockito.mock(OptionParser.class);
        Mockito.when(result.parse(any())).thenReturn(Optional.of(option), Optional.empty());
        Mockito.when(result.getDefault()).thenReturn(option);
        return result;
    }
}
