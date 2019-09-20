package test;

import java.util.Arrays;
import java.util.UUID;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 9/17/2019
 */
public class ArrayTest {

    public static int[] reOrderArray(int[] array) {
        //先找出数组中奇数的个数，按个数划分数组，然后申请一个同样大小的数组进行填充
        int oddNumCount = findOddNumCount(array);
        int[] arr = new int[array.length];
        int index = 0;
        for (int num : array) {
            if ((num&0x1) == 1){
                arr[index++] = num;
            }else {
                arr[oddNumCount++] = num;
            }
        }
        return arr;
    }

    /***
     * 找整数数组中的奇数个数
     * @param array
     * @return
     */
    public static int findOddNumCount(int[] array) {
        int count = 0;
        for (int num : array) {
            if ((num&0x1) == 1){
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6};
        int[] a = reOrderArray(arr);
        System.out.println(Arrays.toString(a));
        System.out.println(UUID.randomUUID().toString());
    }
}
