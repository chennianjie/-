package design_pattern.iterator;

/**
 * 提供一种顺序访问聚合对象元素的方法，并且不暴露聚合对象的内部表示
 */
public class Client {

    /**
     * 创建聚合类，调用其方法获取它对应的构造器，操作构造器
     * @param args
     */
    public static void main(String[] args) {
        ConcreteAggregate concreteAggregate = new ConcreteAggregate();
        Iterator iterator = concreteAggregate.createIterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }
}
