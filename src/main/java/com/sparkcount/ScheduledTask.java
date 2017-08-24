package com.sparkcount;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class ScheduledTask {

    public static void main(String[] args){

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1, (runnable) ->  {
            AtomicLong count = new AtomicLong(0);
            Thread t = new Thread(new ThreadGroup("single"), runnable, "single-" + count.getAndIncrement());

            //t.setDaemon(true);

            return t;
        });

        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("Thread currentName = " + Thread.currentThread().getName());
        }, 0, 1, TimeUnit.MICROSECONDS);

        try {
            scheduledExecutorService.awaitTermination(1000, TimeUnit.MICROSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
