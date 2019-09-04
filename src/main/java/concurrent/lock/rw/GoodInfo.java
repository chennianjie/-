package concurrent.lock.rw;

/**
 * @Description: 商品实体类
 * 设计库存和销售额两个属性
 * @Author: nianjie.chen
 * @Date: 9/4/2019
 */
public class GoodInfo {

    private String name;
    //库存
    private int storeNum;
    //销售额
    private int saleMoney;

    public GoodInfo() {
    }

    public GoodInfo(String name, int storeNum, int saleMoney) {
        this.name = name;
        this.storeNum = storeNum;
        this.saleMoney = saleMoney;
    }

    public int getStoreNum() {
        return storeNum;
    }

    public void setStoreNum(int storeNum) {
        this.storeNum = storeNum;
    }

    public int getSaleMoney() {
        return saleMoney;
    }

    public void setSaleMoney(int saleMoney) {
        this.saleMoney = saleMoney;
    }

    /**
     * 卖出东西
     * @param saleNum
     */
    public void changeStoreNum(int saleNum) {
        storeNum -= saleNum;
        saleMoney += saleNum*20;
    }
}
