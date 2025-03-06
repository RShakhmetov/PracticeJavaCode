package org.example;

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
