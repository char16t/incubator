package com.manenkov;

import java.util.Objects;

public class Hashtable<K, V> implements Map<K, V> {

    private int size;
    private int capacity;
    private Object[] buckets;

    public Hashtable() {
        this(1024);
    }

    public Hashtable(final int capacity) {
        this(0, capacity);
    }

    private Hashtable(int size, int capacity) {
        this(size, capacity, new Object[capacity]);
    }

    private Hashtable(final int size, final int capacity, final Object[] buckets) {
        this.size = size;
        this.capacity = capacity;
        this.buckets = buckets;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean contains(final K key) {
        final int bucketId = Objects.hashCode(key) % capacity;
        final List<Entry<K, V>> values = (List<Entry<K, V>>) this.buckets[bucketId];
        if (values == null) {
            return false;
        }
        final Iterator<Entry<K, V>> it = values.getIterator();
        while (it.hasNext()) {
            if (Objects.equals(it.next().key(), key)) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public V get(final K key) {
        final int bucketId = Objects.hashCode(key) % capacity;
        final List<Entry<K, V>> values = (List<Entry<K, V>>) this.buckets[bucketId];
        if (values == null) {
            return null;
        }
        final Iterator<Entry<K, V>> it = values.getIterator();
        while (it.hasNext()) {
            final Entry<K, V> current = it.next();
            if (Objects.equals(current.key(), key)) {
                return current.value();
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void put(final K key, final V value) throws Exception {
        final int bucketId = Objects.hashCode(key) % capacity;
        final List<Entry<K, V>> values = (List<Entry<K, V>>) this.buckets[bucketId];
        if (values == null) {
            final List<Entry<K, V>> emptyLinkedList = new LinkedList<>();
            this.buckets[bucketId] = emptyLinkedList;
            emptyLinkedList.add(new Entry<>(key, value));
            size += 1;
            return;
        }
        final Iterator<Entry<K, V>> it = values.getIterator();
        boolean wasFound = false;
        while (it.hasNext()) {
            final Entry<K, V> current = it.current();
            if (Objects.equals(current.key(), key)) {
                it.set(new Entry<>(key, value));
                wasFound = true;
                break;
            }
            it.next();
        }
        if (!wasFound) {
            values.add(new Entry<>(key, value));
            size += 1;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void remove(final K key) throws Exception {
        final int bucketId = Objects.hashCode(key) % capacity;
        final List<Entry<K, V>> values = (List<Entry<K, V>>) this.buckets[bucketId];
        if (values == null) {
            return;
        }
        final Iterator<Entry<K, V>> it = values.getIterator();
        int position = 0;
        while (it.hasNext()) {
            Entry<K, V> current = it.next();
            if (Objects.equals(current.key, key)) {
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
    public Iterator<MapEntry<K, V>> getIterator() {
        return new HashtableIterator(this);
    }

    @Override
    public String toString() {
        final HashtableIterator it = new HashtableIterator(this);
        final StringBuilder sb = new StringBuilder();
        int count = 0;
        sb.append("{");
        while (it.hasNext()) {
            MapEntry<K, V> current = it.next();
            sb.append(current.key());
            sb.append("->");
            sb.append(current.value());
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
        if (!(o instanceof Hashtable)) return false;
        Hashtable<?, ?> hashtable = (Hashtable<?, ?>) o;
        if (size != hashtable.size || capacity != hashtable.capacity) {
            return false;
        }

        Iterator<?> it = new HashtableIterator(this);
        int thisHash = 1;
        while (it.hasNext()) {
            Object current = it.next();
            thisHash = 31 * thisHash + (current == null ? 0 : current.hashCode());
        }

        Iterator<?> thatIt = hashtable.getIterator();
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
        final HashtableIterator it = new HashtableIterator(this);
        while (it.hasNext()) {
            MapEntry<K, V> current = it.next();
            result = 31 * result + (current == null ? 0 : current.hashCode());
        }
        return result;
    }

    private class HashtableIterator implements Iterator<MapEntry<K, V>> {
        private final Hashtable<K, V> hashtable;
        private int currentBucket;
        private Object[] bucketIterators;

        public HashtableIterator(Hashtable<K, V> hashtable) {
            this.hashtable = hashtable;
            this.currentBucket = 0;
            this.bucketIterators = new Object[hashtable.buckets.length + 1];
        }

        @Override
        public boolean hasNext() {
            if (currentBucket >= hashtable.capacity) return false;
            List<Entry<K, V>> b = (List<Entry<K, V>>) hashtable.buckets[currentBucket];
            while (b == null && currentBucket + 1 < hashtable.capacity) {
                b = (List<Entry<K, V>>) hashtable.buckets[++currentBucket];
            }
            if (b == null) {
                return false;
            }
            Iterator<Entry<K, V>> it = (Iterator<Entry<K, V>>) bucketIterators[currentBucket];
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
        public MapEntry<K, V> current() {
            if (this.hasNext()) {
                final Iterator<Entry<K, V>> it = (Iterator<Entry<K, V>>)  this.bucketIterators[this.currentBucket];
                return it.current();
            }
            return null;
        }

        @Override
        public MapEntry<K, V> next() {
            if (this.hasNext()) {
                final Iterator<Entry<K, V>> it = (Iterator<Entry<K, V>>)  this.bucketIterators[this.currentBucket];
                return it.next();
            }
            return null;
        }

        @Override
        public void set(MapEntry<K, V> entry) throws Exception {
            final K key = entry.key();
            final V value = entry.value();
            if (!hashtable.contains(key)) {
                throw new Exception("Unable to update entry. Key '" + key + "' not found");
            }
            hashtable.put(key, value);
        }
    }

    public static class Entry<K, V> implements MapEntry<K, V> {
        private K key;
        private V value;
        public Entry(final K key, final V value) {
            this.key = key;
            this.value = value;
        }
        public K key() {
            return key;
        }
        public V value() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Entry)) return false;
            Entry<?, ?> entry = (Entry<?, ?>) o;
            return Objects.equals(key, entry.key) && Objects.equals(value, entry.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }

        @Override
        public String toString() {
            return "[key=" + key +
                    ", value=" + value +
                    ']';
        }
    }
}
