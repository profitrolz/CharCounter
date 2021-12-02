package ua.com.foxminded.charcounter;

import java.util.*;

public class CacheCharCounter implements CharCounter {
    private final Map<String, Map<Character, Integer>> cache;
    private Map<Character, Integer> result;

    public CacheCharCounter() {
        cache = new HashMap<>();
    }

    @Override
    public Map<Character, Integer> countChars(String string) {
        result = new LinkedHashMap<>();
        if(string == null || Objects.equals("", string))
            return result;

        if (cache.containsKey(string)) {
            return cache.get(string);
        }
        Character[] chars = string.chars().mapToObj(character -> (char) character).toArray(Character[]::new);
        Arrays.stream(chars).forEach(this::addCharToResultMap);
        cache.put(string, result);
        return result;
    }

    private void addCharToResultMap(char character) {
        if (result.containsKey(character)) {
            Integer counter = result.get(character);
            counter++;
            result.put(character, counter);
        } else {
            result.put(character, 1);
        }
    }
}
