package arithmetic.array;

/**
 * @Description:寻找一个数组中连续子序列中的最大值
 * 思路：使用两个变量，一个用于存储当前值，一个用于存储最大值；
 * 分两种情况考虑
 * 1.当加上当前数总数比当前数还要小的时候，总数和的起点从当前数开始
 * 2.当遍历的当前数是正数时更新最大值和当前值，当前数是负数时只更新当前值，再次遇到正数时再与最大值进行比较并更新
 * eg: 1 2 3 -9 8    ===>   6
 * @Author: nianjie.chen
 * @Date: 10/22/2019
 */
public class FindGreatestSumOfSubArray {


    public static void main(String[] args) {
        FindGreatestSumOfSubArray findGreatestSumOfSubArray = new FindGreatestSumOfSubArray();
        int[] array = {6,-3,-2,7,-15,1,2,2};

    }

    public int FindGreatestSumOfSubArray(int[] array) {
        int currVal = 0;
        int result = 0;
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
                result += i;
            }
            return result;
        }

        if (flag2) {
            return result;
        }

        for (int i : array) {
            result = i + result;
            if (i > 0 && result < i) {
                result = i;
            }
        }


        return 0;
    }


}
