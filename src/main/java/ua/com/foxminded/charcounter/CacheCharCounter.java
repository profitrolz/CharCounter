package ua.com.foxminded.charcounter;

import java.util.Map;

public class CacheCharCounter extends CharCounterDecorator {
    private final Cache<String, Map<Character, Integer>> cache;
    private int cacheSize = 100;
    public CacheCharCounter(CharCounter charCounter) {
        super(charCounter);
        this.cache = new Cache<>(cacheSize);
    }

    public CacheCharCounter(CharCounter charCounter,  int cacheSize) {
        super(charCounter);
        this.cacheSize = cacheSize;
        this.cache = new Cache<>(cacheSize);
    }

    @Override
    public Map<Character, Integer> countChars(String string) {
        Map<Character, Integer> savedResult = cache.get(string);
        if(savedResult != null){
            return savedResult;
        }
        Map<Character, Integer> result = super.countChars(string);
        cache.put(string, result);
        return result;
    }


}
