package concurrent.tools;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author: nianjie.chen
 * @Date: 8/30/2019
 * @Description:CyclicBarrier测试类
 */
public class CyclicBarrierTest {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        for (int i = 0; i < 4; i++){
            new Thread(new SubThread()).start();
        }
        System.out.println("主线程等待执行");
        cyclicBarrier.await();
        System.out.println("主线程执行结束");
    }

    //定义栅栏类
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5,new CollectResultThread());
    //统计结果需要的容器
    private static ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
    //设计一个子线程用于处理业务
    static class SubThread implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "线程开始执行");
            System.out.println(Thread.currentThread().getName() + "线程开始等待");
            try {
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + "线程执行完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    //设计一个线程用于统计业务处理之后的结果
    static class CollectResultThread implements Runnable{

        @Override
        public void run() {
            //遍历map数组并将结果进行拼接
            Set<Map.Entry<String, String>> entries = map.entrySet();
            for (Map.Entry entry : entries) {
                System.out.println(entry.getKey() + "<--->" +  entry.getValue());
            }
        }
    }

}
