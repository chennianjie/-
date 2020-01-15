package arithmetic.dynamic_plan;

import java.util.Arrays;

/**
 * @Description:
 * 最长非减子序列的长度
 * eg:{1,3,6,7,9,4,10,5,6}   -->   {1,3,6,7,9,10}可以不是连续的
 * @Author: nianjie.chen
 * @Date: 1/14/2020
 */
public class LongestIncreasingSubsequence {

    /**
     * 暴力解，挨个遍历序列段，然后判断是否是非减序列
     * 时间复杂度O（n^3）
     * 空间复杂度O(1)
     *
     * 动态规划解：
     * 状态转移方程
     * 1.确定状态d(i)  i < N ,N为原数组的长度
     * 2.推出状态转移方程，判断d(i)之前是否有比它小的值d(j)，如果有，在d(j)找出最大值 + 1即是最终结果
     * 3.状态转移方程：d(i) = max{d(j1)...d{jn}} + 1，其中i > j && array[i] > array[j],如果没有i前面没有比它小的数，d(i)=d(i-1)
     * @param array
     * @return
     */
    public int longestIncreasingSubsequence(int[] array){
        if (array.length == 0){
            return 0;
        }
        //创建dp数组
        int[] dp = new int[array.length];
        dp[0] = 1;
        int res = 1;
        int preI = 0;
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[i] > array[j]) {
                    preI = Math.max(preI, dp[j]);
                }
            }
            dp[i] = preI + 1;
            res = Math.max(res, dp[i]);
            preI = 0;
        }
        return res;
    }



    /**
     * 动态规划和二分搜索
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }


    public static void main(String[] args) {
        int i = new LongestIncreasingSubsequence().longestIncreasingSubsequence(new int[]{1,3,6,7,9,4,10,5,6});
        System.out.println(i);
    }
}
