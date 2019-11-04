package concurrent.lock.locksupport;

import common.SleepTools;

import java.util.concurrent.locks.LockSupport;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 9/5/2019
 */
public class TestLockSupport {

    public static void main(String[] args) {
        //启动两个线程，线程中调用阻塞方法，主线程休眠几秒之后再调用唤醒方法

        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "进入等待。");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "被唤醒。");
        });

        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "进入等待。");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "被唤醒。");
        });


        Thread thread3 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "执行完成。");
        });

        //启动线程
        thread1.start();
        thread2.start();
        SleepTools.ms(2000);

        //唤醒
        System.out.println("准备唤醒线程0");
        LockSupport.unpark(thread1);
        SleepTools.ms(1000);

        System.out.println("准备唤醒线程1");
        LockSupport.unpark(thread2);

        thread3.start();
        SleepTools.ms(1000);
        System.out.println("线程2被阻塞");
        LockSupport.park(thread3);
        //此时无法释放线程2，线程2将一直处于阻塞状态，因为此方法只能在线程中被阻塞
        System.out.println("线程2释放");
        LockSupport.unpark(thread3);


    }
}
