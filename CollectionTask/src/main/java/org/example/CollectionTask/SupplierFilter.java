package org.example.CollectionTask;

import java.util.ArrayList;
import java.util.List;

public class SupplierFilter {

    public static List<String> filter(Object[] array, StringToUpperFilter filterClass) {
        if (array.length == 0) {
            return null;
        }
        List<String> result = new ArrayList<>();
        for (Object element : array) {
            if (element != null) {
                result.add((String) filterClass.apply(element));
            }
        }
        return result;
    }
}
