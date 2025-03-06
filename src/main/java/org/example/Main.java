package org.example;

import java.util.ArrayList;
import java.util.List;

class Snapshot {

    private final String text;

    public Snapshot(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}

class MyStringBuilder {
    private final StringBuilder stringBuilder;
    List<Snapshot> snapshots;
    private static final int capacity = 10;


    public MyStringBuilder() {
        stringBuilder = new StringBuilder();
        snapshots = new ArrayList<>();
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
            stringBuilder.append(snapshots.removeLast().getText());
        }
    }

    private void saveSnapshot() {
        if (snapshots.size() > capacity) {
            snapshots.removeFirst();
        }
        snapshots.add(new Snapshot(stringBuilder.toString()));
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        MyStringBuilder sb = new MyStringBuilder();
        sb.append("Java");
        sb.append(" Code");
        System.out.println(sb);
        sb.undo();
        System.out.println(sb);
        sb.delete(2);
        System.out.println(sb);
        sb.undo();
        System.out.println(sb);
        sb.append(" Java");
        sb.append(" Codding");
        sb.append(" is");
        sb.append(" awesome");
        sb.append("!");
        sb.append(" Let's");
        sb.append(" continue");
        sb.append(" adding");
        sb.append(" more");
        sb.append(" text");
        sb.append(" to");
        sb.append(" test");
        sb.append(" the");
        sb.append(" limit.");
        System.out.println(sb);
        sb.undo();
        System.out.println(sb);
    }
}