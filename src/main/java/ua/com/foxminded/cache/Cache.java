package ua.com.foxminded.cache;

import java.util.Optional;

public interface Cache<K, V> {

    Optional<V> read (K key);

    void save(K key, V value);

    V readOrCompute(K key);

}
