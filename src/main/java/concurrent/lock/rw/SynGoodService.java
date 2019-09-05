package concurrent.lock.rw;


import common.SleepTools;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 9/4/2019
 */
public class SynGoodService implements GoodService {

    private GoodInfo goodInfo;

    public SynGoodService(GoodInfo goodInfo) {
        this.goodInfo = goodInfo;
    }

    @Override
    public synchronized GoodInfo get() {
        SleepTools.ms(5);
        return this.goodInfo;
    }

    @Override
    public synchronized void sale(int saleNum) {
        SleepTools.ms(5);
        goodInfo.changeStoreNum(saleNum);
    }
}
