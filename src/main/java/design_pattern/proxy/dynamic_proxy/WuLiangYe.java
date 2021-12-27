package design_pattern.proxy.dynamic_proxy;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 12/27/2021
 */
public class WuLiangYe implements SellWine {

    @Override
    public void sell() {
        System.out.println("我卖的是五粮液！");
    }
}
