package ua.com.foxminded.cache;

import java.util.Optional;

public interface Cache<K, V> {
    int DEFAULT_CACHE_SIZE = 100;

    Optional<V> read (K key);

    void save(K key, V value);



}
