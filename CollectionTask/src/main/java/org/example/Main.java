package org.example;

import org.example.CollectionTask.StringToUpperFilter;
import org.example.CollectionTask.SupplierFilter;

import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        Object[] array = {"hello", null, List.of(1,2,3,4), "java", "2", 2};
        StringToUpperFilter filter = new StringToUpperFilter();

        Object[] filteredArray = Objects.requireNonNull(SupplierFilter.filter(array, filter)).toArray();

        for (Object element : filteredArray) {
            System.out.println(element);
        }
    }
}
