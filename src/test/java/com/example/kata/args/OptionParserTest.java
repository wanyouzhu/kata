package com.example.kata.args;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OptionParserTest {
    private CharStream input;
    private Flag flagParserResult;
    private Value valueParserResult;
    private FlagParser flagParser;
    private ValueParser valueParser;
    private FlagParser noResultFlagParser;
    private ValueParser noResultValueParser;

    @Before
    public void setup() {
        input = mock(CharStream.class);
        when(input.eof()).thenReturn(true);
        flagParserResult = Flag.of('t');
        valueParserResult = Value.ofNumber(8);
        flagParser = mockFlagParser(Flag.of('t'));
        valueParser = mockValueParser(Value.ofNumber(8));
        noResultFlagParser = mockFlagParser(null);
        noResultValueParser = mockValueParser(null);
    }

    @Test
    public void testParse() {
        assertThat(OptionParser.create(flagParser, valueParser).parse(input), is(Optional.of(Option.of(flagParserResult, valueParserResult))));
        assertThat(OptionParser.create(noResultFlagParser, valueParser).parse(input), is(Optional.empty()));
        assertThat(OptionParser.create(flagParser, noResultValueParser).parse(input), is(Optional.of(Option.of(flagParserResult, noResultValueParser.getDefaultValue()))));
    }

    private ValueParser mockValueParser(Value value) {
        ValueParser result = mock(ValueParser.class);
        when(result.parse(input)).thenReturn(Optional.ofNullable(value));
        return result;
    }

    private FlagParser mockFlagParser(Flag flag) {
        FlagParser result = mock(FlagParser.class);
        when(result.parse(input)).thenReturn(Optional.ofNullable(flag));
        return result;
    }

}