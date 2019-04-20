package com.example.kata.args;

import lombok.Getter;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Getter
class Tokenizer {
    private final String input;
    private final List<String> tokens = newArrayList();
    private StringBuilder builder = new StringBuilder();
    private int quotationMark = 0;
    private boolean escapeStarted = false;

    Tokenizer(String input) {
        this.input = input;
        tokenize();
    }

    private void tokenize() {
        for (int i = 0; i < input.length(); ++i) {
            if (input.charAt(i) == '\\' && !escapeStarted && quotationMark != 0) {
                escapeStarted = true;
                continue;
            }

            if (escapeStarted) {
                builder.append(input.charAt(i));
                escapeStarted = false;
                continue;
            }

            if (isQuotationMark(input.charAt(i)) && quotationMark == 0) {
                quotationMark = input.charAt(i);
                continue;
            }

            if (isQuotationMark(input.charAt(i)) && quotationMark == input.charAt(i)) {
                quotationMark = 0;
                continue;
            }

            if (input.charAt(i) == ',' && quotationMark == 0) {
                tokens.add(builder.toString());
                builder = new StringBuilder();
                continue;
            }

            builder.append(input.charAt(i));
        }

        if (builder.length() > 0) {
            tokens.add(builder.toString());
        }
    }

    private boolean isQuotationMark(int mark) {
        return mark == '\'' || mark == '"';
    }
}
