package ua.com.foxminded.charcounter;

import ua.com.foxminded.cache.LRUCache;

import java.util.Map;

public class CacheCharCounter extends CharCounterDecorator {

    private final LRUCache<String, Map<Character, Integer>> cache;


    public CacheCharCounter(CharCounter charCounter, int cacheSize) {
        super(charCounter);
        this.cache = new LRUCache<>(super::countChars, cacheSize);
    }

    @Override
    public Map<Character, Integer> countChars(String string) {
        return cache.readOrCompute(string);
    }
}
