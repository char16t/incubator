package com.manenkov;

public interface Heap<T extends Comparable<T>> {
    void insert(T value);
    T remove();
    int size();
}
