package ru.aryukov.storage;

import java.util.NavigableSet;

/**
 * Created by oaryukov on 08.01.2018.
 */
public interface EventStorage<T, K> {

    void put(K k, T o);
    NavigableSet<K> get(K start, K stop);
}
