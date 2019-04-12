package com.example.kata.args;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
class Option {
    private final Flag flag;
    private final Value value;

    public static Option of(char flagName, Value value) {
        return new Option(Flag.of(flagName), value);
    }

    public static Option of(Flag flag, Value value) {
        return new Option(flag, value);
    }
}
