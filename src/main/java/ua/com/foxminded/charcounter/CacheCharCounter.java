package ua.com.foxminded.charcounter;

import ua.com.foxminded.cache.LRUCache;

import java.util.Map;

public class CacheCharCounter extends CharCounterDecorator {
    private static final int DEFAULT_CACHE_SIZE = 100;

    private final LRUCache<String, Map<Character, Integer>> cache;

    public CacheCharCounter(CharCounter charCounter) {
        this(charCounter, DEFAULT_CACHE_SIZE);
    }

    public CacheCharCounter(CharCounter charCounter, int cacheSize) {
        super(charCounter);
        this.cache = new LRUCache<>(super::countChars, cacheSize);
    }

    @Override
    public Map<Character, Integer> countChars(String string) {
        return cache.readOrCompute(string);
    }
}
