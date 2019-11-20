package arithmetic.stack;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 小根堆最小的那个数都比大根堆最大的那个数值大
 * @Description:  寻找中位数
 * @Author: nianjie.chen
 * @Date: 11/18/2019
 */
public class MedianHolder {


    //设计大根堆和小根堆
    private PriorityQueue<Double> minHeap = new PriorityQueue<>(new MinHeapCompararot());
    private PriorityQueue<Double> maxHeap = new PriorityQueue<>(new MaxHeapComparator());

    public void Insert(Double num) {
        addNumber(num);
    }

    public Double GetMedian() {
        return getMedian();
    }

    public Double getMedian(){

        if (maxHeap.isEmpty()) {
            return null;
        }

        if (minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        }else if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        }else {//数量一致的时候
            return (minHeap.peek() + maxHeap.peek())/2;
        }
    }


    private static class MinHeapCompararot implements Comparator<Double> {

        //返回正数o2放在前面，负数o1放在前面
        @Override
        public int compare(Double o1, Double o2) {
            if (o2 < o1) {
                return 1;
            }else {
                return -1;
            }
        }
    }

    private static class MaxHeapComparator implements Comparator<Double> {
        //返回正数o1放在前面，负数o2放在前面
        @Override
        public int compare(Double o1, Double o2) {
            if (o2 < o1){
                return -1;
            }else {
                return 1;
            }
        }
    }

    private void adjustHeapSize(){
        if (minHeap.size() + 2 == maxHeap.size()) {
            minHeap.add(maxHeap.poll());
        }

        if (minHeap.size() == maxHeap.size() + 2) {
            maxHeap.add(minHeap.poll());
        }
    }

    //设计一个大根堆，一个小根堆，一边放n/2个数，默认第一个数放大根堆，大于大根堆的放小根堆
    //控制两堆size，相差超过1就从一个堆弹出一个数放进另一个堆
    public void addNumber(Double number){
        if (maxHeap.isEmpty()) {
            maxHeap.add(number);
        }else if (number > maxHeap.peek()) {
            minHeap.add(number);
        }else {
            maxHeap.add(number);
        }

        //调整两堆的数量，使其均衡
        adjustHeapSize();
    }
}
