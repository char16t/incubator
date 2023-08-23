package com.manenkov;

public interface Iterator<T> {
    boolean hasNext();
    T current();
    T next();
    void set(T value) throws Exception;
}
