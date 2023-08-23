package com.manenkov;

import java.util.Objects;

public class LinkedList<T> implements List<T> {
    private ListNode<T> first;
    private ListNode<T> last;
    private int size;

    public LinkedList() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public T get(final int index) throws Exception {
        if (index < 0 || index >= size) {
            throw new Exception("Index should be in range [0, size)");
        }
        ListNode<T> current = first;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }

    @Override
    public void set(final T element, final int position) throws Exception {
        if (position < 0 || position >= size) {
            throw new Exception("Index should be in range [0, size)");
        }

        ListNode<T> current = first;
        for (int i = 0; i < position; i++) {
            current = current.getNext();
        }
        current.setData(element);
    }

    @Override
    public void add(final T element) throws Exception {
        add(element, size);
    }

    @Override
    public void add(final T element, final int position) throws Exception {
        if (position < 0 || position > size) {
            throw new Exception("Index should be in range [0, size)");
        }
        if (size == 0) {
            Node<T> newNode = new Node<>(element);
            this.first = newNode;
            this.last = newNode;
            this.size = 1;
            return;
        }
        ListNode<T> current = first;
        for (int i = 0; i < position - 1; i++) {
            current = current.getNext();
        }
        final ListNode<T> newNode = new Node<>(element);
        final ListNode<T> next = current != null ? current.getNext() : null;

        newNode.setPrevious(current);
        current.setNext(newNode);

        newNode.setNext(next);
        if (next != null) {
            next.setPrevious(newNode);
        } else {
            this.last = newNode;
        }

        this.size += 1;
    }

    @Override
    public void remove(final int position) throws Exception {
        ListNode<T> current = first;
        for (int i = 0; i < position; i++) {
            current = current.getNext();
        }
        final ListNode<T> previous = current != null ? current.getPrevious() : null;
        final ListNode<T> next = current != null ? current.getNext() : null;
        if (previous != null) {
            previous.setNext(next);
        } else {
            this.first = next;
        }
        if (next != null) {
            next.setPrevious(previous);
        } else {
            this.last = previous;
        }
        this.size -= 1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T first() {
        return this.first != null ? this.first.getData() : null;
    }

    @Override
    public T last() {
        return this.last != null ? this.last.getData() : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinkedList)) return false;
        LinkedList<?> that = (LinkedList<?>) o;
        return this.hashCode() == that.hashCode();
    }

    @Override
    public int hashCode() {
        ListNode<T> current = first;
        int hash = Objects.hash(current);
        for (int i = 0; i < size - 1; i++) {
            final ListNode<T> next = current.getNext();
            hash = Objects.hash(hash, next);
            current = next;
        }
        return Objects.hash(hash, first, last, size);
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        final StringBuilder b = new StringBuilder();
        b.append('[');
        ListNode<T> current = first;
        for (int i = 0; i < size; i++) {
            b.append(current);
            if (i == size - 1) {
                b.append(']');
                break;
            }
            b.append(", ");
            current = current.getNext();
        }
        return b.toString();
    }

    @Override
    public Iterator<T> getIterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {

        private ListNode<T> current;

        public LinkedListIterator() {
            this(first);
        }

        public LinkedListIterator(final ListNode<T> current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (this.hasNext()) {
                final ListNode<T> cur = this.current;
                this.current = current.getNext();
                return cur.getData();
            }
            return null;
        }

        @Override
        public T current() {
            if (this.hasNext()) {
                return this.current.getData();
            }
            return null;
        }

        @Override
        public void set(final T value) {
            current.setData(value);
        }
    }

    static private class Node<T> implements ListNode<T> {

        private ListNode<T> previous;
        private ListNode<T> next;
        private T data;

        public Node(final T data) {
            this.previous = null;
            this.next = null;
            this.data = data;
        }

        public T getData() {
            return this.data;
        }

        public void setData(final T data) {
            this.data = data;
        }

        public ListNode<T> getPrevious() {
            return this.previous;
        }

        public void setPrevious(ListNode<T> previous) {
            this.previous = previous;
        }

        public ListNode<T> getNext() {
            return this.next;
        }

        public void setNext(ListNode<T> next) {
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(data, node.data);
        }

        @Override
        public int hashCode() {
            return Objects.hash(data);
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }
}


