package com.manenkov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HashSetTest {
    @Test
    void testHashSet() throws Exception {
        final Set<Character> set = new HashSet<>(2);
        set.add('a');
        set.add('b');
        set.add('c');
        set.add('d');
        set.add('f');
        set.add('g');
        set.add('h');

        set.add('a');

        Assertions.assertEquals(7, set.size());
        Assertions.assertTrue(set.contains('a'));
        Assertions.assertFalse(set.contains('e'));

        set.remove('a');
        set.remove('g');
        Assertions.assertEquals(5, set.size());
        Assertions.assertFalse(set.contains('a'));
        Assertions.assertFalse(set.contains('g'));
        Assertions.assertFalse(set.contains('e'));
    }

    @Test
    void testHashSet2() throws Exception {
        final Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('b');
        set.add('c');
        set.add('d');
        set.add('a');

        Assertions.assertEquals(4, set.size());
        Assertions.assertTrue(set.contains('a'));
        Assertions.assertFalse(set.contains('e'));
    }

    @Test
    void testIterator() throws Exception {
        final Set<Character> emptySet = new HashSet<>();
        final Iterator<Character> emptyHashtableIt = emptySet.getIterator();
        Assertions.assertFalse(emptyHashtableIt.hasNext());
        Assertions.assertNull(emptyHashtableIt.next());
        Assertions.assertNull(emptyHashtableIt.current());

        final Set<Character> set = new HashSet<>();
        set.add('h');
        set.add('e');
        set.add('l');
        set.add('l');
        set.add('o');
        final Iterator<Character> it = set.getIterator();
        Assertions.assertEquals('e', it.current());
        it.set('E');

        Character c1 = it.next();
        Assertions.assertTrue(it.hasNext());
        Assertions.assertEquals('E', c1);

        Assertions.assertEquals('h', it.current());

        Assertions.assertTrue(it.hasNext());
        Character c2 = it.next();
        Assertions.assertEquals('h', c2);

        Assertions.assertTrue(it.hasNext());
        Character c3 = it.next();
        Assertions.assertEquals('l', c3);

        Assertions.assertTrue(it.hasNext());
        Character c4 = it.next();
        Assertions.assertEquals('o', c4);

        Assertions.assertFalse(it.hasNext());
        Assertions.assertNull(it.next());

        // Test equals
        Assertions.assertNotEquals(c1, c2);
        Assertions.assertEquals(c3, c3);
        Assertions.assertEquals(69, c1.hashCode());
    }

    @Test
    void testToString() throws Exception {
        final Set<Character> emptySet = new HashSet<>();
        Assertions.assertEquals("{}", emptySet.toString());

        final Set<Character> singleelementSet = new HashSet<>();
        singleelementSet.add('a');
        Assertions.assertEquals("{a}", singleelementSet.toString());

        final Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('b');
        set.add('c');
        set.add('d');
        Assertions.assertEquals("{a, b, c, d}", set.toString());
    }

    @Test
    void testEqualsAndHashCode() throws Exception {
        // Empty
        final Set<Character> ea = new HashSet<>();
        final Set<Character> eb = new HashSet<>();
        Assertions.assertEquals(ea, eb);
        Assertions.assertEquals(1985, ea.hashCode());
        Assertions.assertEquals(1985, eb.hashCode());

        // Different capacity
        final Set<Character> c1 = new HashSet<>(2);
        final Set<Character> c2 = new HashSet<>(3);
        Assertions.assertNotEquals(c1, c2);
        Assertions.assertEquals(963, c1.hashCode());
        Assertions.assertEquals(964, c2.hashCode());

        ea.add('1');
        ea.add('2');
        ea.add('3');

        eb.add('1');
        eb.add('2');
        Assertions.assertNotEquals(ea, eb);
        Assertions.assertNotEquals(ea.hashCode(), eb.hashCode());

        ea.remove('3');
        Assertions.assertEquals(eb, ea);
        Assertions.assertEquals(ea.hashCode(), eb.hashCode());
    }
}
