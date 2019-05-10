package com.example.kata.args;

public class IntegerOption extends StrictOption {
    public IntegerOption(char flag) {
        super(flag);
    }

    @Override
    protected Integer getDefaultValue() {
        return 0;
    }

    @Override
    protected Integer parseActualValue(Segment segment) {
        try {
            return Integer.parseInt(segment.getValuePart());
        }
        catch (NumberFormatException e) {
            throw new ArgsException("Malformed integer value near: " + segment);
        }
    }
}
