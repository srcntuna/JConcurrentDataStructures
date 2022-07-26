package org.example.map;

import java.util.*;

public class JSyncronizedMap<K, V> implements SimpleMap<K, V> {
    private Set<Map.Entry<K, V>> entries = new HashSet<>();

    @Override
    public synchronized void put(K key, V value) {
        entries.stream().filter(entry -> entry.getKey().equals(key)).findFirst().ifPresentOrElse(entry -> entry.setValue(value), () -> entries.add(new AbstractMap.SimpleEntry<>(key, value)));
    }

    @Override
    public synchronized V get(K key) {
        return entries.stream().filter(entry -> entry.getKey().equals(key)).findFirst().orElseThrow(() -> new NoSuchElementException("Can't find " + key)).getValue();
    }

    @Override
    public synchronized boolean containsKey(K key) {
        return entries.stream().anyMatch(entry -> entry.getKey().equals(key));
    }

    @Override
    public synchronized String toString() {
        return entries.toString();
    }
}