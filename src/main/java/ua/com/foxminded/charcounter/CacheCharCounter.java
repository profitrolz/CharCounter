package ua.com.foxminded.charcounter;

import java.util.Map;

public class CacheCharCounter extends CharCounterDecorator {
    private static final int DEFAULT_CACHE_SIZE = 100;

    private final Cache<String, Map<Character, Integer>> cache;

    public CacheCharCounter(CharCounter charCounter) {
        this(charCounter, DEFAULT_CACHE_SIZE);
    }

    public CacheCharCounter(CharCounter charCounter, int cacheSize) {
        super(charCounter);
        this.cache = new Cache<>(super::countChars, cacheSize);
    }

    @Override
    public Map<Character, Integer> countChars(String string) {
        return cache.readOrSave(string);
    }
}
