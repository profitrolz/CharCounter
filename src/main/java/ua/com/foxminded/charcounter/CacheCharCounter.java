package ua.com.foxminded.charcounter;

import java.util.Map;
import java.util.Optional;

public class CacheCharCounter extends CharCounterDecorator {
    private final Cache<String, Map<Character, Integer>> cache;
    public CacheCharCounter(CharCounter charCounter) {
        super(charCounter);
        this.cache = new Cache<>();
    }

    @Override
    public Map<Character, Integer> countChars(String string) {
        Optional<Map<Character, Integer>> savedResult = cache.read(string);
        if(savedResult.isPresent() ){
            return savedResult.get();
        }
        Map<Character, Integer> result = super.countChars(string);
        cache.save(string, result);
        return result;
    }


}
