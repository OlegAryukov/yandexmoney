package ru.aryukov.services;

import ru.aryukov.Period;
import ru.aryukov.events.PhotoPostedEvent;
import ru.aryukov.keys.KeysForPhotoEvent;
import ru.aryukov.storage.EventPhotoStorageImpl;

/**
 * Created by oaryukov on 08.01.2018.
 * Сервис для работы с хранилищем событий
 */
public class PhotoEventHandleService {

    private EventPhotoStorageImpl storage = new EventPhotoStorageImpl();

    /**
     * Регистрация собыйтия
     * @param event событие
     */
    public void handleEvent(PhotoPostedEvent event){
        storage.put(new KeysForPhotoEvent(event.getComingTime(), event.getUid(), false), event);
    }

    /**
     * Получение события за период
     * @param period Метка периода
     * @return
     */
    public Long getCountByPeriod(Period period) {
        return Long.valueOf(storage
                .getSkipListMap()
                .subMap(new KeysForPhotoEvent(System.currentTimeMillis() - period.getPeriod(), 0, true), true, new KeysForPhotoEvent(System.currentTimeMillis(), 0, true), true)
                .keySet().size());
    }
}
