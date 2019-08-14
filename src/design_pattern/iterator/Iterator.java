package design_pattern.iterator;

/**
 * 迭代器接口类
 * @param <Item>
 */
public interface Iterator<Item> {

    boolean hasNext();

    Item next();
}
