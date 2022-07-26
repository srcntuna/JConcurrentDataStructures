package org.example.service;

public class RateLimiter {
    private final int PERMITS_PER_SECOND;

    public static RateLimiter create(int permitsPerSecond) {
        return new RateLimiter(permitsPerSecond);
    }

    private RateLimiter(int permitsPerSecond) {
        PERMITS_PER_SECOND = permitsPerSecond;
    }
    /**
     * If 'count' number of permits are available, claim them.
     * Else, wait.
     */
    public void acquire(int count) {
        // TODO
    }

    /**
     * If 1 permit is available, claim it.
     * Else, wait.
     */
    public void acquire() {
        // TODO
    }



}
