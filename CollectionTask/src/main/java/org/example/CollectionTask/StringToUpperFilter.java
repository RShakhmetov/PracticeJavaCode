package org.example.CollectionTask;

public class StringToUpperFilter implements Filter {

    @Override
    public <T> T apply(T o) {
        if (o.getClass() == String.class) {
            return o;
        } else {
            return null;
        }
    }
}
