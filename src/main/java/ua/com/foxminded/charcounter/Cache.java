package ua.com.foxminded.charcounter;

import java.util.LinkedHashMap;
import java.util.function.Supplier;

public class Cache<K, V> {
    private static final int DEFAULT_CACHE_SIZE = 100;
    private final int maxSize;
    private final LinkedHashMap<K, V> cacheStorage = new LinkedHashMap<>();
    public Cache() {
        this(DEFAULT_CACHE_SIZE);
    }

    public Cache(int maxSize) {
        if(maxSize <= 0)
            throw new IllegalArgumentException("Cache size should be greater than 0");
        this.maxSize = maxSize;
    }

    public void save(K key, V value){
        cacheStorage.put(key, value);
        if(cacheStorage.size() > maxSize)
            removeFirstElement();
    }

    public V read(K key){
        return cacheStorage.get(key);
    }

    public V readOrElseAndSave(K key, Supplier<V> supplier){
       if (cacheStorage.containsKey(key))
           return cacheStorage.get(key);
       V result = supplier.get();
       save(key, result);
       return result;
    }


    private void removeFirstElement(){
        cacheStorage.keySet().stream().findFirst().ifPresent(cacheStorage::remove);
    }




}
