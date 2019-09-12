package arithmetic.tree;

import common.TreeNode;

/**
 * @Description:
 * 1.判断当前Node根节点的值是否与判断父树的根节点的值相等
 * 1.1不相等先比较左子树是否是判断父树的子树，再判断右子树是否是判断父树的子树
 * 1.2相等比较左右子树是否在判断父树中有相等的部分
 * @Author: nianjie.chen
 * @Date: 9/12/2019
 */
public class HasSubTree {

    /**
     * 判断treeNode2是否是treeNode1的子树
     * @param treeNode1
     * @param treeNode2
     * @return
     */
    public boolean hasSubTree(TreeNode treeNode1, TreeNode treeNode2) {
        boolean result = false;
        if (treeNode1 != null && treeNode2 != null) {
            if (treeNode1.val == treeNode2.val) {
              result = hasSubTree2(treeNode1, treeNode2);
            }
            //如果当前根节点虽然相等，但是tree2从当前根节点出发不是tree1的子树
            if (!result) {
                result = hasSubTree(treeNode1.left, treeNode2);
            }
            if (!result) {
                result = hasSubTree(treeNode1.right, treeNode2);
            }
        }
        return result;
    }

    /**
     * 当两个树的根节点相等的时候，判断2树是否是1树的子树
     * @param treeNode1
     * @param treeNode2
     * @return
     */
    private boolean hasSubTree2(TreeNode treeNode1, TreeNode treeNode2) {
        //如果tree1为空，tree2不为空返回false
        if (treeNode1 == null && treeNode2 != null) {
            return false;
        }
        //如果tree2为空返回true,因为此时表示tree2所有节点都完成了比较
        if (treeNode2 == null) {
            return true;
        }
        //如果tree1与tree2的val值不等返回false
        if (treeNode1.val != treeNode2.val) {
            return false;
        }
        //递归判断左数和右树
        return hasSubTree2(treeNode1.left, treeNode2.left) && hasSubTree2(treeNode1.right, treeNode2.right);
    }
}
