package org.example.map;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class JReentrantLockMap <K, V> implements SimpleMap<K, V> {
        private Set<Map.Entry<K, V>> entries = new HashSet<>();
        private ReentrantLock mutex = new ReentrantLock();


        @Override
        public void put(K key, V value) {
            try {
                mutex.lock();
                entries.stream().filter(entry -> entry.getKey().equals(key)).findFirst().ifPresentOrElse(entry -> entry.setValue(value), () -> entries.add(new AbstractMap.SimpleEntry<>(key, value)));
            } finally {
                mutex.unlock();
            }
        }

        @Override
        public V get(K key) {
            try {
                mutex.lock();
                return entries.stream().filter(entry -> entry.getKey().equals(key)).findFirst().orElseThrow(() -> new NoSuchElementException("Can't find " + key)).getValue();
            }finally {
                mutex.unlock();
            }
        }

        @Override
        public boolean containsKey(K key) {
            try {
                mutex.lock();
                return entries.stream().anyMatch(entry -> entry.getKey().equals(key));
            }finally {
                mutex.unlock();
            }
        }

        @Override
        public String toString() {
            try {
                mutex.lock();
                return entries.toString();
            }finally {
                mutex.unlock();
            }
        }
}
