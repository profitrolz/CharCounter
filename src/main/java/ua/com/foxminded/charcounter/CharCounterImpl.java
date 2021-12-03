package ua.com.foxminded.charcounter;

import java.util.*;

public class CharCounterImpl implements CharCounter {
    private Map<Character, Integer> result;


    @Override
    public Map<Character, Integer> countChars(String string) {
        result = new LinkedHashMap<>();
        if(string == null || "".equals(string))
            return result;

        Character[] chars = string.chars().mapToObj(character -> (char) character).toArray(Character[]::new);
        Arrays.stream(chars).forEach(character -> result.merge(character, 1, (oldValue, value) -> oldValue += value));

        return result;

    }
}
