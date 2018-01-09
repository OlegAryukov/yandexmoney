package ru.aryukov.keys;

/**
 * Created by oaryukov on 08.01.2018.
 * Класс для ключа регистрации события
 */
public class KeysForPhotoEvent implements Comparable<KeysForPhotoEvent>{
    private long comingTime;
    private long uid;
    private boolean searchFlag;

    public KeysForPhotoEvent(long comingTime, long uid, boolean searchFlag) {
        this.comingTime = comingTime;
        this.uid = uid;
        this.searchFlag = searchFlag;
    }

    public long getComingTime() {
        return comingTime;
    }

    @Override
    public int compareTo(KeysForPhotoEvent o) {
        int result = Long.compare(this.comingTime,o.getComingTime());

        if (result == 0 && (this.searchFlag || o.searchFlag)){
            result = -1;
        }

        return result == 0 ? Long.compare(this.uid, o.uid) : result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KeysForPhotoEvent)) return false;

        KeysForPhotoEvent that = (KeysForPhotoEvent) o;

        return getComingTime() == that.getComingTime();
    }

    @Override
    public int hashCode() {
        return (int) (getComingTime() ^ (getComingTime() >>> 32));
    }
}
