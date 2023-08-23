package com.manenkov;

public class MinHeap<T extends Comparable<T>> implements Heap<T> {

    private int size;
    private int capacity;
    private Object[] data;

    public MinHeap() {
        this(1024);
    }

    public MinHeap(final int capacity) {
        this(0, capacity);
    }

    private MinHeap(int size, int capacity) {
        this(size, capacity, new Object[capacity]);
    }

    private MinHeap(int size, int capacity, Object[] data) {
        this.size = size;
        this.capacity = capacity;
        this.data = data;
    }

    @Override
    public void insert(final T value) {
        if (this.size >= this.capacity) {
            return;
        }
        this.data[this.size] = value;

        int currentPosition = this.size;
        int parentPosition = parent(currentPosition);
        T current = element(currentPosition);
        T parent = element(parentPosition);
        while (current.compareTo(parent) < 0) {
            swap(currentPosition, parentPosition);
            currentPosition = parentPosition;
            parentPosition = parent(currentPosition);
            current = element(currentPosition);
            parent = element(parentPosition);
        }
        this.size += 1;
    }

    @Override
    public T remove() {
        if (this.size == 0) {
            return null;
        }
        T peak = element(0);
        swap(0, --this.size);
        minHeapify();
        return peak;
    }

    @Override
    public int size() {
        return this.size;
    }

    private void minHeapify() {
        int currentPosition = 0;
        if (isLeaf(currentPosition)) {
            return;
        }
        int leftPosition = left(currentPosition);
        int rightPosition = right(currentPosition);
        T current = element(currentPosition);
        T left = element(leftPosition);
        T right = element(rightPosition);

        while ((current.compareTo(left) > 0 && leftPosition < this.size)
                || (current.compareTo(right) > 0 && rightPosition < this.size)) {

            int swapPosition = left.compareTo(right) < 0
                    ? leftPosition < this.size ? leftPosition : rightPosition
                    : rightPosition < this.size ? rightPosition : leftPosition;

            swap(currentPosition, swapPosition);
            currentPosition = swapPosition;
            if (isLeaf(currentPosition)) {
                break;
            }
            leftPosition = left(currentPosition);
            rightPosition = right(currentPosition);
            current = element(currentPosition);
            left = element(leftPosition);
            right = element(rightPosition);
        }
    }

    private int parent(final int pos) {
        return (pos - 1) / 2;
    }

    private int left(final int pos) {
        return (2 * pos) + 1;
    }

    private int right(final int pos) {
        return (2 * pos) + 2;
    }

    private boolean isLeaf(final int pos) {
        return pos > this.size / 2;
    }

    private void swap(final int first, final int second) {
        T buffer = element(first);
        this.data[first] = this.data[second];
        this.data[second] = buffer;
    }

    @SuppressWarnings("unchecked")
    private T element(final int position) {
        return (T) this.data[position];
    }
}
