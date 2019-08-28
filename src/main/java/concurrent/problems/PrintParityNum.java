package concurrent.problems;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 设计两个线程，交替打印100以内的奇偶数
 */
public class PrintParityNum {

    static Integer cxsNum = 0;
    static volatile boolean flag = false;
    static CountDownLatch countDownLatch = new CountDownLatch(2);
    public static void main(String[] args) throws InterruptedException {
//        PrintParityNum printParityNum = new PrintParityNum();
//        Thread thread1 = new Thread(printParityNum::printEvenNum);
//        Thread thread2 = new Thread(printParityNum::printOddNum);
//        thread1.start();
//        thread2.start();
//        countDownLatch.await();
//        System.out.println("demo1==>执行结束");

        System.out.println("===使用CAS方式控制开始===");
        new Thread(() -> {
            while (cxsNum < 100) {
                if (!flag && cxsNum%2 == 0) {
                    System.out.println(Thread.currentThread().getName() + ":" + cxsNum);
                    cxsNum++;
                }
                flag = !flag;
            }
        },"偶数").start();

        new Thread(() -> {
            while (cxsNum < 100) {
                if (flag && cxsNum%2 == 1) {

                    System.out.println(Thread.currentThread().getName() + ":" + cxsNum);
                    cxsNum++;
                }
                flag = !flag;
            }
        },"奇数").start();
        System.out.println("===使用CAS方式控制结束===");

    }

    /**
     * 打印偶数
     */
    public synchronized void printEvenNum() {
        for (int num = 0; num < 100; ) {
            System.out.println(Thread.currentThread().getName() + ":" + num);
            num = num + 2;
            this.notifyAll();
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        countDownLatch.countDown();
    }

    /**
     * 打印奇数
     */
    public synchronized void printOddNum() {
        for (int num = 1; num < 100; ) {
            System.out.println(Thread.currentThread().getName() + ":" + num);
            num = num + 2;
            this.notifyAll();
            try {

                if (num < 100) {
                    this.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        countDownLatch.countDown();
    }

}
