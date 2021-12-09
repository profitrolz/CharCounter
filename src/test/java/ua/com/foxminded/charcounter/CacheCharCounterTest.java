package ua.com.foxminded.charcounter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class CacheCharCounterTest {

    @Test
    void countChars_ShouldReturnMapFromCache_TwoSameString() {
        CharCounter charCounter = new CacheCharCounter(new CharCounterImpl(), 100);

        String string = "hello world!";
        Map<Character, Integer> firstActual = charCounter.countChars(string);
        Map<Character, Integer> secondActual = charCounter.countChars(string);
        Assertions.assertSame(firstActual, secondActual);
    }

    @Test
    void countChars_ShouldNotReturnMapFromCache_TwoSameStringWithCacheSize() {
        CharCounter charCounter = new CacheCharCounter(new CharCounterImpl(), 3);

        String string = "hello world!";
        Map<Character, Integer> firstActual = charCounter.countChars(string);
        charCounter.countChars("abc");
        charCounter.countChars("def");
        charCounter.countChars("ghi");
        Map<Character, Integer> secondActual = charCounter.countChars(string);

        Assertions.assertNotSame(firstActual, secondActual);
    }

}
