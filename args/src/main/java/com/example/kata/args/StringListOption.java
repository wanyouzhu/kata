package com.example.kata.args;

public class StringListOption extends ListOption<String> {
    public StringListOption(char flag) {
        super(flag);
    }

    @Override
    protected String parseListElement(String valueString) {
        return valueString;
    }
}
