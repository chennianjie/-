package test.classload;

/**
 * @Description:
 *
 * 类中静态块按照声明顺序执行，并且(1)和(2)不需要调用new类实例的时候就执行了(意思就是在类加载到方法区的时候执行的)
 * 因而，整体的执行顺序为 
 * public static Test  t1 = new Test();         //(1)
 * static 
 * {
 * System.out.println("blockB");                //(2)
 * }
 * Test t2 =new Test();                             //(3)
 * 在执行(1)时创建了一个Test对象，在这个过程中会执行非静态代码块和缺省的无参构造函数，在执行非静态代码块时就输出了blockA；然后执行(2)输出blockB；执行(3)的过程同样会执行非静态代码块和缺省的无参构造函数，在执行非静态代码块时输出blockA。因此，最终的结果为
 * blockA
 * blockB
 * blockA
 * @Author: nianjie.chen
 * @Date: 10/20/2020
 */
public class Test
{
    public static Test t1 = new Test();
    {
        System.out.println("blockA");
    }
    static
    {
        System.out.println("blockB");
    }
    public static void main(String[] args)
    {
        Test t2 = new Test();
    }
}
