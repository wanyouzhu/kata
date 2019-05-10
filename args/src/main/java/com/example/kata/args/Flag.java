package com.example.kata.args;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@ToString
@EqualsAndHashCode
public class Flag {
    private final char name;

    protected Flag(char name) {
        this.name = name;
    }

    public static Flag of(char name) {
        return new Flag(name);
    }

    public static boolean isFlag(String token) {
        return isNotBlank(token) && token.matches("-[a-zA-Z]");
    }

    public boolean match(String token) {
        return StringUtils.equals(token, "-" + name);
    }
}
