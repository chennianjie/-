package design_pattern.proxy.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 12/27/2021
 */
public class Test {

    public static void main(String[] args) {
        MaoTai maoTai = new MaoTai();
        InvocationHandler jingxiaoshang1 = new CounterA(maoTai);
        WuLiangYe wuLiangYe = new WuLiangYe();
        InvocationHandler jingxiaoshang2 =  new CounterA(wuLiangYe);

        FuRongWang fuRongWang = new FuRongWang();
        InvocationHandler jingxiaoshang3 = new CounterA(fuRongWang);


        SellWine proxyInstance1 = (SellWine)Proxy.newProxyInstance(MaoTai.class.getClassLoader(), MaoTai.class.getInterfaces(), jingxiaoshang1);
        SellWine proxyInstance2 = (SellWine)Proxy.newProxyInstance(WuLiangYe.class.getClassLoader(), WuLiangYe.class.getInterfaces(), jingxiaoshang2);

        SellCigratte proxyInstance3 = (SellCigratte) Proxy.newProxyInstance(FuRongWang.class.getClassLoader(), FuRongWang.class.getInterfaces(), jingxiaoshang3);
        proxyInstance1.sell();
        proxyInstance2.sell();
        proxyInstance3.sell();
    }
}
