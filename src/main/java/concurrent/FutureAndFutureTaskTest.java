package concurrent;

import java.util.concurrent.*;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 9/3/2019
 */
public class FutureAndFutureTaskTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Task task = new Task();
        //方式一：future

        Integer integer = null;
        try {
//            submit.cancel(true);
            Future<Integer> submit = executorService.submit(task);
            System.out.println("取消状态："+submit.isCancelled());
            integer = submit.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("获得的结果是(方式一)" +  integer);
        System.out.println("主线程结束");


        //方式二：futuretask
        FutureTask<Integer> integerFutureTask = new FutureTask<>(task);

        Integer integer1 = null;
        try {
            executorService.submit(integerFutureTask);
            integer1 = integerFutureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
        System.out.println("获得的结果是（方式二）："+integer1);
    }


    static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            return 1989;
        }
    }
}
