package com.example.kata.args;

public class IntegerListOption extends ListOption<Integer> {
    public IntegerListOption(char flag) {
        super(flag);
    }

    @Override
    protected Integer parseListElement(String valueString) {
        return Integer.parseInt(valueString);
    }
}
