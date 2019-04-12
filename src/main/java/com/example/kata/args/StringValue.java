package com.example.kata.args;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
class StringValue extends Value {
    private final String value;
}
