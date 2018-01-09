package ru.aryukov;

import org.junit.Test;
import ru.aryukov.events.PhotoPostedEvent;
import ru.aryukov.models.Photo;
import ru.aryukov.services.PhotoEventHandleService;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by oaryukov on 08.01.2018.
 */

public class TestStorageApi {

    @Test
    public void checkTheSameTimeComing(){
        Long time = System.currentTimeMillis();
        Photo photo = new Photo(1234L);
        PhotoEventHandleService service = new PhotoEventHandleService();
        service.handleEvent(new PhotoPostedEvent(time, photo, 5678L));
        service.handleEvent(new PhotoPostedEvent(time, photo, 9101L));
        service.handleEvent(new PhotoPostedEvent(time, photo, 2345L));
        service.handleEvent(new PhotoPostedEvent(time, photo, 6789L));
        assertThat(service.getCountByPeriod(Period.MINUTE),is(4L));
    }

    @Test
    public void checkHourPeriod(){
        Long time = System.currentTimeMillis();
        Photo photo = new Photo(1234L);
        PhotoEventHandleService service = new PhotoEventHandleService();
        service.handleEvent(new PhotoPostedEvent(time - 500, photo, 5678L));
        service.handleEvent(new PhotoPostedEvent(time - 600, photo, 9101L));
        service.handleEvent(new PhotoPostedEvent(time - 400, photo, 2345L));
        service.handleEvent(new PhotoPostedEvent(time - 60 * 60 * 1000, photo, 6789L));
        service.handleEvent(new PhotoPostedEvent(time - 2 * 60 * 60 * 1000, photo, 1234L));
        assertThat(service.getCountByPeriod(Period.HOUR),is(4L));
    }

    @Test
    public void checkMinutePeriod(){
        Long time = System.currentTimeMillis();
        Photo photo = new Photo(1234L);
        PhotoEventHandleService service = new PhotoEventHandleService();
        service.handleEvent(new PhotoPostedEvent(time - 500, photo, 5678L));
        service.handleEvent(new PhotoPostedEvent(time - 600, photo, 9101L));
        service.handleEvent(new PhotoPostedEvent(time - 400, photo, 2345L));
        service.handleEvent(new PhotoPostedEvent(time - 60 * 1000 * 15, photo, 6789L));
        service.handleEvent(new PhotoPostedEvent(time - 60 * 1000 * 29, photo, 1234L));
        assertThat(service.getCountByPeriod(Period.MINUTE),is(3L));
    }

    @Test
    public void checkDayPeriod(){
        Long time = System.currentTimeMillis();
        Photo photo = new Photo(1234L);
        PhotoEventHandleService service = new PhotoEventHandleService();
        service.handleEvent(new PhotoPostedEvent(time - 500, photo, 5678L));
        service.handleEvent(new PhotoPostedEvent(time - 600, photo, 9101L));
        service.handleEvent(new PhotoPostedEvent(time - 400, photo, 2345L));
        service.handleEvent(new PhotoPostedEvent(time - 10 * 60 * 60 * 1000, photo, 6789L));
        service.handleEvent(new PhotoPostedEvent(time - 25 * 60 * 60 * 1000, photo, 1234L));
        assertThat(service.getCountByPeriod(Period.DAY),is(4L));
    }
}
