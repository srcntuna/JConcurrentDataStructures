package org.example.map;

import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

public class JSemaphoreMap<K, V> implements SimpleMap<K, V> {
    private Set<Map.Entry<K, V>> entries = new HashSet<>();
    private Semaphore mutex = new Semaphore(1); // 1 permit

    @Override
    public void put(K key, V value) {
        try {
            mutex.acquire();
            entries.stream().filter(entry -> entry.getKey().equals(key)).findFirst().ifPresentOrElse(entry -> entry.setValue(value), () -> entries.add(new AbstractMap.SimpleEntry<>(key, value)));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            mutex.release();
        }
    }

    @Override
    public V get(K key) {
        try {
            mutex.acquire();
            return entries.stream().filter(entry -> entry.getKey().equals(key)).findFirst().orElseThrow(() -> new NoSuchElementException("Can't find " + key)).getValue();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            mutex.release();
        }
    }

    @Override
    public boolean containsKey(K key) {
        try {
            mutex.acquire();
            return entries.stream().anyMatch(entry -> entry.getKey().equals(key));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            mutex.release();
        }
    }

    @Override
    public String toString() {
        try {
            mutex.acquire();
            return entries.toString();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            mutex.release();
        }
    }
}
