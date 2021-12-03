package ua.com.foxminded.charcounter;

import java.util.Map;
import java.util.Optional;
import java.util.WeakHashMap;

public class Cache {
    private final Map<String, Map<Character, Integer>> cacheStorage;

    public Cache() {
        cacheStorage = new WeakHashMap<>();
    }

    public Optional<Map<Character, Integer>> read(String key) {
        return Optional.ofNullable(cacheStorage.get(key));
    }

    public void save(String key, Map<Character, Integer> value){
        cacheStorage.put(key, value);
    }
}
