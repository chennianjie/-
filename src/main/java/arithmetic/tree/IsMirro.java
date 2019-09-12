package arithmetic.tree;

import common.TreeNode;

/**
 * @Description:
 * 判断一个树是否是另一个树的镜像树
 * @Author: nianjie.chen
 * @Date: 9/12/2019
 */
public class IsMirro {

    public boolean isMirro(TreeNode tree1, TreeNode tree2) {
        if (tree1 == null && tree2 == null) {
            return true;
        }

        if (tree1 == null || tree2 == null) {
            return false;
        }

        if (tree1.val == tree2.val) {
            return isMirro(tree1.left, tree2.right) && isMirro(tree1.right, tree2.left);
        }
        return false;
    }
}
