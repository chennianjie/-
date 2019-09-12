package arithmetic.tree;

import common.TreeNode;

/**
 * @Description:
 * （前序）遍历整棵树，如果该节点不为空且包含左节点或右节点，即交换左右节点，递归执行
 * @Author: nianjie.chen
 * @Date: 9/12/2019
 */
public class CreateMirrorTree {


    public void createMirrorTree(TreeNode root) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            return;
        }
        //满足条件，交换左右节点
        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;

        createMirrorTree(root.left);
        createMirrorTree(root.right);
    }
}
