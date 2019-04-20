package com.example.kata.args;

import lombok.Getter;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Getter
class Tokenizer {
    private final String input;
    private final char delimiter;
    private final List<String> tokens = newArrayList();
    private StringBuilder builder = new StringBuilder();
    private int quotationMark = 0;
    private boolean escapeStarted = false;
    private boolean flagStarted = false;

    Tokenizer(String input) {
        this(input, '\0');
    }

    Tokenizer(String input, char delimiter) {
        this.input = input;
        this.delimiter = delimiter;
        tokenize();
    }

    private void tokenize() {
        input.codePoints().forEach(this::collect);
        endToken();
    }

    private void collect(int current) {
        if (collectSpecialChar(current)) return;
        builder.appendCodePoint(current);
    }

    private boolean collectSpecialChar(int current) {
        return collectEscape(current) || collectQuotation(current) || collectDelimiter(current);
    }

    private boolean collectDelimiter(int current) {
        return collectCharDelimiter(current) || collectFlagDelimiter(current);
    }

    private boolean collectFlagDelimiter(int current) {
        return collectFlagStart(current) || collectFlagEnd(current);
    }

    private boolean collectFlagStart(int current) {
        if (isQuotationOpen() || current != '-' || flagStarted) return false;
        flagStarted = true;
        return true;
    }

    private boolean collectFlagEnd(int current) {
        if (isQuotationOpen() || !flagStarted || !isFlag(current)) return false;
        endToken();
        builder.append('-').appendCodePoint(current);
        endToken();
        flagStarted = false;
        return true;
    }

    private boolean isFlag(int current) {
        return Character.isLowerCase(current) || Character.isUpperCase(current);
    }

    private boolean collectCharDelimiter(int current) {
        if (!(isQuotationClosed() && isDelimiter(current))) return false;
        endToken();
        return true;
    }

    private boolean collectEscape(int current) {
        return collectEscapeStart(current) || collectEscapeEnd(current);
    }

    private boolean collectEscapeEnd(int current) {
        if (!escapeStarted) return false;
        endEscape(current);
        return true;
    }

    private boolean collectEscapeStart(int current) {
        if (!isQuotationOpen() || !isEscapeLeading(current) || escapeStarted) return false;
        startEscape(current);
        return true;
    }

    private boolean collectQuotation(int current) {
        return collectQuotationStart(current) || collectQuotationEnd(current);
    }

    private boolean collectQuotationEnd(int current) {
        if (!(quotationMark == current && isQuotationMark(current))) return false;
        closeQuotation(current);
        return true;
    }

    private boolean collectQuotationStart(int current) {
        if (!(isQuotationClosed() && isQuotationMark(current))) return false;
        openQuotation(current);
        return true;
    }

    private void endToken() {
        tokens.add(builder.toString());
        builder = new StringBuilder();
    }

    private boolean isQuotationMark(int mark) {
        return mark == '\'' || mark == '"';
    }

    private void closeQuotation(int current) {
        builder.appendCodePoint(current);
        quotationMark = 0;
    }

    private void openQuotation(int current) {
        builder.appendCodePoint(current);
        quotationMark = current;
    }

    private boolean isDelimiter(int current) {
        return current == delimiter;
    }

    private boolean isQuotationClosed() {
        return quotationMark == 0;
    }

    private void endEscape(int current) {
        builder.appendCodePoint(current);
        escapeStarted = false;
    }

    private void startEscape(int current) {
        builder.appendCodePoint(current);
        escapeStarted = true;
    }

    private boolean isQuotationOpen() {
        return quotationMark != 0;
    }

    private boolean isEscapeLeading(int current) {
        return current == '\\';
    }
}
