package test.polymorphism;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 8/26/2020
 */
public class Japanese extends Human{
    String a = "111";
    @Override
    public void relax(){
        System.out.println("日本人放松了一下");
    }
}
