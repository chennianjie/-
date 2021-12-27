package arithmetic.queue;

import common.MyRuntimeException;


/**
 * 实现一个队列
 * 用数组，可以使用集合api
 */
public class ArrayQueue {

    private int size = 0;
    private int first = 0;//表示第一个进入的
    private int last = 0;//表示最后一个进入队列的
    private Integer[] list;

    public ArrayQueue(int size) {
        list = new Integer[size];
    }

    /**
     * 插入一个值
     * @param value
     */
    public void push(int value) {
        if (size == list.length) {
            throw new MyRuntimeException("超出队列容量啦！");
        }

        list[last] = value;
        last = (last == list.length - 1) ? 0 : last + 1;
        size++;
    }

    /**
     * 弹出一个值，并删除
     * @return
     */
    public Integer poll(){
        if (size == 0) {
            throw new MyRuntimeException("没有值啦!");
        }

        int temp = first;
        first = (first == list.length - 1) ? 0 : first + 1;
        size--;
        return list[temp];
    }

    /**
     * 弹出一个值不删除
     * @return
     */
    public Integer peek(){
        if (size == 0) {
            return null;
        }
        return list[first];
    }
}
