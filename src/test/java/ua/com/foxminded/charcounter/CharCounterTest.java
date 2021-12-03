package ua.com.foxminded.charcounter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

class CharCounterTest {

    @Test
    void countChars_ShouldReturnEmptyMap_stringIsNull(){
        CharCounter charCounter = new CharCounterImpl();
        Map<Character, Integer> expected = new LinkedHashMap<>();
        Assertions.assertTrue(expected.equals(charCounter.countChars(null)));
    }

    @Test
    void countChars_ShouldReturnEmptyMap_EmptyString(){
        CharCounter charCounter = new CharCounterImpl();
        Map<Character, Integer> expected = new LinkedHashMap<>();
        Assertions.assertTrue(expected.equals(charCounter.countChars(null)));
    }

    @Test
    void countChars_ShouldReturnCorrectMap_OnlySpaces(){
        CharCounter charCounter = new CharCounterImpl();
        String string = "      ";
        Map<Character, Integer> expected = new LinkedHashMap<>();
        expected.put(' ', 6);

        Assertions.assertTrue(expected.equals(charCounter.countChars(string)));
    }

    @Test
    void countChars_ShouldReturnCorrectMap_SimpleString(){
        CharCounter charCounter = new CharCounterImpl();
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

        Assertions.assertTrue(expected.equals(charCounter.countChars(string)));
    }

    @Test
    void countChars_ShouldReturnCorrectMap_SymbolRepeat(){
        CharCounter charCounter = new CharCounterImpl();
        String string = "hhhh";
        Map<Character, Integer> expected = new LinkedHashMap<>();
        expected.put('h', 4);
        Assertions.assertTrue(expected.equals(charCounter.countChars(string)));
    }

    @Test
    void countChars_ShouldReturnCorrectMap_SameSymbolInDifferentCase(){
        CharCounter charCounter = new CharCounterImpl();
        String string = "hhHH";
        Map<Character, Integer> expected = new LinkedHashMap<>();
        expected.put('h', 2);
        expected.put('H', 2);
        Assertions.assertTrue(expected.equals(charCounter.countChars(string)));
    }

    @Test
    void countChars_ShouldReturnRightOrderedMap_SimpleString(){
        CharCounter charCounter = new CharCounterImpl();
        String string = "hello world!";

        Assertions.assertInstanceOf(LinkedHashMap.class, charCounter.countChars(string));
    }

    @Test
    void countChars_ShouldReturnMapFromCache_TwoSameString(){
        CharCounter charCounter = new CacheCharCounter(new CharCounterImpl());
        String string = "hello world!";
        Map<Character, Integer> firstActual = charCounter.countChars(string);
        Map<Character, Integer> secondActual = charCounter.countChars(string);
        Assertions.assertSame(firstActual, secondActual);
    }

    @Test
    void countChars_ShouldNotReturnMapFromCache_TwoSameStringWithCacheSize(){
        CharCounter charCounter = new CacheCharCounter(new CharCounterImpl(), 3);
        String string = "hello world!";
        Map<Character, Integer> firstActual = charCounter.countChars(string);
        charCounter.countChars("abc");
        charCounter.countChars("def");
        charCounter.countChars("ghi");
        Map<Character, Integer> secondActual = charCounter.countChars(string);

        Assertions.assertNotSame(firstActual, secondActual);
    }

    @Test
    void countChars_ShouldReturnCorrectMap_LettersCounter(){
        CharCounter charCounter = new LettersCounter(new CharCounterImpl());
        String string = "he123llo     wor345ld!";
        Map<Character, Integer> expected = new LinkedHashMap<>();
        expected.put('h', 1);
        expected.put('e', 1);
        expected.put('l', 3);
        expected.put('o', 2);
        expected.put('w', 1);
        expected.put('r', 1);
        expected.put('d', 1);
        Map<Character, Integer> actual = charCounter.countChars(string);

        Assertions.assertTrue(expected.equals(actual));
    }

    @Test
    void countChars_ShouldReturnCorrectMap_CaseInsensitiveCounter(){
        CharCounter charCounter = new CaseInsensitiveCharCounter(new CharCounterImpl());
        String string = "HeLLo WoRlD!";
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
        Map<Character, Integer> actual = charCounter.countChars(string);

        Assertions.assertTrue(expected.equals(actual));
    }

    @Test
    void countChars_ShouldReturnCorrectMapFromCache_CachedCaseInsensitiveCounter(){
        CharCounter charCounter = new CaseInsensitiveCharCounter(new CacheCharCounter(new CharCounterImpl()));
        String string = "HeLLo WoRlD!";
        Map<Character, Integer> actual1 = charCounter.countChars(string);
        Map<Character, Integer> actual2 = charCounter.countChars(string);
        Assertions.assertSame(actual1, actual2);
    }

    @Test
    void countChars_ShouldReturnCorrectMap_CachedCaseInsensitiveCounter(){
        CharCounter charCounter = new CaseInsensitiveCharCounter(new CacheCharCounter(new CharCounterImpl()));
        String string = "HeLLo WoRlD!";
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
        Assertions.assertTrue(charCounter.countChars(string).equals(expected));
    }

}
