package ru.aryukov.events;

/**
 * Created by oaryukov on 08.01.2018.
 */
public interface Event<T> {

    Long getComingTime();

    T getValue();

    Long getUid();
}
