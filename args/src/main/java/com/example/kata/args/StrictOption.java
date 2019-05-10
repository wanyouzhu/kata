package com.example.kata.args;

public abstract class StrictOption extends Option {
    protected StrictOption(char flag) {
        super(flag);
    }

    @Override
    protected void checkSegment(Segment segment) {
        if (segment.isValuePartMissing()) {
            throw new ArgsException("Missing value part: " + segment);
        }
    }
}
