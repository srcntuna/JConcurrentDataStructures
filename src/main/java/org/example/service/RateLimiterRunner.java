package org.example.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.IntStream;

public class RateLimiterRunner {
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(10); // 10 permits available per second
    public static void main(String[] args) throws InterruptedException {
        /*
         * So here's what's going on in the RateLimiterRunner.
         * I have 5 threads that are all "examining" numbers
         * Why? Because I want them to all be very busy and have tons of work to do.
         *
         * HOWEVER, the examineNumber method in this class is using "RateLimiter"
         * RateLimiter has one job: make sure only x threads can touch a critical section per second.
         * When RATE_LIMITER.acquire() is called in that method, it permits the thread to proceed if there is an open
         *  permit.
         * If there is not an open permit, it forces the thread to wait until there is an open permit.
         *
         * You'll need to write the RATE_LIMITER code.
         * HINT: Consider using Semaphore and consider using OOP and consider using the other stuff you've learned
         * about threads.
         *
         * HINT: Only make changes to RateLimiter.java
         *
         * GOOD LUCK!
         *
         */
        Thread t1 = new Thread(() -> {
            getWordsToCheck().forEach(RateLimiterRunner::examineNumber);
        }, "t1");
        Thread t2 = new Thread(() -> {
            getWordsToCheck().forEach(RateLimiterRunner::examineNumber);

        }, "t2");
        Thread t3 = new Thread(() -> {
            getWordsToCheck().forEach(RateLimiterRunner::examineNumber);

        }, "t3");
        Thread t4 = new Thread(() -> {
            getWordsToCheck().forEach(RateLimiterRunner::examineNumber);

        }, "t4");
        Thread t5 = new Thread(() -> {
            getWordsToCheck().forEach(RateLimiterRunner::examineNumber);

        }, "t5");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();


    }

    private static void examineNumber(String num) {
        RATE_LIMITER.acquire();
        System.out.println(ZonedDateTime.now() + ": " + num);
    }



    private static List<String> getWordsToCheck() {
        return IntStream.range(-100_000, 1_000).mapToObj(i -> String.valueOf(i)).toList();
    }
}
