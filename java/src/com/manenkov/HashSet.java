package com.manenkov;

import java.util.Objects;

public class HashSet<T> implements Set<T> {

    private int size;
    private int capacity;
    private Object[] buckets;

    public HashSet() {
        this(1024);
    }

    public HashSet(final int capacity) {
        this(0, capacity);
    }

    private HashSet(int size, int capacity) {
        this(size, capacity, new Object[capacity]);
    }

    private HashSet(final int size, final int capacity, final Object[] buckets) {
        this.size = size;
        this.capacity = capacity;
        this.buckets = buckets;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean contains(final T element) {
        final int bucketId = Objects.hashCode(element) % capacity;
        final List<T> values = (List<T>) this.buckets[bucketId];
        if (values == null) {
            return false;
        }
        final Iterator<T> it = values.getIterator();
        while (it.hasNext()) {
            if (Objects.equals(it.next(), element)) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void add(final T element) throws Exception {
        final int bucketId = Objects.hashCode(element) % capacity;
        final List<T> values = (List<T>) this.buckets[bucketId];
        if (values == null) {
            final List<T> emptyLinkedList = new LinkedList<>();
            this.buckets[bucketId] = emptyLinkedList;
            emptyLinkedList.add(element);
            size += 1;
            return;
        }
        final Iterator<T> it = values.getIterator();
        boolean wasFound = false;
        while (it.hasNext()) {
            final T current = it.current();
            if (Objects.equals(current, element)) {
                it.set(element);
                wasFound = true;
                break;
            }
            it.next();
        }
        if (!wasFound) {
            values.add(element);
            size += 1;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void remove(final T element) throws Exception {
        final int bucketId = Objects.hashCode(element) % capacity;
        final List<T> values = (List<T>) this.buckets[bucketId];
        if (values == null) {
            return;
        }
        final Iterator<T> it = values.getIterator();
        int position = 0;
        while (it.hasNext()) {
            T current = it.next();
            if (Objects.equals(current, element)) {
                // TODO: Fix me. We already found node to delete.
                //       Don't need find it again.
                values.remove(position);
                this.size -= 1;
                return;
            }
            position += 1;
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<T> getIterator() {
        return new HashSetIterator(this);
    }

    @Override
    public String toString() {
        final Iterator<T> it = new HashSetIterator(this);
        final StringBuilder sb = new StringBuilder();
        int count = 0;
        sb.append("{");
        while (it.hasNext()) {
            T current = it.next();
            sb.append(current);
            if (count < size - 1) {
                sb.append(", ");
            }
            count += 1;
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HashSet)) return false;
        HashSet<?> hashSet = (HashSet<?>) o;
        if (size != hashSet.size || capacity != hashSet.capacity) return false;

        Iterator<?> it = new HashSetIterator(this);
        int thisHash = 1;
        while (it.hasNext()) {
            Object current = it.next();
            thisHash = 31 * thisHash + (current == null ? 0 : current.hashCode());
        }

        Iterator<?> thatIt = hashSet.getIterator();
        int thatHash = 1;
        while (thatIt.hasNext()) {
            Object current = thatIt.next();
            thatHash = 31 * thatHash + (current == null ? 0 : current.hashCode());
        }
        return thisHash == thatHash;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size, capacity);
        final Iterator<T> it = new HashSetIterator(this);
        while (it.hasNext()) {
            T current = it.next();
            result = 31 * result + (current == null ? 0 : current.hashCode());
        }
        return result;
    }

    private class HashSetIterator implements Iterator<T> {
        private final HashSet<T> hashSet;
        private int currentBucket;
        private Object[] bucketIterators;

        public HashSetIterator(HashSet<T> hashSet) {
            this.hashSet = hashSet;
            this.currentBucket = 0;
            this.bucketIterators = new Object[hashSet.buckets.length + 1];
        }

        @Override
        public boolean hasNext() {
            if (currentBucket >= hashSet.capacity) return false;
            List<T> b = (List<T>) hashSet.buckets[currentBucket];
            while (b == null && currentBucket + 1 < hashSet.capacity) {
                b = (List<T>) hashSet.buckets[++currentBucket];
            }
            if (b == null) {
                return false;
            }
            Iterator<T> it = (Iterator<T>) bucketIterators[currentBucket];
            if (it == null) {
                it = b.getIterator();
                bucketIterators[currentBucket] = it;
            }
            if (!it.hasNext()) {
                currentBucket += 1;
                return hasNext();
            }
            return true;
        }

        @Override
        public T current() {
            if (this.hasNext()) {
                final Iterator<T> it = (Iterator<T>)  this.bucketIterators[this.currentBucket];
                return it.current();
            }
            return null;
        }

        @Override
        public T next() {
            if (this.hasNext()) {
                final Iterator<T> it = (Iterator<T>)  this.bucketIterators[this.currentBucket];
                return it.next();
            }
            return null;
        }

        @Override
        public void set(T entry) throws Exception {
            this.hasNext();
            final Iterator<T> it = (Iterator<T>)  this.bucketIterators[this.currentBucket];
            it.set(entry);
        }
    }
}
