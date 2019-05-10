package com.example.kata.args;

import lombok.ToString;

import java.util.List;

@ToString
public class Segment {
    private final List<String> parts;

    public Segment(List<String> parts) {
        this.parts = parts;
    }

    public boolean isEmpty() {
        return parts.isEmpty();
    }

    public boolean isValuePartMissing() {
        return parts.size() == 1;
    }

    public String getValuePart() {
        return parts.get(1);
    }
}
