package arithmetic.tree;

import arithmetic.structure.TreeNode;

import java.util.Stack;

/**
 * @Description: 搜索二叉树中第k小的值
 * @Author: nianjie.chen
 * @Date: 11/18/2019
 */
public class KthNodeValue {

    /**
     * 非递归版本
     * @param pRoot
     * @param k
     * @return
     */
    TreeNode KthNode(TreeNode pRoot, int k) {
        if (pRoot != null) {
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || pRoot != null) {
                if (pRoot != null) {
                    stack.push(pRoot);
                    pRoot = pRoot.left;
                } else {
                    pRoot = stack.pop();
                    if (--k == 0) {
                        return pRoot;
                    }
                    pRoot = pRoot.right;
                }
            }
        }
        return null;
    }
}
