package com.example.kata.args;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
class Flag {
    private final char name;

    static Flag of(char name) {
        return new Flag(name);
    }
}
