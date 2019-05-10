package com.example.kata.args;

import org.apache.commons.lang3.StringUtils;

public class BooleanOption extends Option {
    public BooleanOption(char flag) {
        super(flag);
    }

    @Override
    protected Boolean getDefaultValue() {
        return false;
    }

    @Override
    protected Boolean parseActualValue(Segment segment) {
        if (segment.isValuePartMissing()) return true;
        if (matchFalse(segment.getValuePart())) return false;
        if (matchTrue(segment.getValuePart())) return true;
        throw new ArgsException("Malformed boolean value near: " + segment.getValuePart());
    }

    @Override
    protected void checkSegment(Segment segment) {
    }

    private boolean matchTrue(String valueString) {
        return match(valueString, "true");
    }

    private boolean matchFalse(String valueString) {
        return match(valueString, "false");
    }

    private boolean match(String valueString, String expect) {
        return StringUtils.equals(valueString, expect);
    }
}
