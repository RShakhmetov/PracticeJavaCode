package org.example;

import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> list = List.of("bad", "bad", "bad", "bad", "bad", "good", "good", "good", "good", "normal");
        System.out.println(counterElements(list));
    }

    public static HashMap<String, Object> counterElements(List<?> arrayList) {
        HashMap<String, Object> resultMap = new HashMap<>();
        for (Object o : arrayList) {
            int counter = 0;
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