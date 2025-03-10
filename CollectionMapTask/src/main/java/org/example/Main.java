package org.example;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String[] array = {"bad", "bad", "bad", "bad", "bad", "good", "good", "good", "good", "normal"};
        Integer[] arrayNumber = {1, 2, 3, 4, 5, 3, 2, 1};
        System.out.println(counterElements(arrayNumber));
        System.out.println(counterElements(array));
    }

    public static <T> Map<T, Integer> counterElements(T[] array) {
        Map<T, Integer> resultMap = new HashMap<>();
        for (T element : array) {
            resultMap.merge(element, 1, Integer::sum);
        }
        return resultMap;
    }
}