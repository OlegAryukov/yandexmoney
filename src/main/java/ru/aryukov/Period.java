package ru.aryukov;

/**
 * Created by oaryukov on 08.01.2018.
 * Enum периода получения количества событий
 */
public enum Period {
    MINUTE(60000),
    HOUR(3600000),
    DAY(86400000);

    Period(int period) {
        this.period = period;
    }

    private int period;

    public int getPeriod() {
        return period;
    }
}
