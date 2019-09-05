package design_pattern.sigleton;

/**
 * 采用静态内部类方式的单例模式
 * 当 InnerClassSingleton 类被加载时，静态内部类 SingletonHolder 没有被加载进内存。
 * 调用getSingleInstance方法时才加载，JVM保证INSTANCE只初始化一次
 */
public class InnerClassSingleTon {

    private InnerClassSingleTon(){}

    private static class SingleTonHolder{
        private static final InnerClassSingleTon INSTANCE = new InnerClassSingleTon();
    }

    public InnerClassSingleTon getSingleInstance(){
        return SingleTonHolder.INSTANCE;
    }
}
