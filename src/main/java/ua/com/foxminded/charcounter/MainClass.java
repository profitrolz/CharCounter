package ua.com.foxminded.charcounter;

public class MainClass {
    public static void main(String[] args) {
        CharCounter charCounter = new CacheCharCounter(new CaseInsensitiveCharCounter(new CharCounterImpl()), 100);
        System.out.println(charCounter.countChars("heLlo world!"));

        System.out.println(charCounter.countChars("hell world!"));

        System.out.println(charCounter.countChars("hello world!"));
    }
}
