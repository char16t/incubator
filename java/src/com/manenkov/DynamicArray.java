package com.manenkov;

import java.util.Arrays;

public class DynamicArray<T> implements List<T> {

    private int size;
    private int capacity;
    private T[] data;

    public DynamicArray() {
        this(1024);
    }

    public DynamicArray(int capacity) {
        this(0, capacity);
    }

    @SuppressWarnings("unchecked")
    private DynamicArray(final int size, final int capacity) {
        this(size, capacity, (T[]) new Object[capacity]);
    }

    private DynamicArray(final int size, final int capacity, final T[] data) {
        this.size = size;
        this.capacity = capacity;
        this.data = data;
    }

    @Override
    public T get(final int index) throws Exception {
        if (index < 0 || index >= size) {
            throw new Exception("Index should be in range [0, size)");
        }
        return data[index];
    }

    @Override
    public T first() {
        return size > 0 ? this.data[0] : null;
    }

    @Override
    public T last() {
        return size > 0 ? this.data[size - 1] : null;
    }

    @Override
    public void set(final T element, final int position) throws Exception {
        if (position < 0 || position >= size) {
            throw new Exception("Index should be in range [0, size)");
        }
        data[position] = element;
    }

    @Override
    public void add(final T element) throws Exception {
        add(element, size);
    }

    @Override
    public void add(final T element, final int position) throws Exception {
        if (position < 0 || position > size) {
            throw new Exception("Index should be in range [0, size]");
        }
        if (size >= capacity) {
            final int newCapacity = capacity * 2;
            this.data = Arrays.copyOf(data, newCapacity);
            this.capacity = newCapacity;
        }
        final int sizeBefore = size;
        for (int i = sizeBefore - 1; i >= position; i -= 1) {
            data[i + 1] = data[i];
        }
        data[position] = element;
        size += 1;
    }

    @Override
    public void remove(final int position) throws Exception {
        if (position < 0 || position >= size) {
            throw new Exception("Index should be in range [0, size)");
        }
        for (int i = position; i < size; i += 1) {
            data[i] = data[i + 1];
        }
        size -= 1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        final StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; i < size; i++) {
            b.append(data[i]);
            if (i == size - 1) {
                b.append(']');
                break;
            }
            b.append(", ");
        }
        return b.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DynamicArray)) return false;
        DynamicArray<?> that = (DynamicArray<?>) o;

        if (this.size != that.size) {
            return false;
        }
        for (int i = 0; i < this.size; i++) {
            if (!this.data[i].equals(that.data[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        for (int i = 0; i < size; i++) {
            T element = data[i];
            result = 31 * result + (element == null ? 0 : element.hashCode());
        }
        return result;
    }

    @Override
    public Iterator<T> getIterator() {
        return new DynamicArrayIterator();
    }

    private class DynamicArrayIterator implements Iterator<T> {

        int position;

        public DynamicArrayIterator() {
            this(0);
        }

        public DynamicArrayIterator(final int position) {
            this.position = position;
        }

        @Override
        public boolean hasNext() {
            if (position < size) {
                return true;
            }
            return false;
        }

        @Override
        public T next() {
            if (this.hasNext()) {
                return data[position++];
            }
            return null;
        }

        @Override
        public T current() {
            if (this.hasNext()) {
                return data[position];
            }
            return null;
        }

        @Override
        public void set(final T value) {
            data[position] = value;
        }
    }
}
