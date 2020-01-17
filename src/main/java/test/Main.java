package test;

import java.util.Arrays;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 12/19/2019
 */
public class Main {
    public static void main(String[] args) {
        try {
            Thread.sleep(-1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e){

        }
        System.out.println(1111);

    }
}
