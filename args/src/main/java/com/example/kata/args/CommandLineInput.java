package com.example.kata.args;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.lang.Math.min;

public class CommandLineInput {
    private final List<String> args;

    public CommandLineInput(String[] args) {
        this.args = Arrays.asList(args);
    }

    public Segment match(Flag flag) {
        Optional<Integer> first = getFirst(flag);
        Optional<Integer> last = first.map(this::getLast);
        return new Segment(args.subList(first.orElse(0), last.orElse(0)));
    }

    private Integer getLast(Integer first) {
        return getRange(first + 1).filter(i -> matchAnyFlag(args.get(i))).boxed().findFirst().orElse(args.size());
    }

    private Optional<Integer> getFirst(Flag flag) {
        return getRange(0).filter(i -> matchSpecificFlag(args.get(i), flag)).boxed().findFirst();
    }

    private IntStream getRange(int a) {
        return IntStream.range(min(a, args.size()), args.size());
    }

    private boolean matchSpecificFlag(String token, Flag flag) {
        return flag.match(token);
    }

    private boolean matchAnyFlag(String token) {
        return Flag.isFlag(token);
    }
}
