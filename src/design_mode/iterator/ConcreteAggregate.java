package design_mode.iterator;

/**
 * 聚合类具体实现类
 */
public class ConcreteAggregate implements Aggregate {

    private Integer[] values = new Integer[]{1,2,3,4,5,6,7,8,9,10};

    @Override
    public Iterator createIterator() {
        return new ConcreteIterator(values);
    }
}
