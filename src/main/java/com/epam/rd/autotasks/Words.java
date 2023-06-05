package com.epam.rd.autotasks;


import java.util.*;
import java.util.stream.Collectors;

public class Words {

    public String countWords(List<String> lines) {
        String words = lines.stream()
                .flatMap(line -> Arrays.stream(line.split("[ .,‘’(“—/:?!”;*)'\"-]|\\s+]"))
                            .map(String::toLowerCase))
                        .filter(word -> word.length() >= 4)
                        .collect(Collectors.groupingBy(word -> word, Collectors.counting()))
                        .entrySet()
                        .stream()
                        .filter(collectorListEntry -> collectorListEntry.getValue() >= 10)
                        .sorted(Map.Entry.comparingByKey())
                        .sorted((o1,o2) -> o2.getValue().compareTo(o1.getValue()))
                        .map(stringLongEntry -> String.format("%s - %d\n", stringLongEntry.getKey(), stringLongEntry.getValue()))
                        .collect(Collectors.joining());
        StringBuilder stringBuilder = new StringBuilder(words);
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }
}
