package org.example.service;

import org.apache.commons.lang3.concurrent.TimedSemaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class RateLimiter {
    private final int PERMITS_PER_SECOND;

    private TimedSemaphore semaphore;


    public static RateLimiter create(int permitsPerSecond) {
        return new RateLimiter(permitsPerSecond);
    }

    private RateLimiter(int permitsPerSecond) {

        PERMITS_PER_SECOND = permitsPerSecond;
        semaphore = new TimedSemaphore(1,TimeUnit.SECONDS,permitsPerSecond);
    }
    /**
     * If 'count' number of permits are available, claim them.
     * Else, wait.
     */
    public void acquire(int count) throws InterruptedException {

        for(int i=1;i<=count;i++){
            semaphore.acquire();
        }
    }

    /**
     * If 1 permit is available, claim it.
     * Else, wait.
     */
    public void acquire() throws InterruptedException {

     semaphore.acquire();


    }



}
