package com;

public class Main {
    public static void main(String[] args) {
        StringBuilderWrapper stringBuilderWrapper = new StringBuilderWrapper("javacode");
        System.out.println("Now: " + stringBuilderWrapper);
        stringBuilderWrapper.deleteCharAt(2);
        System.out.println("Before deleting char: " + stringBuilderWrapper);
        stringBuilderWrapper.deleteCharAt(2);
        System.out.println("Before repeat deleting char: " + stringBuilderWrapper);
        stringBuilderWrapper.undo();
        System.out.println("Before restore: " + stringBuilderWrapper);
        stringBuilderWrapper.undo();
        System.out.println("Before restore: " + stringBuilderWrapper);
    }
}