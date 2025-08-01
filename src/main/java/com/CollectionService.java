package com;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionService {

    public <T> Map<T, Long>  countsOfElements(T[] e) {
        Stream<T> stream = Arrays.stream(e);
        return stream
                .collect(Collectors.groupingBy(
                      item -> item,
                      Collectors.counting()
                ));
    }
}
