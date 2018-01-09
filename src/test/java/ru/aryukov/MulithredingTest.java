package ru.aryukov;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import ru.aryukov.events.PhotoPostedEvent;
import ru.aryukov.models.Photo;
import ru.aryukov.services.PhotoEventHandleService;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by oaryukov on 09.01.2018.
 */
public class MulithredingTest {

    private PhotoEventHandleService service;

    private final static int NUMBER_OF_CYCLES = 10000;

    private Thread getNewThread() {
        return new Thread(() -> {
            for (int i = 0; i < NUMBER_OF_CYCLES; i++) {
                service.handleEvent(new PhotoPostedEvent(System.currentTimeMillis(), new Photo(i), UUID.randomUUID().hashCode()));
            }
        });
    }


    @Before
    public void init() {
        service = new PhotoEventHandleService();
    }

    @Test
    public void testThreeThreads() throws InterruptedException {
        List<Thread> threads = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            threads.add(getNewThread());
        }

       threads.forEach(Thread::start);
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        MatcherAssert.assertThat(NUMBER_OF_CYCLES * 3L, is(service.getCountByPeriod(Period.HOUR)));
        MatcherAssert.assertThat(NUMBER_OF_CYCLES * 3L, is(service.getCountByPeriod(Period.MINUTE)));
    }

    @Test
    public void testTenThreads() throws InterruptedException {

       List<Thread> threads = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(getNewThread());
        }

        threads.forEach(Thread::start);
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        MatcherAssert.assertThat(NUMBER_OF_CYCLES * 10L, is(service.getCountByPeriod(Period.HOUR)));
        MatcherAssert.assertThat(NUMBER_OF_CYCLES * 10L, is(service.getCountByPeriod(Period.MINUTE)));
    }

}
