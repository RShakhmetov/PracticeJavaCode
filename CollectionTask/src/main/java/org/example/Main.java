package org.example;

import org.example.CollectionTask.FilterClass;
import org.example.CollectionTask.SupplierFilter;

public class Main {
    public static void main(String[] args) {
        String[] array = {"hello", "", "world", "java", ""};
        FilterClass filter = new FilterClass();

        String[] filteredArray = (String[]) SupplierFilter.filter(array, filter);

        for (String element : filteredArray) {
            System.out.println(element);
        }
    }
}