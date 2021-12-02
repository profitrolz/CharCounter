package ua.com.foxminded.charcounter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class CacheCharCounterTest {
    private static CharCounter charCounter = new CacheCharCounter();

    private boolean mapsAreEqual(Map<Character, Integer> first, Map<Character, Integer> second) {
        if(first.size() != second.size())
            return false;

        return first.entrySet().stream().allMatch(set -> Objects.equals(set.getValue(), second.get(set.getKey())));
    }

    @Test
    void countChars_ShouldReturnEmptyMap_stringIsNull(){
        Map<Character, Integer> expected = new LinkedHashMap<>();
        Assertions.assertTrue(mapsAreEqual(expected, charCounter.countChars(null)));
    }

    @Test
    void countChars_ShouldReturnEmptyMap_EmptyString(){
        Map<Character, Integer> expected = new LinkedHashMap<>();
        Assertions.assertTrue(mapsAreEqual(expected, charCounter.countChars(null)));
    }

    @Test
    void countChars_ShouldReturnMap_OnlySpaces(){
        String string = "      ";
        Map<Character, Integer> expected = new LinkedHashMap<>();
        expected.put(' ', 6);

        Assertions.assertTrue(mapsAreEqual(expected, charCounter.countChars(string)));
    }

    @Test
    void countChars_ShouldReturnMap_SimpleString(){
        String string = "hello world!";
        Map<Character, Integer> expected = new LinkedHashMap<>();
        expected.put('h', 1);
        expected.put('e', 1);
        expected.put('l', 3);
        expected.put('o', 2);
        expected.put(' ', 1);
        expected.put('w', 1);
        expected.put('r', 1);
        expected.put('d', 1);
        expected.put('!', 1);

        Assertions.assertTrue(mapsAreEqual(expected, charCounter.countChars(string)));
    }

    @Test
    void countChars_ShouldReturnMap_SymbolRepeat(){
        String string = "hhhh";
        Map<Character, Integer> expected = new LinkedHashMap<>();
        expected.put('h', 4);

        Assertions.assertTrue(mapsAreEqual(expected, charCounter.countChars(string)));
    }

    @Test
    void countChars_ShouldReturnMap_SameSymbolInDifferentCase(){
        String string = "hhHH";
        Map<Character, Integer> expected = new LinkedHashMap<>();
        expected.put('h', 2);
        expected.put('H', 2);
        Assertions.assertTrue(mapsAreEqual(expected, charCounter.countChars(string)));
    }

    @Test
    void countChars_ShouldReturnRightOrderedMap_SimpleString(){
        String string = "hello world!";
        Map<Character, Integer> expected = new LinkedHashMap<>();
        expected.put('h', 1);
        expected.put('e', 1);
        expected.put('l', 3);
        expected.put('o', 2);
        expected.put(' ', 1);
        expected.put('w', 1);
        expected.put('r', 1);
        expected.put('d', 1);
        expected.put('!', 1);
        Object[] expectedEntryArray = expected.entrySet().toArray();
        Map<Character, Integer> actual = charCounter.countChars(string);

        Assertions.assertArrayEquals(expectedEntryArray, actual.entrySet().toArray());
    }

    @Test
    void countChars_ShouldReturnMapFromCache_TwoSameString(){
        String string = "hello world!";
        Map<Character, Integer> firstActual = charCounter.countChars(string);
        Map<Character, Integer> secondActual = charCounter.countChars(string);
        Assertions.assertSame(firstActual, secondActual);
    }
}
