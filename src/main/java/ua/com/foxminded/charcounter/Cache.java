package ua.com.foxminded.charcounter;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cache<K, V> extends LinkedHashMap<K, V> {
    private final int maxSize;
    public Cache() {
        super();
        maxSize = 100;
    }

    public Cache(int maxSize) {
        super();
        if(maxSize <= 0)
            throw new IllegalArgumentException();
        this.maxSize = maxSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > maxSize;
    }
}
