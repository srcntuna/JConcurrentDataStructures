package org.example.map;

public interface SimpleMap <K, V> {
    void put(K key, V value);
    V get(K key);
    boolean containsKey(K key);
}
