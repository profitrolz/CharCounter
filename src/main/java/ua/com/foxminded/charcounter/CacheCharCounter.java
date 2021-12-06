package ua.com.foxminded.charcounter;

import java.util.Map;

public class CacheCharCounter extends CharCounterDecorator {
    private final Cache<String, Map<Character, Integer>> cache;
    private static final int DEFAULT_CACHE_SIZE = 100;

    public CacheCharCounter(CharCounter charCounter) {
        this(charCounter, DEFAULT_CACHE_SIZE);
    }

    public CacheCharCounter(CharCounter charCounter, int cacheSize) {
        super(charCounter);
        this.cache = new Cache<>(cacheSize);
    }

    @Override
    public Map<Character, Integer> countChars(String string) {
        return cache.readOrElseAndSave(string, () -> countAndSave(string));
    }

    private Map<Character, Integer> countAndSave(String string) {
        Map<Character, Integer> result = super.countChars(string);
        return result;
    }
}
