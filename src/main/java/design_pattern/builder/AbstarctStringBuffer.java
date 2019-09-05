package design_pattern.builder;

import java.util.Arrays;

/**
 * 参照StringBuffer源码
 * 构造器模式，按照一定的规则生成对象实例
 */
public class AbstarctStringBuffer {

    //存储字符
    protected char[] value;
    //表示当前char数组中实际存储的值数量
    protected  int count;

    //构造方法，有参
    public AbstarctStringBuffer(int capacity) {
        count = 0;
        value = new char[capacity];
    }

    //expend方法
    public void expend(char a) {
        //确保容量是否足够
        ensureCapacityInterval(count + 1);
        //向数组中插入该值
        value[count++] = a;
    }

    private void ensureCapacityInterval(int minCapacity){
        if (minCapacity > value.length) {
            //扩容
            expandCapacity(minCapacity);
        }
    }

    private void expandCapacity(int minCapacity){
        int newCapacity = 2 * value.length + 2;
        if (minCapacity > newCapacity) {
            newCapacity = minCapacity;
        }
        if (newCapacity < 0 ) {
            if (minCapacity < 0) {
                try {
                    throw new OutOfMemoryException("Out of Memory!");
                } catch (OutOfMemoryException e) {
                    e.printStackTrace();
                }
            }
            //新算出来的容量超过int范围
            newCapacity = Integer.MAX_VALUE;
        }
        Arrays.copyOf(value, newCapacity);
    }
}
