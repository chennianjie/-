package arithmetic.structure;

/**
 * @Description:  循环双端队列
 * front==rear时表示队列为空
 * front==(rear+1)%capacity表示列队满了
 * @Author: nianjie.chen
 * @Date: 2/27/2020
 */
class MyCircularDeque {

    private int[] arr;
    private int capacity;
    private int front; //指向双端队列开头有效值
    private int rear; //指向双端队列最后有效值的下一位

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        capacity = k + 1;//数组空一个值，用于区分空和满两种状态
        arr = new int[capacity];
        front = rear = 0;
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (isFull()){
            return false;
        }
        front = (front - 1 + capacity)%capacity; //开头越界从结尾循环
        arr[front] = value;
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (isFull()){
            return false;
        }
        arr[rear] = value;
        rear = (rear + 1)%capacity; //结尾越界到达开头
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (isEmpty()){
            return false;
        }
        front = (front + 1)%capacity;
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (isEmpty()){
            return false;
        }
        rear = (rear - 1 + capacity)%capacity;
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        if (isEmpty()){
            return -1;
        }
        return arr[front];
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if (isEmpty()){
            return -1;
        }
        // 当 rear 为 0 时防止数组越界
        return arr[(rear - 1 + capacity) % capacity];
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return front == rear;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return (rear + 1)%capacity == front;
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */