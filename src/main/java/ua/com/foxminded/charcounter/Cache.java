package ua.com.foxminded.charcounter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class Cache<K, V> {
    private static final int DEFAULT_CACHE_SIZE = 100;

    private final int maxSize;
    private final Function<K, V> cacheLoader;
    private final LinkedHashMap<K, V> cacheStorage = new LinkedHashMap<>() {

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > maxSize;
        }
    };

    public Cache(Function<K, V> cacheLoader) {
        this(cacheLoader, DEFAULT_CACHE_SIZE);
    }

    public Cache(Function<K, V> cacheLoader, int maxSize) {
        if(maxSize <= 0)
            throw new IllegalArgumentException("Cache size should be greater than 0");
        this.maxSize = maxSize;
        this.cacheLoader = cacheLoader;
    }

    public void save(K key, V value){
        cacheStorage.put(key, value);
    }

    public Optional<V> read(K key){
        return Optional.ofNullable(cacheStorage.get(key));
    }

    public V readOrSave(K key){
        return cacheStorage.computeIfAbsent(key, cacheLoader);
    }

}
