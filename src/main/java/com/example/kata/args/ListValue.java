package com.example.kata.args;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
class ListValue extends Value {
    private final List<Value> value;
}
