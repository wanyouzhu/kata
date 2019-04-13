package com.example.kata.args;

import lombok.*;

import java.util.Arrays;
import java.util.stream.Collectors;

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


    static Option ofList(char flag, Integer... values) {
        return Option.of(flag, Value.ofList(Arrays.stream(values).map(Value::ofNumber).collect(Collectors.toList())));
    }

    static Option ofNumber(char flag, int value) {
        return Option.of(flag, Value.ofNumber(value));
    }

    static Option ofBoolean(char flag, boolean value) {
        return Option.of(flag, Value.ofBoolean(value));
    }

    static Option ofString(char flag, String value) {
        return Option.of(flag, Value.ofString(value));
    }
}
