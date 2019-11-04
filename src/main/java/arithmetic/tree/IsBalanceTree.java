package arithmetic.tree;

import common.TreeNode;

/**
 * @Description: 判断平衡二叉树
 * @Author: nianjie.chen
 * @Date: 11/4/2019
 */
public class IsBalanceTree {


    public boolean isBalanceTree(TreeNode root) {
        if (root == null) {
            return true;
        }

        if (root.left == null && root.right == null) {
            return true;
        }

        if (root.left == null || root.right == null) {
            return false;
        }
        int diff = deepthTree(root.left) - deepthTree(root.right);
        return diff > 1 || diff < -1 ? false : true;

    }

    public int deepthTree(TreeNode root) {
        if (root == null) {
            return -1;
        }

        int left = deepthTree(root.left);
        int right = deepthTree(root.right);

        return Math.max(left, right) + 1;
    }
}
