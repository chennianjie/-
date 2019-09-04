package concurrent.lock.rw;

/**
 * 商品服务层接口
 */
public interface GoodService {

    GoodInfo get();

    void sale(int saleNum);
}
