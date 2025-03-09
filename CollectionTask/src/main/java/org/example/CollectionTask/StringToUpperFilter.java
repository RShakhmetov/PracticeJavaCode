package org.example.CollectionTask;

public class StringToUpperFilter implements Filter {

    @Override
    public Object apply(Object o) {
        if (o.getClass() == String.class) {
            return o;
        } else {
            return null;
        }
    }
}
