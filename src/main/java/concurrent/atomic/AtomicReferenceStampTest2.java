package concurrent.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 9/9/2019
 */
public class AtomicReferenceStampTest2 {
    private static AtomicInteger atomicInt = new AtomicInteger(100);
    private static AtomicStampedReference atomicStampedRef = new AtomicStampedReference(100, 0);

    public static void main(String[] args) throws InterruptedException {
        Thread intT1 = new Thread(new Runnable() {
            @Override
            public void run() {
                atomicInt.compareAndSet(100, 101);
                atomicInt.compareAndSet(101, 100);
            }
        });

        Thread intT2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                }
                boolean c3 = atomicInt.compareAndSet(100, 101);
                System.out.println(c3); // true
            }
        });

        intT1.start();
        intT2.start();
        intT1.join();
        intT2.join();

        Thread refT1 = new Thread(new Runnable() {
            @Override
            public void run(){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                }
                atomicStampedRef.compareAndSet(100, 101, atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
                atomicStampedRef.compareAndSet(101, 100, atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
                System.out.println(atomicStampedRef.getStamp());

        }
    });

    Thread refT2 = new Thread(new Runnable() {
        @Override
        public void run() {
            //当两个线程同时启动，他们获得的版本号大多数情况都是一样在初始化时候的值（0）
            int stamp = atomicStampedRef.getStamp();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
            }
            //此时由于等待2s之后，线程t1已经将版本号改成了2，所以以下操作失败
            boolean c3 = atomicStampedRef.compareAndSet(100, 101, stamp, stamp + 1);
            System.out.println(c3); // false
        }
    });

       refT1.start();
       refT2.start();
}
}
