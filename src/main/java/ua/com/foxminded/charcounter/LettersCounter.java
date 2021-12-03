package ua.com.foxminded.charcounter;

import java.util.Arrays;
import java.util.Map;

public class LettersCounter extends CharCounterDecorator {
    public LettersCounter(CharCounter charCounter) {
        super(charCounter);
    }

    @Override
    public Map<Character, Integer> countChars(String string) {
        StringBuilder str = new StringBuilder();
        string.chars().mapToObj(ch -> (char) ch).filter(Character::isLetter).forEach(str::append);
        return super.countChars(str.toString());
    }
}
