package design_pattern.sigleton;

/**
 * 采用双重校验锁方式的单例模式
 * 只有当单例对象为空的时候才加锁
 * 内层if是为了当有多个线程进入时，避免创建多个single对象
 */
public class SigleTon {

    /*
        加上volatile的原因：禁止指令重排序
        sigleInstance = new SigleTon()这条代码分为三个步骤：
            1.为 sigleInstance 分配内存空间
            2.初始化 sigleInstance
            3.将 sigleInstance 指向分配的内存地址
            但是由于 JVM 具有指令重排的特性，执行顺序有可能变成 1>3>2。
            指令重排在单线程环境下不会出现问题，但是在多线程环境下会导致一个线程获得还没有初始化的实例。
    */
    private volatile static SigleTon sigleInstance;

    private SigleTon(){}

    public static SigleTon getSigleInstance(){
        if (sigleInstance == null) {
            synchronized (SigleTon.class) {
                if (sigleInstance == null) {
                    sigleInstance = new SigleTon();
                }
            }
        }
        return sigleInstance;
    }
}
