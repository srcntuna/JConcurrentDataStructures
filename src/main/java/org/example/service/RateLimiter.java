package org.example.service;

import org.apache.commons.lang3.concurrent.TimedSemaphore;

import java.util.concurrent.TimeUnit;

public class RateLimiter {
    private final int PERMITS_PER_SECOND;

    private TimedSemaphore semaphore;

    public static RateLimiter create(int permitsPerSecond) {
        return new RateLimiter(permitsPerSecond);
    }

    private RateLimiter(int permitsPerSecond) {

        PERMITS_PER_SECOND = permitsPerSecond;
        semaphore = new TimedSemaphore(1000, TimeUnit.MILLISECONDS,permitsPerSecond);
    }
    /**
     * If 'count' number of permits are available, claim them.
     * Else, wait.
     */
    public void acquire(int count) throws InterruptedException {
        if(semaphore.getAvailablePermits() >= count){
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
