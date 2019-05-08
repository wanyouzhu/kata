package com.example.kata.args;

class IntegersValueType extends ListValueType {
    @Override
    Object parseElement(String valueString) {
        return new IntegerValueType().parseValue(valueString).getValue();
    }
}
