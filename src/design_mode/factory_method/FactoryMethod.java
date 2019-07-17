package design_mode.factory_method;

/**
 * 工厂方法模式（类似简单工厂模式）
 * 简单工厂模式把对象的创建交给一个类，工厂方法模式把对象的创建交给子类（多态）
 */
public abstract class FactoryMethod {

    public Product product;

    public  void doSomething(){
        product = createInstance();
    }

    public abstract Product createInstance();

}
