package org.example;

import org.example.singleton.Dictionary;

import java.util.*;

public class Main {
    /*
     * Learning Goals:
     * 1. Understand why unsynchronized code is dangerous in a multi-threaded environment
     *     Try to avoid threads. If you must use threads, try to avoid having them work on the same objects at the
     * same time.
     * 2. Become familiar with common ways to make critical sections safer
     *      synchronized, Semaphore, ReentrantLock.... ConcurrentHashMap
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world!");
        // PART 1
//        Thread t1 = new Thread(() -> {
//            Dictionary d1 = Dictionary.getInstance();
//            System.out.println(d1);
//        });
//        Thread t2 = new Thread(() -> {
//            Dictionary d2 = Dictionary.getInstance();
//            System.out.println(d2);
//        });
//        t1.start();
//        t2.start();
        // Are we going to get the same Dictionary instance?
        // Is it not going to be a Singleton?


        // PART 2
        // Let's try to add a bunch to a map from 4 different threads
//        SimpleMap<String, Integer> stringToLength = new JConcurrentMap<>();
////        Map<String, Integer> concurrentStringToLength = new ConcurrentHashMap<>();
//        Thread stl1 = new Thread(() -> {
//            stringToLength.put("a", 1);
//            stringToLength.put("apple", 5);
//        }, "stl1");
//        Thread stl2 = new Thread(() -> {
//            stringToLength.put("a", 5);
//            stringToLength.put("accent", 6);
//
//        }, "stl2");
//        Thread stl3 = new Thread(() -> {
//            stringToLength.put("a", 3);
//            stringToLength.put("app", 3);
//
//        }, "stl3");
//        Thread stl4 = new Thread(() -> {
//            stringToLength.put("a", 2);
//            stringToLength.put("angry", 5);
//
//        }, "stl4");

        // Scenario: you are making a desktop app that lets the user see the weather forecast
        // So the user is typing peacefully in the desktop
        // Oh no! The desktop app decided it's time to pull updated weather information
        // Do you want the desktop app to lock up for 5 seconds while it pulls the weather information from a server?

        // Imagine you have a web app that takes in HTTP requests, (ex: Google server that receives search requests)


//        stl1.start();
//        stl2.start();
//        stl3.start();
//        stl4.start();
//        // at this point, all 4 threads may be running at the same time
//        stl1.join();
//        stl2.join();
//        stl3.join();
//        stl4.join();
//        // wait for those 4 threads to die before printing map
//        System.out.println(stringToLength);

        // PART 3
        // Let's try to get two lists of words
        // And let's add them to a map with two keys: true and false.
        // true's value will be a list of valid words that start with A.
        // false's value will be a list of Strings that aren't valid words that start with A
        Map<Boolean, List<String>> results = new HashMap<>();
        results.put(true, new ArrayList<>());
        results.put(false, new ArrayList<>());
//        SimpleMap<Boolean, List<String>> results = new JConcurrentMap<>();
       String inputA = "a,apple,app,angry,argghhhhhhh";
        String inputB = "and,angel,actual,altruistic";
        Dictionary dictionary = Dictionary.getInstance();
        Thread t3 = new Thread(() -> {
            String[] inputArr = inputA.split(",");
            for (String string : inputArr) {
                System.out.println(Thread.currentThread() + " " + string + " being added to " + results);
                boolean isWord = dictionary.isValidWord(string);

                doWork(string, results, isWord);
            }
        }, "t3");

        Thread t4 = new Thread(() -> {
            String[] inputArr = inputB.split(",");
            for (String string : inputArr) {
                boolean isWord = dictionary.isValidWord(string);
                System.out.println(Thread.currentThread() + " " + string + " being added to " + results);
                doWork(string, results, isWord);
                if(string.equals("altruistic")) {
                    removeSomething(results);
                }

            }
        }, "t4");
        t3.start();
        t4.start();
        t3.join();
        t4.join();
        System.out.println(results);
    }

    private static synchronized void doWork(String string, Map<Boolean, List<String>> results, boolean isWord) {
        if (results.containsKey(isWord)) {
            // we don't know what will happen between lines 95 and 97
            results.get(isWord).add(string);
        } else {
            results.put(isWord, new ArrayList<>(Arrays.asList(string)));
        }
    }

    private static synchronized void removeSomething(Map<Boolean, List<String>> results) {
        results.clear();
    }
}