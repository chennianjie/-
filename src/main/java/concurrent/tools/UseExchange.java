package concurrent.tools;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Exchanger;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 9/3/2019
 */
public class UseExchange {
    private static final Exchanger<Set<String>> exchange
            = new Exchanger<>();

    public static void main(String[] args) {

        //第一个线程
        new Thread(()-> {
            {
                Set<String> setA = new HashSet<>();//存放数据的容器
                try {
                    /*添加数据
                     * set.add(.....)
                     * */
                    setA.add("chennianjie");
                    setA = exchange.exchange(setA);//交换set
                    /*处理交换后的数据*/
                    for (String a:setA) {
                        System.out.println("setA"+a);
                    }
                } catch (InterruptedException e) {
                }
            }
        }).start();

        //第二个线程
        new Thread(()->{
            {
                Set<String> setB = new HashSet<String>();//存放数据的容器
                try {
                    /*添加数据
                     * set.add(.....)
                     * set.add(.....)
                     * */
                    setB.add("wangling");
                    setB = exchange.exchange(setB);//交换set
                    for (String a:setB) {
                        System.out.println("setB"+a);
                    }
                    /*处理交换后的数据*/
                } catch (InterruptedException e) {
                }
            }
        }).start();
    }
}