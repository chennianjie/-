package concurrent.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description: 带版本号的类引用原子操作，解决ABA问题
 * @Author: nianjie.chen
 * @Date: 9/3/2019
 */
public class AbaProblem {
    public static void main(String[] args) {
        //ABA问题
        System.out.println("==========ABA问题：");
        AtomicReference<String> reference = new AtomicReference<>("A");
        new Thread(() -> {
            //获取期望值
            String expect = reference.get();
            //打印期望值
            System.out.println(Thread.currentThread().getName() + "---- expect: " + expect);
            try {
                //干点别的事情
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //打印实际值
            System.out.println(Thread.currentThread().getName() + "---- actual: " + reference.get());
            //进行CAS操作，这里的"A"是已经经过ABA之后的结果了
            boolean result = reference.compareAndSet("A", "X");
            //打印操作结果
            System.out.println(Thread.currentThread().getName() + "---- result: " + result + " ==》 final reference = " + reference.get());
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //进行ABA操作
            System.out.print(Thread.currentThread().getName() + "---- change: " + reference.get());
            reference.compareAndSet("A", "B");
            System.out.print(" -- > B");
            reference.compareAndSet("B", "A");
            System.out.println(" -- > A");
        }).start();
    }
}
