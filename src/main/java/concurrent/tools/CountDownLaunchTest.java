package concurrent.tools;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁测试类
 * 设计5个初始化线程，6个扣除点
 */
public class CountDownLaunchTest {

    //声明CountDownLaunch对象
    static private CountDownLatch countDownLatch = new CountDownLatch(5);

    /**
     * 初始化线程
     */
    static class InitThread implements Runnable{
        @Override
        public void run() {
            System.out.println("【初始化线程】"+Thread.currentThread().getName() + ":正在初始化程序。。。");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        }
    }

    /**
     * 业务线程，需要等待初始化线程执行完成才可以运行
     */
    static class BusinessThread implements Runnable{

        @Override
        public void run() {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("【业务线程】"+Thread.currentThread().getName() + ":正在执行业务代码。。。");
        }
    }


    public static void main(String[] args) throws InterruptedException {
        //生成一个单独的初始化线程
        new Thread(()->{
            System.out.println("【主线程中的初始化线程】"+Thread.currentThread().getName() + ":正在初始化程序。。。");
            countDownLatch.countDown();
        }).start();

        new Thread(new BusinessThread()).start();
        new Thread(new InitThread()).start();
        new Thread(new InitThread()).start();
        new Thread(new InitThread()).start();
        new Thread(new InitThread()).start();
        new Thread(new InitThread()).start();
        //在主线程中也设置一个wait点
        countDownLatch.await();
        System.out.println("主线程结束");
    }

}
