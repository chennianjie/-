package test.polymorphism;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 8/25/2020
 */
public class Chinese extends Human{
    String b = "111";
    @Override
    public void relax(){
        System.out.println("中国人放松了一下");
    }
}
