package arithmetic.array;

import java.util.Arrays;

/**
 * @Description: 扑克牌顺子
 * 首先把数组排序，再统计数组中 0 的个数，最后统计排序之后的数组中相邻数字之间的空缺总数。如果空缺的总数小于或者等于 0 的个数，那么这个数组就是连续的：反之则不连续。
 * 另外，还需要注意一点： 如果数组中的非 0 数字重复出现，则该数组不是连续的
 * @Author: nianjie.chen
 * @Date: 11/6/2019
 */
public class Iscontinuous {


    public boolean isContinuous(int [] numbers) {
        //参数判断
        if (numbers == null || numbers.length < 5) {
            return false;
        }
        //排序之后统计0的个数
        Arrays.sort(numbers);
        int cnt = 0;
        for (int num : numbers) {
            if (num == 0) {
                cnt++;
            }
        }
        //间隔的总值小于
        int ksum = 0;//间隔总值
        for (int i = 0; i < numbers.length - 1; i++) {
            int a = numbers[i];
            int b = numbers[i + 1];
            if (a == 0 || b == 0) {
                continue;
            }
            if (b == a) {
                return false;
            }
            ksum += b - a - 1;
        }
        if ((cnt != 0 && ksum == cnt)|| ksum ==0) {
            return true;
        }
        return false;
    }
}
