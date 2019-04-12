package com.example.kata.args;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

class ListValueParser extends ValueParser {
    private final ValueParser elementParser;

    ListValueParser(ValueParser elementParser) {
        this.elementParser = elementParser;
    }

    @Override
    Optional<Value> parse(CharStream input) {
        final int initialPosition = input.getPosition();
        List<Value> elements = Lists.newArrayList();

        Optional<Value> firstElement = elementParser.parse(input);
        if (!firstElement.isPresent()) {
            input.resetPosition(initialPosition);
            return Optional.empty();
        }
        elements.add(firstElement.get());

        while (!input.eof() && input.get() == ',') {
            input.read();
            Optional<Value> element = elementParser.parse(input);
            if (!element.isPresent()) {
                input.resetPosition(initialPosition);
                return Optional.empty();
            }
            elements.add(element.get());
        }

        if (elements.isEmpty()) {
            input.resetPosition(initialPosition);
            return Optional.empty();
        }

        return Optional.of(Value.ofList(elements));
    }

    @Override
    Value getDefaultValue() {
        return Value.ofList(Collections.emptyList());
    }
}
