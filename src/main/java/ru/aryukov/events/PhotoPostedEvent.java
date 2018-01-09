package ru.aryukov.events;

import ru.aryukov.models.Photo;

/**
 * Created by oaryukov on 08.01.2018.
 * Класс события отправки фото.
 * comingTime время совершения события.
 * Photo хранит идентификатор фото
 * uid уникальный идентификатор пользователя.
 */
public class PhotoPostedEvent implements Event<Photo>{

    private final Long comingTime;
    private final Photo value;
    private final Long uid;

    public PhotoPostedEvent(long comingTime, Photo value, long uid) {
        this.comingTime = comingTime;
        this.value = value;
        this.uid = uid;
    }

    public Long getComingTime() {
        return comingTime;
    }

    public Photo getValue() {
        return value;
    }

    public Long getUid() {
        return uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhotoPostedEvent)) return false;

        PhotoPostedEvent that = (PhotoPostedEvent) o;

        if (getComingTime() != null ? !getComingTime().equals(that.getComingTime()) : that.getComingTime() != null)
            return false;
        if (getValue() != null ? !getValue().equals(that.getValue()) : that.getValue() != null) return false;
        return getUid() != null ? getUid().equals(that.getUid()) : that.getUid() == null;
    }

    @Override
    public int hashCode() {
        int result = getComingTime() != null ? getComingTime().hashCode() : 0;
        result = 31 * result + (getValue() != null ? getValue().hashCode() : 0);
        result = 31 * result + (getUid() != null ? getUid().hashCode() : 0);
        return result;
    }
}
