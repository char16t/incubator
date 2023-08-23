package com.manenkov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LinkedListTest {
    @Test
    void testLinkedList() throws Exception {
        final List<Character> a = new LinkedList<>();
        a.add('h'); a.add('e'); a.add('l'); a.add('l'); a.add('o');

        Assertions.assertEquals('h', a.get(0));
        Assertions.assertEquals('e', a.get(1));
        Assertions.assertEquals('l', a.get(2));
        Assertions.assertEquals('l', a.get(3));
        Assertions.assertEquals('o', a.get(4));
        Assertions.assertEquals(5, a.size());
        Assertions.assertEquals('h', a.first());
        Assertions.assertEquals('o', a.last());

        a.set('H', 0);
        Assertions.assertEquals('H', a.get(0));
        Assertions.assertEquals('e', a.get(1));
        Assertions.assertEquals('l', a.get(2));
        Assertions.assertEquals('l', a.get(3));
        Assertions.assertEquals('o', a.get(4));

        Assertions.assertThrows(Exception.class, () -> {
            a.get(-1);
        });
        Assertions.assertThrows(Exception.class, () -> {
            a.get(5);
        });

        Assertions.assertThrows(Exception.class, () -> {
            a.add('!', 12);
        });

        Assertions.assertThrows(Exception.class, () -> {
            a.set('!', 12);
        });

        a.remove(1);
        Assertions.assertEquals('H', a.get(0));
        Assertions.assertEquals('l', a.get(1));
        Assertions.assertEquals('l', a.get(2));
        Assertions.assertEquals('o', a.get(3));
        Assertions.assertEquals(4, a.size());
        Assertions.assertEquals('H', a.first());
        Assertions.assertEquals('o', a.last());

        a.remove(0);
        Assertions.assertEquals('l', a.get(0));
        Assertions.assertEquals('l', a.get(1));
        Assertions.assertEquals('o', a.get(2));
        Assertions.assertEquals(3, a.size());
        Assertions.assertEquals('l', a.first());
        Assertions.assertEquals('o', a.last());

        a.remove(2);
        a.set('L', 1);
        Assertions.assertEquals('l', a.get(0));
        Assertions.assertEquals('L', a.get(1));
        Assertions.assertEquals(2, a.size());
        Assertions.assertEquals('l', a.first());
        Assertions.assertEquals('L', a.last());

        a.add('-', 1);
        Assertions.assertEquals('l', a.get(0));
        Assertions.assertEquals('-', a.get(1));
        Assertions.assertEquals('L', a.get(2));
        Assertions.assertEquals(3, a.size());
        Assertions.assertEquals('l', a.first());
        Assertions.assertEquals('L', a.last());
    }

    @Test
    void testToString() throws Exception {
        final List<Character> e = new LinkedList<>();
        Assertions.assertEquals("[]", e.toString());

        final List<Character> o = new LinkedList<>();
        o.add('o');
        Assertions.assertEquals("[o]", o.toString());

        final List<Character> a = new LinkedList<>();
        a.add('h'); a.add('e'); a.add('l'); a.add('l'); a.add('o');
        Assertions.assertEquals("[h, e, l, l, o]", a.toString());
    }

    @Test
    void testHashCode() throws Exception {
        final List<Character> a = new LinkedList<>();
        a.add('h'); a.add('e'); a.add('l'); a.add('l'); a.add('o');
        final int hashCode1 = a.hashCode();

        a.add('!');
        final int hashCode2 = a.hashCode();

        a.remove(5);
        final int hashCode3 = a.hashCode();

        Assertions.assertEquals(hashCode1, hashCode3);
        Assertions.assertNotEquals(hashCode1, hashCode2);
        Assertions.assertNotEquals(hashCode2, hashCode3);
    }

    @Test
    void testEquals() throws Exception {
        final List<Character> a = new LinkedList<>();
        a.add('h'); a.add('e'); a.add('l'); a.add('l'); a.add('o');

        final List<Character> b = new LinkedList<>();
        b.add('h'); b.add('e'); b.add('l'); b.add('l'); b.add('o');

        Assertions.assertEquals(a, b);

        b.add('!');
        Assertions.assertNotEquals(a, b);

        b.remove(5);
        Assertions.assertEquals(a, b);

        final List<Character> e1 = new LinkedList<>();
        final List<Character> e2 = new LinkedList<>();
        Assertions.assertEquals(a, b);
    }

    @Test
    void testIterator() throws Exception {
        final List<Character> a = new LinkedList<>();
        a.add('h'); a.add('e'); a.add('l'); a.add('l'); a.add('o');

        Iterator<Character> iterator = a.getIterator();
        Assertions.assertEquals('h', iterator.next());
        Assertions.assertEquals('e', iterator.next());
        Assertions.assertEquals('l', iterator.next());
        Assertions.assertEquals('l', iterator.next());
        Assertions.assertEquals('o', iterator.next());
        Assertions.assertNull(iterator.next());

        final List<Character> b = new LinkedList<>();
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
