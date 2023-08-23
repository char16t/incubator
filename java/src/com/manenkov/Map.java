package com.manenkov;

public interface Map<K, V> extends Iterable<MapEntry<K, V>> {
    boolean contains(final K key);
    V get(final K key);
    void put(final K key, final V value) throws Exception;
    void remove(final K key) throws Exception;
    int size();
}
