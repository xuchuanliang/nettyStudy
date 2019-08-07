package com.snail.nettystudy.capter09;

import java.util.concurrent.TimeUnit;

/**
 * @author xuchuanliangbt
 * @title: Test1
 * @projectName netty-study
 * @description:
 * @date 2019/5/1018:52
 * @Version
 */
public class Test1 {
    public static void main(String[] args) throws InterruptedException {
        test1();
    }
    private static boolean stop = false;
    private static void test1() throws InterruptedException {
        Thread thread = new Thread(()->{
            int i=0;
            while (!stop){
                i++;
                System.out.println(i);
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        stop = true;
    }
}
