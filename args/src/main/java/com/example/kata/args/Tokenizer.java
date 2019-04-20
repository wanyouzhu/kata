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
        input.codePoints().forEach(this::collect);
        endToken();
    }

    private void collect(int current) {
        if (canStartEscape(current)) {
            startEscape();
            return;
        }
        if (canEndEscape()) {
            endEscape(current);
            return;
        }
        if (canOpenQuotation(current)) {
            openQuotation(current);
            return;
        }
        if (canCloseQuotation(current)) {
            closeQuotation();
            return;
        }
        if (isDelimiter(current) && isQuotationClosed()) {
            endToken();
            return;
        }
        builder.appendCodePoint(current);
    }

    private boolean canEndEscape() {
        return escapeStarted;
    }

    private boolean canStartEscape(int current) {
        return isEscapeLeading(current) && !escapeStarted && isQuotationOpen();
    }

    private void endToken() {
        tokens.add(builder.toString());
        builder = new StringBuilder();
    }

    private boolean isQuotationMark(int mark) {
        return mark == '\'' || mark == '"';
    }

    private void closeQuotation() {
        quotationMark = 0;
    }

    private void openQuotation(int current) {
        quotationMark = current;
    }

    private boolean canCloseQuotation(int current) {
        return isQuotationMark(current) && quotationMark == current;
    }

    private boolean canOpenQuotation(int current) {
        return isQuotationMark(current) && isQuotationClosed();
    }

    private boolean isDelimiter(int current) {
        return current == ',';
    }

    private boolean isQuotationClosed() {
        return quotationMark == 0;
    }

    private void endEscape(int current) {
        builder.appendCodePoint(current);
        escapeStarted = false;
    }

    private void startEscape() {
        escapeStarted = true;
    }

    private boolean isQuotationOpen() {
        return quotationMark != 0;
    }

    private boolean isEscapeLeading(int current) {
        return current == '\\';
    }
}
