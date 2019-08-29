package concurrent.forkjoin.sumarray;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 使用forkjoin方式求数组的和
 * 继承，实现compute方法
 */
public class ForkjoinSumArray extends RecursiveTask<Integer> {

    public static final int ARRAY_LENGTH = 1000000;
    //阈值
    public static final int THRESHOLDS = ARRAY_LENGTH/10;

    //需要处理的源文件
    private int[] src;
    private int fromIndex;
    private int toIndex;

    public ForkjoinSumArray(int[] src, int fromIndex, int toIndex) {
        this.src = src;
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
    }

    @Override
    protected Integer compute() {
        int result = 0;
        //当我们需要处理的任务在规定的阈值内
        if (toIndex - fromIndex < THRESHOLDS) {
            for (int i = fromIndex; i <= toIndex; i++) {
                result += src[i];
            }
            return result;
        } else {
            //继续拆分任务
            int mid = (toIndex + fromIndex)/2;
            ForkjoinSumArray left = new ForkjoinSumArray(src, fromIndex, mid);
            ForkjoinSumArray right = new ForkjoinSumArray(src, mid + 1, toIndex);
            //将拆分的子任务invokeAll进队列
            invokeAll(left, right);
            //返回结果
            return left.join() + right.join();
        }

    }

    public static void main(String[] args) {
        int[] randomArray = RandomArray.buildRandomArray(1000000);

        ForkjoinSumArray forkjoinSumArray = new ForkjoinSumArray(randomArray, 0, randomArray.length - 1);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(forkjoinSumArray);
        System.out.println("Result:" + forkjoinSumArray.join());
        System.out.println("Result:" + NormalSumArray.sum(randomArray));
    }
}
