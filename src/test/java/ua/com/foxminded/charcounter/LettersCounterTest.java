package ua.com.foxminded.charcounter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class LettersCounterTest {

    CharCounter charCounter = new LettersCounter(new CharCounterImpl());

    @Test
    void countChars_ShouldReturnCorrectMap_LettersCounter(){
        String string = "he123llo     wor345ld!";
        Map<Character, Integer> expected = new LinkedHashMap<>();
        expected.put('h', 1);
        expected.put('e', 1);
        expected.put('l', 3);
        expected.put('o', 2);
        expected.put('w', 1);
        expected.put('r', 1);
        expected.put('d', 1);
        Map<Character, Integer> actual = charCounter.countChars(string);

        Assertions.assertTrue(expected.equals(actual));
    }

}
