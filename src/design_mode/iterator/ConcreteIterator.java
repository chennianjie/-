package design_mode.iterator;

/**
 * 迭代器的具体实现类
 * 控制数组的输出
 * @param <Item>
 */
public class ConcreteIterator<Item>  implements Iterator{

    private Integer position = 0;
    private Item[] items;

    //有参构造方法
    public ConcreteIterator(Item[] items) {
        this.items = items;
    }

    public Integer getPosition() {
        return position;
    }

    public Item[] getItems() {
        return items;
    }

    @Override
    public boolean hasNext() {
        return position < items.length;
    }

    @Override
    public Item next() {
        return items[position++];
    }
}
