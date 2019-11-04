package arithmetic.tree;

import common.TreeNode;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 11/4/2019
 */
public class Exchange {

    public void exchage(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode left = root.left;
        TreeNode right = root.right;
        TreeNode help = left;
        left = right;
        right = help;

        exchage(root.left);
        exchage(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode root1 = new TreeNode(2);
        TreeNode root2 = new TreeNode(3);
        root.left = root1;
        root.right = root2;

        new Exchange().exchage(root);
    }
}
