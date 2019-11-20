package arithmetic.structure;

import java.util.LinkedList;

/**
 * 最大窗口结构
 * dequeue
 * head(大) <---> last(小)
 * 维护这样一个降序的双端队列，当有新值来的时候：1.双端队列head端的值（窗口最大值下标）等于窗口结构最左边值的下标（将要淘汰的下标），双端队列弹出该值
 * 2.当前值与双端队列last端（最小值端）所对应的值比较，弹出比当前值小的所有下标，然后插入当前下标
 * 3.当遍历完k个值得时候，每次从双端队列中head端peek下标即是当前最大窗口的最大值的下标
 *
 */
public class MaxWindowValue {
        public int[] maxSlidingWindow2(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) {
                return new int[0];
            }
            //创建双端队列（双向链表），实现最大窗口结构
            //先向队列放入k个数，之后再头去掉一个，尾加上一个计算最大值
            int[] res = new int[nums.length - k + 1];
            int indexRes = 0;
            LinkedList<Integer> maxQueue = new LinkedList<>();
            int L = 0;
            for (int i=0; i<k; i++){

                //向队列尾部加值，如果当前值比尾部第一个值大，尾部数弹出，直到队列尾部值大于当前值或队列为空，然后插入当前值下标进队列
                while (!maxQueue.isEmpty() && nums[maxQueue.peekLast()] <= nums[i]) {
                    maxQueue.pollLast();
                }
                maxQueue.addLast(i);
            }
            res[indexRes] = nums[maxQueue.peekFirst()];

            for (int i = k; i < nums.length; i++) {
                //队尾加入一个数，对头去掉一个数，调整这两个操作之后双端链表的状态
                while (!maxQueue.isEmpty() && nums[maxQueue.peekLast()] <= nums[i]) {
                    maxQueue.pollLast();
                }
                maxQueue.addLast(i);

                //如果过期下标（即对头淘汰的下标）和对头的下标相同，就弹出对头，否则不做任何操作
                if (L == maxQueue.peekFirst()) {
                    maxQueue.pollFirst();
                }
                //左边索引变化，向结果数组里插值
                L++;
                indexRes++;
                res[indexRes] = nums[maxQueue.peekFirst()];
            }
            return res;
        }


        public int[] maxSlidingWindow(int[] nums, int k) {
            if(nums == null || nums.length == 0 || k <= 0 || k > nums.length) {
                return new int[0];
            }
            LinkedList<Integer> deque = new LinkedList<>();
            int[] res = new int[nums.length + 1 - k];
            for(int i = 0; i < nums.length; i++){
                // 每当新数进来时，如果发现队列头部的数的下标，是窗口最左边数的下标，则扔掉
                if(!deque.isEmpty() && deque.peekFirst() == i - k) {
                    deque.poll();
                }
                // 把队列尾部所有比新数小的都扔掉，保证队列是降序的
                while(!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                    deque.removeLast();
                }
                // 加入新数
                deque.offerLast(i);
                // 队列头部就是该窗口内第一大的
                if((i + 1) >= k) {
                    res[i + 1 - k] = nums[deque.peek()];
                }
            }
            return res;
        }

    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        MaxWindowValue maxWindowValue = new MaxWindowValue();
        int[] ints = maxWindowValue.maxSlidingWindow(nums, k);
        int[] ints1 = maxWindowValue.maxSlidingWindow2(nums, k);
        for (int a: ints) {
            System.out.print(a);
        }
        System.out.println();
        for (int a: ints1) {
            System.out.print(a);
        }
    }

}
