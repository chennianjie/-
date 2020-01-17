package arithmetic.array;

import java.util.Arrays;

/**
 * @Description:
 * 找0和1相同数量的最长连续子数组的长度
 * 1.暴力解，遍历每一个子数组，找出最长连续子数组
 * @Author: nianjie.chen
 * @Date: 1/16/2020
 */
public class FindMaxLength {

    /**
     * 遍历数组，遇到1就count+1,遇到0就count-1，记录count的值、标记此时的index、标记此count值是否是第一次，
     * 然后如果后续再次计算出此count值，即算出此时的index差求出最大值
     * 使用一个辅助数组记录count最早出现的index,下次再遇到相同的count则说明这段子数组中0和1的数量相同
     * @param arr
     * @return
     */
    public int findMaxLength(int[] arr) {

        if (arr == null || arr.length <= 1) {
            return 0;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[i] = -1;
            }
        }
        //考虑到数组可能全为1或0的情况，count的取值范围在-n,n,counts数组上存储索引index
        int[] counts = new int[2 * arr.length + 1];
        Arrays.fill(counts, -2);
        //表示开始遍历位置时count为0
        int count = 0;
        int maxLen = 0;
        counts[arr.length] = -1;
        for (int i = 0; i < arr.length; i++) {
            count = count + arr[i];
            //count + arr.length表示count下标实际在数组中的位置
            if (counts[count + arr.length] == -2){
                //标记count第一次出现的位置
                counts[count + arr.length] = i;
            }else {
                //count不是第一次出现
                maxLen = Math.max(maxLen, i - counts[count + arr.length]);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(new FindMaxLength().findMaxLength(new int[]{0,0,1,0,0,1}));
        System.out.println(new FindMaxLength().findMaxLength(new int[]{1,1,1,1,1,1}));
    }
}
