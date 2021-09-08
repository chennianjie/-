package arithmetic.stack;

/**
 * @Description: 用数组实现一个栈
 * @Author: nianjie.chen
 * @Date: 9/8/2021
 */
public class ArrayStack {


    private Integer[] arr; //存储stack中的值
    private Integer size; //标记指针

    public ArrayStack(Integer initSize){
        if (initSize < 0){
            System.out.println("初始大小不能为负数！");
        }
        arr = new Integer[initSize];
        size = 0;
    }

    public Integer peek(){
        if (size == 0){
            return null;
        }
        return arr[size-1];
    }


    public void push(Integer value){
        if (size == arr.length){
            //抛出异常
            System.out.println("Stack is full!");
            return;
        }
        arr[size++] = value;
    }

    public Integer pop(){
        if (size == 0){
            System.out.println("Stack is Empty!");
            return null;
        }
        return arr[--size];
    }

    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(5);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(4);
        arrayStack.push(5);
        System.out.println(arrayStack.peek());
        System.out.println(arrayStack.pop());
        arrayStack.push(6);
        System.out.println(arrayStack.peek());
        arrayStack.push(7);
    }
}
