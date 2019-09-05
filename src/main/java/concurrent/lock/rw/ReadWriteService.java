package concurrent.lock.rw;

import common.SleepTools;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description: 利用读写锁的方式
 * @Author: nianjie.chen
 * @Date: 9/4/2019
 */
public class ReadWriteService implements GoodService{

    private GoodInfo goodInfo;

    public ReadWriteService(GoodInfo goodInfo) {
        this.goodInfo = goodInfo;
    }

    static final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static final Lock readLock = readWriteLock.readLock();
    static final Lock writeLock = readWriteLock.writeLock();


    @Override
    public GoodInfo get() {
        SleepTools.ms(5);
        readLock.lock();
        try {
            return this.goodInfo;
        }finally {
            readLock.unlock();
        }
    }

    @Override
    public void sale(int saleNum) {
        SleepTools.ms(5);
        writeLock.lock();
        try {
            goodInfo.changeStoreNum(saleNum);
        }finally {
            writeLock.unlock();
        }
    }
}
