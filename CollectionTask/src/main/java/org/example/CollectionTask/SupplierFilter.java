package org.example.CollectionTask;

import java.util.ArrayList;
import java.util.List;

public class SupplierFilter {

    public static <T> List<T> filter(T[] array, StringToUpperFilter filterClass) {
        if (array.length == 0) {
            return null;
        }
        List<T> result = new ArrayList<>();
        for (T element : array) {
            if (element != null) {
                result.add(filterClass.apply(element));
            }
        }
        return result;
    }
}
