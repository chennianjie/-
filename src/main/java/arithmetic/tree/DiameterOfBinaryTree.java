package arithmetic.tree;

import arithmetic.structure.TreeNode;


/**
 * @Description:
 * 树的直径，树中任意结点的左右子树高度和的最大值
 * ps:不是求根节点左右子树深度之和
 * @Author: nianjie.chen
 * @Date: 1/13/2020
 */
public class DiameterOfBinaryTree {
    int result = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root.left == null && root.right == null){
            return 0;
        }
        height(root);
        return result;
    }

    private int height(TreeNode root){
        if (root == null) {
            return 0;
        }
        int left = height(root.left);
        int right = height(root.right);
        result = Math.max(result, left + right);
        return Math.max(left, right) + 1;
    }

}
