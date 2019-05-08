package com.example.kata.args;

class StringsValueType extends ListValueType {
    @Override
    Object parseElement(String valueString) {
        return new StringValueType().parseValue(valueString).getValue();
    }
}
