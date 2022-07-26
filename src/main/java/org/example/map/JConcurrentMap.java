package org.example.map;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JConcurrentMap<K, V> implements SimpleMap<K, V>{
    private Set<Map.Entry<K, V>> keyValuePairs = new HashSet<>();
    @Override
    public synchronized void put(K key, V value) {
       keyValuePairs
               .stream()
               .filter(keyValuePair -> keyValuePair.getKey().equals(key))
               .findFirst()
               .ifPresentOrElse(entry -> {
                   entry.setValue(value);
               }, () -> keyValuePairs.add(new AbstractMap.SimpleEntry<>(key, value)));
    }

    @Override
    public synchronized V get(K key) {
        Map.Entry<K, V> pair = keyValuePairs
                .stream()
                .filter(keyValuePair -> keyValuePair.getKey().equals(key))
                .findFirst()
                .orElse(null);
        if(pair == null)
        {
            return null;
        }
        return pair.getValue();

    }

    @Override
    public synchronized boolean containsKey(K key) {
        return keyValuePairs
                .stream().anyMatch(keyValuePair -> keyValuePair.getKey().equals(key));
    }

    @Override
    public String toString() {
        return keyValuePairs.toString();
    }
}

