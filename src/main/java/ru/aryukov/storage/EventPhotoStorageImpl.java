package ru.aryukov.storage;

import ru.aryukov.events.PhotoPostedEvent;
import ru.aryukov.keys.KeysForPhotoEvent;

import java.util.NavigableSet;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Created by oaryukov on 08.01.2018.
 * Класс реализующий хранение событий.
 * Для хранения была выбрана структура данных ConcurrentSkipListMap.
 */
public class EventPhotoStorageImpl implements EventStorage<PhotoPostedEvent, KeysForPhotoEvent> {

    private ConcurrentSkipListMap<KeysForPhotoEvent, PhotoPostedEvent> skipListMap = new ConcurrentSkipListMap<>();

    /**
     * Сохранение события
     * @param key ключ
     * @param event событие
     */
    public void put(KeysForPhotoEvent key, PhotoPostedEvent event) {
        skipListMap.put(key, event);
    }

    /**
     * Получение событий за выбранный временной период
     * @param timeStart начало периода
     * @param timeStop конец периода
     * @return
     */
    public NavigableSet<KeysForPhotoEvent> get(KeysForPhotoEvent timeStart, KeysForPhotoEvent timeStop) {
        return skipListMap.subMap(timeStart, true, timeStop, true).keySet();
    }

    public ConcurrentSkipListMap<KeysForPhotoEvent, PhotoPostedEvent> getSkipListMap() {
        return skipListMap;
    }
}
