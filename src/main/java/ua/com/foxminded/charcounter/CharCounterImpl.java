package ua.com.foxminded.charcounter;

import java.util.*;

public class CharCounterImpl implements CharCounter {


    @Override
    public Map<Character, Integer> countChars(String string) {
        Map<Character, Integer> result = new LinkedHashMap<>();

        if(string == null || "".equals(string))
            return result;

        string.chars().mapToObj(character -> (char) character).forEach(character -> result.merge(character, 1, (oldValue, value) -> oldValue += value));
        return result;

    }
}
