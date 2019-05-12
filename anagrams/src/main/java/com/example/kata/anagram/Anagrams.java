package com.example.kata.anagram;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class Anagrams {
    private Map<String, Set<String>> wordMap = Maps.newHashMap();

    public Anagrams(BufferedReader words) {
        words.lines().forEach(word -> {
            Set<String> entry = wordMap.get(toBaseForm(word));
            if (entry != null) {
                entry.add(word);
            } else {
                wordMap.put(toBaseForm(word), Sets.newHashSet(word));
            }
        });
    }

    private String toBaseForm(String word) {
        int[] ints = word.codePoints().sorted().toArray();
        return new String(ints, 0, ints.length);
    }

    public Map<String, Set<String>> resolve(Stream<String> input) {
        Map<String, Set<String>> result = Maps.newLinkedHashMap();
        input.forEach(x -> {
            Set<String> found = apply(x);
            if (found.size() > 0) {
                result.put(x, found);
            }
        });
        return result;
    }

    private Set<String> apply(String x) {
        Set<String> orDefault = wordMap.getOrDefault(toBaseForm(x), Collections.emptySet());
        if (orDefault.isEmpty()) return orDefault;
        HashSet<String> strings = Sets.newHashSet(orDefault);
        strings.remove(x);
        return strings;
    }

    public void resolve(BufferedReader input, Writer output) throws IOException {
        Map<String, Set<String>> stringSetMap = resolve(input.lines());
        for (Map.Entry<String, Set<String>> x : stringSetMap.entrySet()) {
            String s = x.getKey() + ", " + StringUtils.join(x.getValue().iterator(), ", ");
            output.write(s + "\n");
        }
    }
}