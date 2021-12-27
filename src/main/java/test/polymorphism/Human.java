package test.polymorphism;


/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 8/25/2020
 */
public abstract class Human {
    String a = "111";

    public void openEyes(){
        System.out.println("睁开了眼睛");
        relax();
    }

    public void relax(){
        System.out.println("放松了一下");
    }
}
