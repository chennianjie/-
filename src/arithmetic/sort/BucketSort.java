package arithmetic.sort;

/**
 * 桶排序，时间复杂多元N，空间复杂度N*logN
 * 根据数据状况的排序
 * 要求需要排序的数组都是自然数
 */
public class BucketSort {

    public static void bucketSort(int[] arr){
        //找出该数组中的最大值
        int max = Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++){
            max = Math.max(max,arr[i]);
        }
        //创建一个max+1大小的数值
        int[] bucket = new int[max+1];
        //将桶里面填满值
        for(int i=0;i<arr.length;i++){
            bucket[arr[i]]++;
        }
        int j = 0;
        //将桶里面的数按顺序倒出来
        for(int i=0;i<bucket.length;i++){
            while(bucket[i]>0){
                arr[j++] = i;
                bucket[i]--;
            }
        }
    }

}
