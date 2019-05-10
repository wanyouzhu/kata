package com.example.kata.args;

public abstract class Option {
    protected final Flag flag;

    protected Option(char flag) {
        this.flag = Flag.of(flag);
    }

    public Argument match(CommandLineInput input) {
        return new Argument(flag, parseValue(input.match(flag)));
    }

    protected Object parseValue(Segment segment) {
        return segment.isEmpty() ? getDefaultValue() : parseCheckedValue(segment);
    }

    private Object parseCheckedValue(Segment segment) {
        checkSegment(segment);
        return parseActualValue(segment);
    }

    protected abstract void checkSegment(Segment segment);

    protected abstract Object getDefaultValue();

    protected abstract Object parseActualValue(Segment segment);
}
