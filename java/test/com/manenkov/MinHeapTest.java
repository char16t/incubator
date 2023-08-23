package com.manenkov;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class MinHeapTest {
    @Test
    void testMinHeap() {
        final Heap<Integer> heap = new MinHeap<>(10);
        heap.insert(100);
        heap.insert(25);
        heap.insert(15);
        heap.insert(50);
        heap.insert(10);
        heap.insert(75);
        heap.insert(5);
        Assertions.assertEquals(7, heap.size());

        Assertions.assertEquals(5, heap.remove());
        Assertions.assertEquals(6, heap.size());

        Assertions.assertEquals(10, heap.remove());
        Assertions.assertEquals(5, heap.size());

        Assertions.assertEquals(15, heap.remove());
        Assertions.assertEquals(4, heap.size());

        Assertions.assertEquals(25, heap.remove());
        Assertions.assertEquals(3, heap.size());

        Assertions.assertEquals(50, heap.remove());
        Assertions.assertEquals(2, heap.size());

        Assertions.assertEquals(75, heap.remove());
        Assertions.assertEquals(1, heap.size());

        Assertions.assertEquals(100, heap.remove());
        Assertions.assertEquals(0, heap.size());

        Assertions.assertEquals(0, heap.size());
        Assertions.assertNull(heap.remove());
    }
}
