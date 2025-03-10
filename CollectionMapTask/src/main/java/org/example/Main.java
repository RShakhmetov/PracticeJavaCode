package org.example;

import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> list = List.of("bad", "bad", "bad", "bad", "bad", "good", "good", "good", "good", "normal");
        List<Long> longList = List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L);
        List<Object> objectList = List.of(1, 2, 3L, 1, 3L, 3L, "bad", "bad");
        System.out.println(counterElements(list));
        System.out.println(counterElements(longList));
        System.out.println(counterElements(objectList));
    }

    public static HashMap<String, Long> counterElements(List<?> arrayList) {
        HashMap<String, Long> resultMap = new HashMap<>();
        for (Object o : arrayList) {
            long counter = 0;
            for (Object object : arrayList) {
                if (o.equals(object)) {
                    counter++;
                }
            }
            resultMap.put(o.toString(), counter);
        }
        return resultMap;
    }
}