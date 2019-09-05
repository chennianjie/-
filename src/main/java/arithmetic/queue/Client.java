package arithmetic.queue;

public class Client {



    public static void main(String[] args) {
        //测试队列
        ArrayQueue queue = new ArrayQueue(5);
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        queue.push(5);
        queue.poll();
        System.out.println(queue.peek());
    }
}
