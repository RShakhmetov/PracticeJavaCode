package org.example;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class MyStringBuilder {

    private final StringBuilder stringBuilder;

    private final LinkedList<Snapshot> snapshots;

    private final int capacity;

    public MyStringBuilder() {
        this(10);
    }

    public MyStringBuilder(int capacity) {
        stringBuilder = new StringBuilder();
        snapshots = new LinkedList<>();
        this.capacity = capacity;
    }

    public void append(String line) {
        saveSnapshot();
        stringBuilder.append(line);
    }

    public void delete(int end) {
        saveSnapshot();
        if (end > stringBuilder.length()) {
            end = stringBuilder.length();
        }
        stringBuilder.setLength(stringBuilder.length() - end);
    }

    public void undo() {
        if (!snapshots.isEmpty()) {
            stringBuilder.setLength(0);
            stringBuilder.append(Objects.requireNonNull(snapshots.pollLast()).getText());
        }
    }

    private void saveSnapshot() {
        if (snapshots.size() > capacity) {
            snapshots.pollFirst();
        }
        snapshots.add(new Snapshot(stringBuilder.toString()));
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}
