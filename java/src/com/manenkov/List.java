package com.manenkov;

public interface List<T> extends Iterable<T> {
    T get(final int index) throws Exception;
    T first();
    T last();
    void set(final T element, final int position) throws Exception;
    void add(final T element) throws Exception;
    void add(final T element, final int position) throws Exception;
    void remove(final int position) throws Exception;
    int size();
}
