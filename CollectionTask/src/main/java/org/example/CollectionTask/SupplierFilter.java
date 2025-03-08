package org.example.CollectionTask;

import java.lang.reflect.Array;

public class SupplierFilter {

    public static Object[] filter(Object[] array, FilterClass filterClass) {
        if (array.length == 0) {
            return null;
        }
        Object[] resultArray = (Object[]) Array.newInstance(array.getClass().getComponentType(), array.length);
        int count = 0;
        for (Object element : array) {
            Object filteredElement = filterClass.apply(element);
            if (filteredElement != null) {
                resultArray[count++] = filterClass.apply(element);
            }
        }
        return resultArray;
    }
}
