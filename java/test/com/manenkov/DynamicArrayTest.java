package com.manenkov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DynamicArrayTest {

    @Test
    void testGet() throws Exception {
        final DynamicArray<Character> a = new DynamicArray<>(3);
        a.add('h'); a.add('e'); a.add('l'); a.add('l'); a.add('o');
        Assertions.assertEquals('h', a.get(0));
        Assertions.assertEquals('e', a.get(1));
        Assertions.assertEquals('l', a.get(2));
        Assertions.assertEquals('l', a.get(3));
        Assertions.assertEquals('o', a.get(4));
    }

    @Test
    void testFirstLast() throws Exception {
        final List<Character> a = new DynamicArray<>(3);
        Assertions.assertNull(a.first());
        Assertions.assertNull(a.last());

        a.add('Q');
        Assertions.assertEquals('Q', a.first());
        Assertions.assertEquals('Q', a.last());

        a.add('W');
        Assertions.assertEquals('Q', a.first());
        Assertions.assertEquals('W', a.last());
    }

    @Test
    void testGetOutbound1() throws Exception {
        final List<Character> a = new DynamicArray<>(3);
        a.add('h'); a.add('e'); a.add('l'); a.add('l'); a.add('o');

        Assertions.assertThrows(Exception.class, () -> {
            a.get(-1);
        });
        Assertions.assertThrows(Exception.class, () -> {
            a.get(-10);
        });
        Assertions.assertThrows(Exception.class, () -> {
            a.get(5);
        });
    }

    @Test
    void testAdd() throws Exception {
        final List<Character> a = new DynamicArray<>(3);
        a.add('h'); a.add('e'); a.add('l'); a.add('l'); a.add('o');
        Assertions.assertEquals(5, a.size());

        a.add('H', 0);
        a.add('E', 2);
        Assertions.assertEquals('H', a.get(0));
        Assertions.assertEquals('h', a.get(1));
        Assertions.assertEquals('E', a.get(2));
        Assertions.assertEquals('e', a.get(3));
        Assertions.assertEquals('l', a.get(4));
        Assertions.assertEquals('l', a.get(5));
        Assertions.assertEquals('o', a.get(6));
    }

    @Test
    void testSet() throws Exception {
        final List<Character> a = new DynamicArray<>(3);
        a.add('h'); a.add('e'); a.add('l'); a.add('l'); a.add('o');
        a.set('H', 0);
        Assertions.assertEquals('H', a.get(0));
        Assertions.assertEquals('e', a.get(1));
        Assertions.assertEquals('l', a.get(2));
        Assertions.assertEquals('l', a.get(3));
        Assertions.assertEquals('o', a.get(4));
    }

    @Test
    void testSetIncorrect() throws Exception {
        final List<Character> a = new DynamicArray<>(3);
        a.add('h'); a.add('e'); a.add('l'); a.add('l'); a.add('o');
        Assertions.assertThrows(Exception.class, () -> {
            a.set('H', -1);
        });
        Assertions.assertThrows(Exception.class, () -> {
            a.set('H', 5);
        });
    }

    @Test
    void testAddInvalidPosition() throws Exception {
        final List<Character> a = new DynamicArray<>(3);
        a.add('h'); a.add('e'); a.add('l'); a.add('l'); a.add('o');

        Assertions.assertThrows(Exception.class, () -> {
            a.add('H', -1);
        });

        Assertions.assertThrows(Exception.class, () -> {
            a.add('E', 6);
        });
    }

    @Test
    void testGetSize() {
        final List<Character> a = new DynamicArray<>();
        Assertions.assertEquals(0, a.size());

        final List<Character> b = new DynamicArray<>(100);
        Assertions.assertEquals(0, b.size());
    }

    @Test
    void testRemove() throws Exception {
        final List<Character> a = new DynamicArray<>(3);
        a.add('h'); a.add('e'); a.add('l'); a.add('l'); a.add('o');
        a.remove(2);
        a.remove(0);

        Assertions.assertEquals('e', a.get(0));
        Assertions.assertEquals('l', a.get(1));
        Assertions.assertEquals('o', a.get(2));
        Assertions.assertThrows(Exception.class, () -> {
           a.remove(3);
        });
        Assertions.assertThrows(Exception.class, () -> {
            a.remove(-10);
        });
    }

    @Test
    void testEqualsSame() {
        final List<Character> a = new DynamicArray<>(10);
        final List<Character> b = new DynamicArray<>(10);
        Assertions.assertEquals(a, b);
        Assertions.assertEquals(b, a);
    }

    @Test
    void testEqualsSameWithDifferentSize() throws Exception {
        final List<Character> a = new DynamicArray<>(10);
        a.add('h'); a.add('e'); a.add('l'); a.add('l'); a.add('o');
        final List<Character> b = new DynamicArray<>(10);
        b.add('h'); b.add('i');
        Assertions.assertNotEquals(a, b);
        Assertions.assertNotEquals(b, a);
    }

    @Test
    void testEqualsNotEquals() throws Exception {
        final List<Character> a = new DynamicArray<>(10);
        a.add('h'); a.add('e'); a.add('l'); a.add('l'); a.add('o');
        final List<Character> b = new DynamicArray<>(10);
        b.add('h'); b.add('e'); b.add('L'); b.add('L'); b.add('O');
        Assertions.assertNotEquals(a, b);
        Assertions.assertNotEquals(b, a);
    }

    @Test
    void testEqualsSameWithDifferentCapacity() {
        final List<Character> a = new DynamicArray<>(10);
        final List<Character> b = new DynamicArray<>(20);
        Assertions.assertEquals(a, b);
        Assertions.assertEquals(b, a);
    }

    @Test
    void testEqualsSameFilled() throws Exception {
        final List<Character> a = new DynamicArray<>(10);
        a.add('h'); a.add('i');
        final List<Character> b = new DynamicArray<>(10);
        b.add('h'); b.add('i');
        Assertions.assertEquals(a, b);
        Assertions.assertEquals(b, a);
    }

    @Test
    void testEqualsSameFilledWithDifferentCapacity() throws Exception {
        final List<Character> a = new DynamicArray<>(10);
        a.add('h'); a.add('i');
        final List<Character> b = new DynamicArray<>(20);
        b.add('h'); b.add('i');
        Assertions.assertEquals(a, b);
        Assertions.assertEquals(b, a);
    }

    @Test
    void testHashCode() throws Exception {
        final List<Character> a = new DynamicArray<>(10);
        a.add('h'); a.add('i');
        final List<Character> b = new DynamicArray<>(20);
        b.add('h'); b.add('i');
        Assertions.assertEquals(a.hashCode(), b.hashCode());
        Assertions.assertEquals(b.hashCode(), a.hashCode());
    }

    @Test
    void testToStringEmpty() {
        final List<Character> a = new DynamicArray<>(10);
        final String expected = "[]";
        final String actual = a.toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToStringSingle() throws Exception {
        final List<Character> a = new DynamicArray<>(10);
        a.add('a');
        final String expected = "[a]";
        final String actual = a.toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToStringSeveral() throws Exception {
        final List<Character> a = new DynamicArray<>(10);
        a.add('a');
        a.add('b');
        a.add('c');
        final String expected = "[a, b, c]";
        final String actual = a.toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testIterator() throws Exception {
        final List<Character> a = new DynamicArray<>(3);
        final Iterator<Character> it = a.getIterator();
        Assertions.assertNull(it.next());

        a.add('h'); a.add('e'); a.add('l'); a.add('l'); a.add('o');
        final Iterator<Character> iterator = a.getIterator();
        Assertions.assertEquals('h', iterator.next());
        Assertions.assertEquals('e', iterator.next());
        Assertions.assertEquals('l', iterator.next());
        Assertions.assertEquals('l', iterator.next());
        Assertions.assertEquals('o', iterator.next());
        Assertions.assertNull(iterator.next());

        final List<Character> b = new DynamicArray<>(3);
        b.add('h'); b.add('e'); b.add('l'); b.add('l'); b.add('o');
        final Iterator<Character> bIterator = b.getIterator();
        while (bIterator.hasNext()) {
            final Character current = bIterator.current();
            bIterator.set(Character.toUpperCase(current));
            bIterator.next();
        }
        final Iterator<Character> it2 = b.getIterator();
        Assertions.assertEquals('H', it2.next());
        Assertions.assertEquals('E', it2.next());
        Assertions.assertEquals('L', it2.next());
        Assertions.assertEquals('L', it2.next());
        Assertions.assertEquals('O', it2.next());
        Assertions.assertNull(it2.next());
    }
}
