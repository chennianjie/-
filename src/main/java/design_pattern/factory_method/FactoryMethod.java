package design_pattern.factory_method;

/**
 * 工厂方法模式（类似简单工厂模式）
 * 简单工厂模式把对象的创建交给一个类，工厂方法模式把对象的创建交给子类（多态）
 * 在创建一个对象时不向客户暴露内部细节，并提供一个创建对象的通用接口
 * 定义了一个创建对象的接口，但由子类决定要实例化哪个类。工厂方法把实例化操作推迟到子类。
 */
public abstract class FactoryMethod {

    public Product product;

    public  void doSomething(){
        product = createInstance();
    }

    public abstract Product createInstance();

}
