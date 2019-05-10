package com.example.kata.args;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class ListOption<T> extends StrictOption {
    public ListOption(char flag) {
        super(flag);
    }

    @Override
    protected List<T> getDefaultValue() {
        return Collections.emptyList();
    }

    @Override
    protected List<T> parseActualValue(Segment segment) {
        return splitCsvString(segment).map(this::parseListElement).collect(Collectors.toList());
    }

    private Stream<String> splitCsvString(Segment segment) {
        return Arrays.stream(segment.getValuePart().split(","));
    }

    protected abstract T parseListElement(String valueString);
}
