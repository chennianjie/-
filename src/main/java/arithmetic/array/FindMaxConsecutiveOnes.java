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
        //是否全是1
        int flag = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count++;
            }else {
                flag++;
                max = Math.max(count, max);
                count = 0;
            }
        }
        if(count != 0) {
            max = Math.max(count, max);
        }
        if (flag == 0) {
            return count;
        }
        return max;
    }

    public static void main(String[] args) {
        int maxConsecutiveOnes = new FindMaxConsecutiveOnes().findMaxConsecutiveOnes(new int[]{0, 1});
        System.out.println(maxConsecutiveOnes);
    }
}
