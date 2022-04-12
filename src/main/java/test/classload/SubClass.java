package test.classload;

/**
 * @Description:
 *
 * static    类中静态块按照声明顺序执行
 *
 * @Author: nianjie.chen
 * @Date: 10/20/2020
 */
public class SubClass{

    public static SubClass subClass = new SubClass();  //创建了一个SubClass对象，在这个过程中会执行非静态代码块和缺省的无参构造函数
    {
        System.out.println("子类非静态块");
    }
    static {
        System.out.println("子类静态块");
    }



    public static void main(String[] args) {
        SubClass subClass = new SubClass();
    }
}
