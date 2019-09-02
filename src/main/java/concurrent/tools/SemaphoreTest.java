package concurrent.tools;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 9/2/2019
 */
public class SemaphoreTest {
    private final Semaphore semaphore ;
    private AtomicReferenceArray atomicReferenceArray;
    private final ReentrantLock lock;
    public SemaphoreTest() {
        //存放厕所状态
        this.atomicReferenceArray = new AtomicReferenceArray(10);
        //控制10个共享资源的使用，使用先进先出的公平模式进行共享;公平模式的信号量，先来的先获得信号量
        this.semaphore = new Semaphore(10,true);
        //公平模式的锁，先来的先选
        this.lock = new ReentrantLock(true);
        for(int i=0 ;i<10; i++){
            //初始化为资源可用的情况
            atomicReferenceArray.set(i, true);
        }
    }

    public void useResource(int userId){
        try{
            semaphore.acquire();
            //semaphore.acquire();
            //占到一个坑
            int id = getResourceId();
            System.out.print("userId:"+userId+"正在使用资源，资源id:"+id+"\n");
            //do something，相当于于使用资源
            Thread.sleep(100);
            //退出这个坑
            atomicReferenceArray.set(id, true);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            //释放信号量，计数器加1
            semaphore.release();
        }
    }
    private int getResourceId(){
        int id = -1;
        //lock方法放在try块外面，避免发生异常执行finally里面的内容
        lock.lock();
        try {
            //虽然使用了锁控制同步，但由于只是简单的一个数组遍历，效率还是很高的，所以基本不影响性能。
            for(int i=0; i<10; i++){
                if((boolean)atomicReferenceArray.get(i)){
                    atomicReferenceArray.set(i, false);
                    id = i;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return id;
    }
    static class ResourceUser implements Runnable {
        private SemaphoreTest SemaphoreTest;
        private int userId;

        public ResourceUser(SemaphoreTest SemaphoreTest, int userId) {
            this.SemaphoreTest = SemaphoreTest;
            this.userId = userId;
        }

        @Override
        public void run() {
            System.out.print("userId:" + userId + "准备使用资源...\n");
            SemaphoreTest.useResource(userId);
            System.out.print("userId:" + userId + "使用资源完毕...\n");
        }
    }
    public static void main(String[] args){
        SemaphoreTest SemaphoreTest = new SemaphoreTest();
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(new ResourceUser(SemaphoreTest,i));//创建多个资源使用者
            threads[i] = thread;
        }
        for(int i = 0; i < 100; i++){
            Thread thread = threads[i];
            try {
                thread.start();//启动线程
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
