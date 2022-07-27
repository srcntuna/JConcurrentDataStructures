package org.example.singleton;

import java.time.LocalTime;
import java.util.Scanner;

public class DictionaryRunner {
    private static Dictionary dictionary = Dictionary.getInstance();

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Word to check?");
                String word = scanner.nextLine();
                check(word);
            }
        }
    }

    private static void check(String word) {
        new Thread(() ->
                System.out.println(LocalTime.now() + "-Status of '" + word + "': " + dictionary.isValidWord(word))
        ).start();
    }
}
