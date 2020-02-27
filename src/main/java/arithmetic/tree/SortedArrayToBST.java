package arithmetic.tree;

import arithmetic.structure.TreeNode;
import test.Sort;

/**
 * @Description:
 * 排序数组转换成二叉搜索树
 * @Author: nianjie.chen
 * @Date: 2/26/2020
 */
public class SortedArrayToBST {

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0){
            return null;
        }
        return helper(nums, 0, nums.length-1);
    }

    private TreeNode helper(int[] nums, int start, int end) {
        if (start > end){
            return null;
        }
        int mid = (end + start)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, start, mid-1);
        root.right = helper(nums, mid + 1, end);
        return root;
    }

    public static void main(String[] args) {
        TreeNode node = new SortedArrayToBST().sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
        System.out.println(node);
    }
}
