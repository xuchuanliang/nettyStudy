package com.snail.nettystudy.capter07;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SechudleTest {
    public static void main(String[] args) throws InterruptedException {
        test1();
    }
    public static void test1() throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.schedule(()->{
            System.out.println("hahahha");
        },10, TimeUnit.SECONDS);
    }
}
