package arithmetic.array;

/**
 * @Description:
 * 最大连续个数1
 * @Author: nianjie.chen
 * @Date: 1/8/2020
 */
public class FindMaxConsecutiveOnes {


    public int findMaxConsecutiveOnes(int[] nums) {
        int max = Integer.MIN_VALUE;
        int count = 0;
        //是否存在0
        boolean flag = true;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count++;
            }else {
                flag = false;
                max = Math.max(count, max);
                count = 0;
            }
        }
        if (flag) {
            return
        }
        return max;
    }

    public static void main(String[] args) {
        int maxConsecutiveOnes = new FindMaxConsecutiveOnes().findMaxConsecutiveOnes(new int[]{1, 0, 0, 1, 1, 1, 0, 1, 1});
        System.out.println(maxConsecutiveOnes);
    }
}
