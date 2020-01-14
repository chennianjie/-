package arithmetic.array;

import java.util.TreeSet;

/**
 * @Description:
 * 给定一个整数数组，判断数组中是否有两个不同的索引 i 和 j，
 * 使得 nums [i] 和 nums [j] 的差的绝对值最大为 t，并且 i 和 j 之间的差的绝对值最大为 ķ。
 *
 *
 * 优化版：
 * 概念：  自平衡二叉搜索树:这个树在随机进行插入,删除操作之后，它会自动保证树的高度最小
 * TreeSet就是一个自平衡的二叉搜索树
 * @Author: nianjie.chen
 * @Date: 1/14/2020
 */
public class ContainsNearbyAlmostDuplicate {

    /**
     * 时间复杂度：O(nmin(k,n))
     * 空间复杂度：O（1）
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        //判断每个数与它k之前的数的差的绝对值是否满足条件
        for (int i = 0; i < nums.length; i++) {
            //滑动窗口
            for (int j = Math.max(i-k,0); j < i; j++) {
                if (Math.abs(nums[j]-nums[i]) <= t){
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 解法二：二叉搜索树优化版
     * 时间复杂度：O(nlog(min(n,k))
     * 空间复杂度：O(min(n,k))
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        TreeSet<Integer> set = new TreeSet<>();
        int count = 0;
        for (int i = 0; i < nums.length; ++i) {
            //减少判断的次数
            // 返回set中大于等于num[i]的数值最小的数
            Integer s = set.ceiling(nums[i]);
            if (s != null && s <= nums[i] + t) return true;
            // 返回set中小于等于num[i]的数值最大的数
            Integer g = set.floor(nums[i]);
            if (g != null && nums[i] <= g + t) return true;
            //添加和删除操作的时间复杂度为O（logN）
            set.add(nums[i]);
            count++;
            if (count > k) {
                set.remove(nums[i - k]);
                count--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        ContainsNearbyAlmostDuplicate containsNearbyAlmostDuplicate = new ContainsNearbyAlmostDuplicate();
//        System.out.println(containsNearbyAlmostDuplicate.containsNearbyAlmostDuplicate(new int[]{1,2,3,1}, 3, 0));
//        System.out.println(containsNearbyAlmostDuplicate.containsNearbyAlmostDuplicate(new int[]{1,5,9,1,5,9}, 2, 3));


        TreeSet<Integer> set = new TreeSet<>();
        set.add(1);
        set.add(2);
        set.add(1);
        set.add(2);
        System.out.println(set.size());
    }
}
