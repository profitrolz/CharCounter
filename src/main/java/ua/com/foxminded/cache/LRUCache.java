package ua.com.foxminded.cache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class LRUCache<K, V> implements Cache<K, V> {
    private static final int DEFAULT_CACHE_SIZE = 100;

    private final Function<K, V> cacheLoader;
    private final Map<K, V> cacheStorage;

    public LRUCache(Function<K, V> cacheLoader) {
        this(cacheLoader, DEFAULT_CACHE_SIZE);
    }

    public LRUCache(Function<K, V> cacheLoader, int maxSize) {
        if(maxSize <= 0)
            throw new IllegalArgumentException("Cache size should be greater than 0");
        this.cacheLoader = cacheLoader;
        cacheStorage  = new LinkedHashMap<>() {

            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > maxSize;
            }
        };
    }

    @Override
    public void save(K key, V value){
        cacheStorage.put(key, value);
    }

    @Override
    public Optional<V> read(K key){
        return Optional.ofNullable(cacheStorage.get(key));
    }

    @Override
    public V readOrCompute(K key){
        return cacheStorage.computeIfAbsent(key, cacheLoader);
    }


}
