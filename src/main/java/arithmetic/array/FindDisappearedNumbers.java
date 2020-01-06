package arithmetic.array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description: 找寻数组中消失的数， 1=< a[i] <= n(数组大小)
 *  输入:
    [4,3,2,7,8,2,3,1]
    输出:
    [5,6]


思路一：hash表把数组值存储，然后遍历1-n的整数判断不存在的数
思路二：遍历数组，因为数值范围在1-n内，定位当前下标对应值的newIndex,然后（nums[newIndex]*-1）表示newIndex这个对应的值存在，
最后找出数组中正数即可

扩展：考虑到数组中存在负数的情况
 * @Author: nianjie.chen
 * @Date: 1/6/2020
 */
public class FindDisappearedNumbers {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            //巧妙在于即标记了变量也保留下变量的值
            int newIndex = Math.abs(nums[i]) - 1;
            if (nums[newIndex] > 0) {
                nums[newIndex] *= -1;
            }
        }
        List<Integer> result = new LinkedList<>();
        for (int i = 1; i <= nums.length; i++) {
            if (nums[i - 1] > 0) {
                result.add(i);
            }
        }
        return result;
    }

    public ArrayList<Integer> findDisappearedNumbers2(int[] nums) {
        for(int i=0;i<nums.length;){
            if(nums[nums[i]-1] != nums[i]){
                int tmp = nums[i];
                nums[i] = nums[tmp-1];
                nums[tmp-1] = tmp;
            } else {
                i++;
            }
        }
        ArrayList<Integer> res = new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=i+1){
                res.add(i+1);
            }
        }
        return res;
    }

}
