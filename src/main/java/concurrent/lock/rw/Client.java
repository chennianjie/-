package concurrent.lock.rw;

import common.SleepTools;

import java.util.Random;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 9/4/2019
 */
public class Client {
    static final int readWriteRatio = 10;//读写线程的比例
    static final int minthreadCount = 3;//最少线程数
    //static CountDownLatch latch= new CountDownLatch(1);

    //读操作
    private static class GetThread implements Runnable{

        private GoodService GoodService;
        public GetThread(GoodService GoodService) {
            this.GoodService = GoodService;
        }

        @Override
        public void run() {
//            try {
//                latch.await();//让读写线程同时运行
//            } catch (InterruptedException e) {
//            }
            long start = System.currentTimeMillis();
            for(int i=0;i<100;i++){//操作100次
                GoodService.get();
            }
            System.out.println(Thread.currentThread().getName()+"读取商品数据耗时："
                    +(System.currentTimeMillis()-start)+"ms");

        }
    }

    //写操做
    private static class SetThread implements Runnable{

        private GoodService GoodService;
        public SetThread(GoodService GoodService) {
            this.GoodService = GoodService;
        }

        @Override
        public void run() {
//            try {
//                latch.await();//让读写线程同时运行
//            } catch (InterruptedException e) {
//            }
            long start = System.currentTimeMillis();
            Random r = new Random();
            for(int i=0;i<10;i++){//操作10次
                SleepTools.ms(50);
                GoodService.sale(r.nextInt(10));
            }
            System.out.println(Thread.currentThread().getName()
                    +"写商品数据耗时："+(System.currentTimeMillis()-start)+"ms---------");

        }
    }

    public static void main(String[] args) {
        GoodInfo GoodInfo = new GoodInfo("Cup",100000,10000);
        GoodService GoodService = new SynGoodService(GoodInfo);/*new UseSyn(GoodInfo);*/
        GoodService = new ReadWriteService(GoodInfo);
        for(int i = 0;i<minthreadCount;i++){
            Thread setT = new Thread(new SetThread(GoodService));
            for(int j=0;j<readWriteRatio;j++) {
                Thread getT = new Thread(new GetThread(GoodService));
                getT.start();
            }
            SleepTools.ms(100);
            setT.start();
        }
        //latch.countDown();

    }
}
