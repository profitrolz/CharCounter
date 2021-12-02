package ua.com.foxminded.charcounter;

public class MainClass {
    public static void main(String[] args) {
        CharCounter charCounter = new CacheCharCounter();
        System.out.println(charCounter.countChars("hello world!"));

        System.out.println(charCounter.countChars("hell world!"));

        System.out.println(charCounter.countChars("hello world!"));
    }
}
