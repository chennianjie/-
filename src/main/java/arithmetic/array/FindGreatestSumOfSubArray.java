package arithmetic.array;

/**
 * @Description:寻找一个数组中连续子序列中的最大值
 * 思路：1 -2 3 10 -4 7 2 -5
 *
 * 当从1加到3时，总和为2，小于当前数3，也就是说，3比前面的子数组之和大，这样就舍弃3前面的子数组；
 * 紧接着3 + 10 = 13，然后遇到一个负数，大家都知道，加上一个负数，总和变小，所以当前总和13可能为答案，保存；加到7的时候总和为13 + 7 - 4 = 16，所以更新最大总和为16，最终得出结果；
 * 在此过程中，需要一个变量保存最大总和，一个变量保存实时总和值
 *
 *
 * @Author: nianjie.chen
 * @Date: 10/22/2019
 */
public class FindGreatestSumOfSubArray {


    public static void main(String[] args) {
        FindGreatestSumOfSubArray findGreatestSumOfSubArray = new FindGreatestSumOfSubArray();
        int[] array = {1, -2, 3, 10, -4, 7, 2, -5};
        int i = findGreatestSumOfSubArray.FindGreatestSumOfSubArray(array);
        System.out.println(i);
    }

    public int FindGreatestSumOfSubArray(int[] array) {

        if (array == null || array.length == 0) {
            return 0;
        }

        int currVal = 0;
        int sumMax = 0;
        //输入处理
        boolean flag = true;
        boolean flag2 = true;
        for (int i : array) {
            if (i < 0) {
                flag = false;
            }

            if (i > 0) {
                flag2 = false;
            }
        }
        //如果flag为true，全部为正数；如果flag2为true，全部为负数
        if (flag) {
            for (int i : array) {
                sumMax += i;
            }
            return sumMax;
        }

        if (flag2) {
            return sumMax;
        }

        for (int i = 0; i < array.length; i++) {
            if (currVal <= 0) {
                currVal = array[i];
            }else {
                currVal += array[i];

            }
            if (currVal > sumMax) {
                sumMax = currVal;
            }
        }
        return sumMax;
    }


    /**
     * 使用动态规划
     * F（i）：以array[i]为末尾元素的子数组的和的最大值，子数组的元素的相对位置不变
     * F（i）=max（F（i-1）+array[i] ， array[i]）
     * res：所有子数组的和的最大值
     * res=max（res，F（i））
     *
     * 如数组[6, -3, -2, 7, -15, 1, 2, 2]
     * 初始状态：
     *     F（0）=6
     *     res=6
     * i=1：
     *     F（1）=max（F（0）-3，-3）=max（6-3，3）=3
     *     res=max（F（1），res）=max（3，6）=6
     * i=2：
     *     F（2）=max（F（1）-2，-2）=max（3-2，-2）=1
     *     res=max（F（2），res）=max（1，6）=6
     * i=3：
     *     F（3）=max（F（2）+7，7）=max（1+7，7）=8
     *     res=max（F（2），res）=max（8，6）=8
     * i=4：
     *     F（4）=max（F（3）-15，-15）=max（8-15，-15）=-7
     *     res=max（F（4），res）=max（-7，8）=8
     * 以此类推
     * 最终res的值为8
     * @param array
     * @return
     */
    public  int FindGreatestSumOfSubArray2(int[] array) {
        int res = array[0]; //记录当前所有子数组的和的最大值
        int max=array[0];   //包含array[i]的连续数组最大值
        for (int i = 1; i < array.length; i++) {
            max=Math.max(max+array[i], array[i]);
            res=Math.max(max, res);
        }
        return res;
    }



}
