package ua.com.foxminded.charcounter;

import java.util.Arrays;
import java.util.Map;

public class LettersCounter extends CharCounterDecorator {
    public LettersCounter(CharCounter charCounter) {
        super(charCounter);
    }

    @Override
    public Map<Character, Integer> countChars(String string) {
        return super.countChars(string.replaceAll("[^a-zA-Z]", ""));
    }
}
