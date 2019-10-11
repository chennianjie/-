package test;

import common.SleepTools;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 10/7/2019
 */
public class LockTest {

    public static void main(String[] args) {

        long start1 = System.currentTimeMillis();
        method1();
        long end1 = System.currentTimeMillis();
        System.out.println("method1{}" + (end1 - start1));

        long start2 = System.currentTimeMillis();
        method2();
        long end2 = System.currentTimeMillis();
        System.out.println("method2{}" + (end2 - start2));
    }

    private static void method2() {
        ReentrantLock lock = new ReentrantLock();
        System.out.println("业务开始");
        System.out.println("业务阻塞");
        lock.lock();
        SleepTools.s(2);
        lock.unlock();
        System.out.println("业务结束");
    }

    private static void method1(){
        CountDownLatch countDownLatch = new CountDownLatch(1);
        System.out.println("业务开始");
        System.out.println("业务阻塞");
        countDownLatch.countDown();
        SleepTools.s(2);
        countDownLatch.countDown();
        System.out.println("业务结束");
    }
}
