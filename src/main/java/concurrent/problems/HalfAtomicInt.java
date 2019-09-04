package concurrent.problems;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 有一个残缺AtomicInteger的类实现了线程安全的：get方法和compareAndSet()方法
 * 自行实现它的递增方法
 * @Author: nianjie.chen
 * @Date: 9/4/2019
 */
public class HalfAtomicInt {
        private AtomicInteger atomicI = new AtomicInteger(0);

    /**
     * 递增
     */
    public void increament() {
            for (;;) {
                int i = atomicI.get();
                boolean suc = atomicI.compareAndSet(i, ++i);
                if (suc) {
                    break;
                }
            }
        }

    public int getCount() {
            return atomicI.get();
        }
}
