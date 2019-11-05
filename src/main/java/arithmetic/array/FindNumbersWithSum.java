package arithmetic.array;

import java.util.ArrayList;

/**
 * @Description:
 * 连续排序数组中和为s的两个数
 * 如果有多组两数和满足条件，选用两数乘积最小的
 * @Author: nianjie.chen
 * @Date: 11/5/2019
 */
public class FindNumbersWithSum {


    /**
     * 两个指针first和last，一个从前往后，一个从后往前；当两个数和等于sum即满足条件
     * @param array
     * @param sum
     * @return
     */
    public ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {
        ArrayList<Integer> res = new ArrayList<>();
        int first = 0;
        int last = array.length - 1;

        while (first < last) {
            if (array[first] + array[last] == sum) {
                res.add(array[first]);
                res.add(array[last]);
                break;
            }
            if (array[first] + array[last] < sum) {
                first++;
            }else {
                last--;
            }
        }

        return res;
    }
}
