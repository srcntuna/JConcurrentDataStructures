package org.example.map;

public class JConcurrentMap<K, V> implements SimpleMap<K, V>{
    @Override
    public  void put(K key, V value) {
    }

    @Override
    public synchronized V get(K key) {
        return null;

    }

    @Override
    public synchronized boolean containsKey(K key) {
        return false;
    }

    @Override
    public String toString() {
        return "";
    }
}

