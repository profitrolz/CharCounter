package ua.com.foxminded.charcounter;

import java.util.Map;
import java.util.Optional;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private final Map<K, V> cacheStorage;

    public Cache() {
        cacheStorage = new WeakHashMap<>();
    }

    public Optional<V> read(K key) {
        return Optional.ofNullable(cacheStorage.get(key));
    }

    public void save(K key, V value){
        cacheStorage.put(key, value);
    }
}
