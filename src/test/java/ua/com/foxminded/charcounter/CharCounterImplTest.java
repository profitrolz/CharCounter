package ua.com.foxminded.charcounter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

class CharCounterImplTest {

    private final CharCounter charCounter = new CharCounterImpl();

    @Test
    void countChars_ShouldReturnEmptyMap_stringIsNull(){
        Map<Character, Integer> expected = new LinkedHashMap<>();
        Assertions.assertEquals(expected, charCounter.countChars(null));
    }

    @Test
    void countChars_ShouldReturnEmptyMap_EmptyString(){
        Map<Character, Integer> expected = new LinkedHashMap<>();
        Assertions.assertEquals(expected, charCounter.countChars(null));
    }

    @Test
    void countChars_ShouldReturnCorrectMap_OnlySpaces(){
        String string = "      ";
        Map<Character, Integer> expected = new LinkedHashMap<>();
        expected.put(' ', 6);

        Assertions.assertEquals(expected, charCounter.countChars(string));
    }

    @Test
    void countChars_ShouldReturnCorrectMap_SimpleString(){
        String string = "hello world!";
        Map<Character, Integer> expected = new LinkedHashMap<>();
        expected.put('h', 1);
        expected.put('e', 1);
        expected.put('l', 3);
        expected.put('o', 2);
        expected.put(' ', 1);
        expected.put('w', 1);
        expected.put('r', 1);
        expected.put('d', 1);
        expected.put('!', 1);

        Assertions.assertEquals(expected, charCounter.countChars(string));
    }

    @Test
    void countChars_ShouldReturnCorrectMap_SymbolRepeat(){
        String string = "hhhh";
        Map<Character, Integer> expected = new LinkedHashMap<>();
        expected.put('h', 4);
        Assertions.assertEquals(expected, charCounter.countChars(string));
    }

    @Test
    void countChars_ShouldReturnCorrectMap_SameSymbolInDifferentCase(){
        String string = "hhHH";
        Map<Character, Integer> expected = new LinkedHashMap<>();
        expected.put('h', 2);
        expected.put('H', 2);
        Assertions.assertEquals(expected, charCounter.countChars(string));
    }

    @Test
    void countChars_ShouldReturnRightOrderedMap_SimpleString(){
        String string = "hello world!";

        Assertions.assertInstanceOf(LinkedHashMap.class, charCounter.countChars(string));
    }

}
