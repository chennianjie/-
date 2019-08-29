package concurrent.thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {


    static class MyThread extends Thread{
        @Override
        public void run() {
            while (true) {
                System.out.println(Thread.currentThread().getName()+"正在执行。。。");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        //创建一个可重用固定线程数的线程池
//        ExecutorService pool = Executors.newFixedThreadPool(2);
//        ExecutorService pool = Executors.newSingleThreadExecutor();
        ExecutorService pool = Executors.newCachedThreadPool();
        //创建实现了 Runnable 接口对象，Thread 对象当然也实现了 Runnable 接口
        pool.execute(new MyThread());
        pool.execute(new MyThread());
        pool.execute(new MyThread());
        pool.shutdown();
    }
}
