package com.example.kata.args;

public class StringOption extends StrictOption {
    public StringOption(char flag) {
        super(flag);
    }

    @Override
    protected String getDefaultValue() {
        return "";
    }

    @Override
    protected String parseActualValue(Segment segment) {
        return segment.getValuePart();
    }
}
