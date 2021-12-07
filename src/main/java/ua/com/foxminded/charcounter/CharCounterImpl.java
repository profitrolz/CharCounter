package ua.com.foxminded.charcounter;

import java.util.*;
import java.util.stream.Collectors;

public class CharCounterImpl implements CharCounter {


    @Override
    public Map<Character, Integer> countChars(String string) {
        Map<Character, Integer> result = new LinkedHashMap<>();

        if (string == null || "".equals(string))
            return result;

        return string.chars()
                .mapToObj(character -> (char) character)
                .collect(Collectors.toMap(character -> character, character -> 1, (oldValue, newValue) -> oldValue += newValue, LinkedHashMap::new));
    }
}
