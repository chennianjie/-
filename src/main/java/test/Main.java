package test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 12/19/2019
 */
public class Main {
    public static void main(String[] args) throws UnknownHostException {
        try {
            Thread.sleep(-1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e){

        }
        System.out.println(1111);


        InetAddress addr = InetAddress.getLocalHost();
        System.out.println("Local HostAddress: "+addr.getHostAddress());
                String hostname = addr.getHostName();
        System.out.println("Local host name: "+hostname);
    }
}
