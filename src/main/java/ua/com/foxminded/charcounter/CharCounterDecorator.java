package ua.com.foxminded.charcounter;

import java.util.Map;

public class CharCounterDecorator implements CharCounter{
    protected CharCounter charCounter;

    public CharCounterDecorator(CharCounter charCounter) {
        this.charCounter = charCounter;
    }

    public Map<Character, Integer> countChars(String string) {
        return charCounter.countChars(string);
    }
}
