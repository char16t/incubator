package com.manenkov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HashtableTest {
    @Test
    void testHashtable() throws Exception {
        final Hashtable<Character, String> ht = new Hashtable<>(2);
        ht.put('a', "A");
        ht.put('b', "B");
        ht.put('c', "C");
        ht.put('d', "D");
        ht.put('f', "F");
        ht.put('g', "G");
        ht.put('h', "H");

        ht.put('a', "AA");

        Assertions.assertEquals(7, ht.size());
        Assertions.assertEquals("AA", ht.get('a'));
        Assertions.assertEquals("B", ht.get('b'));
        Assertions.assertEquals("C", ht.get('c'));
        Assertions.assertEquals("D", ht.get('d'));
        Assertions.assertTrue(ht.contains('a'));
        Assertions.assertFalse(ht.contains('e'));
        Assertions.assertNull(ht.get('e'));

        ht.remove('a');
        ht.remove('g');
        Assertions.assertEquals(5, ht.size());
        Assertions.assertEquals("B", ht.get('b'));
        Assertions.assertEquals("C", ht.get('c'));
        Assertions.assertEquals("D", ht.get('d'));
        Assertions.assertFalse(ht.contains('a'));
        Assertions.assertFalse(ht.contains('g'));
        Assertions.assertNull(ht.get('a'));
        Assertions.assertFalse(ht.contains('e'));
    }

    @Test
    void testHashtable2() throws Exception {
        final Hashtable<Character, String> ht = new Hashtable<>();
        ht.put('a', "A");
        ht.put('b', "B");
        ht.put('c', "C");
        ht.put('d', "D");
        ht.put('a', "AA");

        Assertions.assertEquals(4, ht.size());
        Assertions.assertEquals("AA", ht.get('a'));
        Assertions.assertEquals("B", ht.get('b'));
        Assertions.assertEquals("C", ht.get('c'));
        Assertions.assertEquals("D", ht.get('d'));
        Assertions.assertTrue(ht.contains('a'));
        Assertions.assertFalse(ht.contains('e'));
        Assertions.assertNull(ht.get('e'));
    }

    @Test
    void testIterator() throws Exception {
        final Hashtable<Character, String> emptyHashtable = new Hashtable<>();
        final Iterator<MapEntry<Character, String>> emptyHashtableIt = emptyHashtable.getIterator();
        Assertions.assertFalse(emptyHashtableIt.hasNext());
        Assertions.assertNull(emptyHashtableIt.next());
        Assertions.assertNull(emptyHashtableIt.current());

        final Hashtable<Character, String> ht = new Hashtable<>();
        ht.put('h', "Hello");
        ht.put('e', "hEllo");
        ht.put('l', "heLlo");
        ht.put('l', "helLo");
        ht.put('o', "hellO");
        final Iterator<MapEntry<Character, String>> it = ht.getIterator();
        Assertions.assertEquals('e', it.current().key());
        Assertions.assertEquals("hEllo", it.current().value());
        it.set(new Hashtable.Entry<>('e', "HELLO"));
        Assertions.assertThrows(Exception.class, () -> {
            it.set(new Hashtable.Entry<>('E', "HELLO"));
        });

        MapEntry<Character, String> c1 = it.next();
        Assertions.assertTrue(it.hasNext());
        Assertions.assertEquals('e', c1.key());
        Assertions.assertEquals("HELLO", c1.value());

        Assertions.assertEquals('h', it.current().key());
        Assertions.assertEquals("Hello", it.current().value());

        Assertions.assertTrue(it.hasNext());
        MapEntry<Character, String> c2 = it.next();
        Assertions.assertEquals('h', c2.key());
        Assertions.assertEquals("Hello", c2.value());

        Assertions.assertTrue(it.hasNext());
        MapEntry<Character, String> c3 = it.next();
        Assertions.assertEquals('l', c3.key());
        Assertions.assertEquals("helLo", c3.value());

        Assertions.assertTrue(it.hasNext());
        MapEntry<Character, String> c4 = it.next();
        Assertions.assertEquals('o', c4.key());
        Assertions.assertEquals("hellO", c4.value());

        Assertions.assertFalse(it.hasNext());
        Assertions.assertNull(it.next());

        // Test equals
        Assertions.assertNotEquals(c1, c2);
        Assertions.assertEquals(c3, c3);
        Assertions.assertEquals(68628654, c1.hashCode());

        // Test toString
        Assertions.assertEquals("[key=e, value=HELLO]", c1.toString());
    }

    @Test
    void testToString() throws Exception {
        final Hashtable<Character, String> emptyHashtable = new Hashtable<>();
        Assertions.assertEquals("{}", emptyHashtable.toString());

        final Hashtable<Character, String> singleHashtable = new Hashtable<>();
        singleHashtable.put('a', "A");
        Assertions.assertEquals("{a->A}", singleHashtable.toString());

        final Hashtable<Character, String> ht = new Hashtable<>();
        ht.put('a', "A");
        ht.put('b', "B");
        ht.put('c', "C");
        ht.put('d', "D");
        Assertions.assertEquals("{a->A, b->B, c->C, d->D}", ht.toString());
    }

    @Test
    void testEqualsAndHashCode() throws Exception {
        // Empty
        final Hashtable<Character, String> ea = new Hashtable<>();
        final Hashtable<Character, String> eb = new Hashtable<>();
        Assertions.assertEquals(ea, eb);
        Assertions.assertEquals(1985, ea.hashCode());
        Assertions.assertEquals(1985, eb.hashCode());

        // Different capacity
        final Hashtable<Character, String> c1 = new Hashtable<>(2);
        final Hashtable<Character, String> c2 = new Hashtable<>(3);
        Assertions.assertNotEquals(c1, c2);
        Assertions.assertEquals(963, c1.hashCode());
        Assertions.assertEquals(964, c2.hashCode());

        ea.put('1', "one");
        ea.put('2', "two");
        ea.put('3', "three");

        eb.put('1', "one");
        eb.put('2', "two");
        Assertions.assertNotEquals(ea, eb);
        Assertions.assertNotEquals(ea.hashCode(), eb.hashCode());

        ea.remove('3');
        Assertions.assertEquals(eb, ea);
        Assertions.assertEquals(ea.hashCode(), eb.hashCode());

    }
}
