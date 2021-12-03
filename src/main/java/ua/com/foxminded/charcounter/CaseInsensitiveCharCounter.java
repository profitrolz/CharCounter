package ua.com.foxminded.charcounter;

import java.util.Map;

public class CaseInsensitiveCharCounter extends CharCounterDecorator {

    public CaseInsensitiveCharCounter(CharCounter charCounter) {
        super(charCounter);
    }

    @Override
    public Map<Character, Integer> countChars(String string) {
        return super.countChars(string.toLowerCase());
    }


}
