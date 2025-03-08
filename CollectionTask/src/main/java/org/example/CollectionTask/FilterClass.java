package org.example.CollectionTask;

public class FilterClass implements Filter {

    @Override
    public Object apply(Object o) {
        if (o instanceof String) {
            return ((String) o).toUpperCase();
        } else {
            return 0;
        }
    }
}
