package com.manenkov;

public interface Set<T> extends Iterable<T> {
    boolean contains(final T element);
    void add(final T element) throws Exception;
    void remove(final T element) throws Exception;
    int size();
}
