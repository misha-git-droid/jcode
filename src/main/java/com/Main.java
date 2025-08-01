package com;

public class Main {
    public static void main(String[] args) {
        CollectionService collectionService = new CollectionService();
        String[] arr = {"a", "b", "c", "c", "a", "b"};
        System.out.println(collectionService.countsOfElements(arr));
    }
}