package concurrent.dbpool;

import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 连接池测试类
 * 多线程测试
 */
public class DBPoolTest {

    static private CountDownLatch countDownLatch;

    public DBPoolTest(CountDownLatch countDownLatch) {
        DBPoolTest.countDownLatch = countDownLatch;
    }

    static class Worker implements Runnable{

        //线程执行次数
        private int count;
        //线程安全的技术器（记录获取到的连接和未获取到的连接个数）
        private AtomicInteger gets;
        private AtomicInteger noGets;

        public Worker(int count, AtomicInteger gets, AtomicInteger noGets) {
            this.count = count;
            this.gets = gets;
            this.noGets = noGets;
        }

        @Override
        public void run() {
            while (count > 0) {
                try {
                    SqlConnectImpl connection = (SqlConnectImpl)DBPool.fetchConnection(1000);
                    if (connection==null) {
                        noGets.getAndIncrement();
                        System.out.println(Thread.currentThread().getName() + "：{}获取连接失败");
                    }else {
//                        System.out.println(Thread.currentThread().getName() + "获取连接成功【"+count+"】");
                        try {
                            gets.getAndIncrement();
                            connection.createStatement();
                            connection.commit();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }finally {
                            DBPool.release(connection);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    count--;
                }
            }
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DBPool dbPool = new DBPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(50);
        DBPoolTest.countDownLatch = countDownLatch;
        AtomicInteger gets = new AtomicInteger(0);
        AtomicInteger noGets = new AtomicInteger(0);
        for (int i = 0;i < 50; i++){
            new Thread(new Worker(20,gets,noGets), "thread_"+i).start();
        }
        countDownLatch.await();
        System.out.println("一共获取的连接数："+1000);
        System.out.println("获取失败的连接数："+noGets);
        System.out.println("获取成功的连接数："+gets);
    }
}
