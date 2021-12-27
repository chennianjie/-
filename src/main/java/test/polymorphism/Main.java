package test.polymorphism;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 8/25/2020
 */
public class Main {

    public static void main(String[] args) {
        Human chinese = new Chinese();
        Human Japanese = new Japanese();
        chinese.openEyes();
        String a = "111";
        String b = "111";
        System.out.println(a==b);
        System.out.println(Japanese.a==chinese.a);
    }
}
