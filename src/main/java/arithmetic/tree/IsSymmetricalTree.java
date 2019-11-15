package arithmetic.tree;

import arithmetic.structure.TreeNode;

import java.util.Stack;

/**
 * @Description: 判断二叉树是否是对称的
 * @Author: nianjie.chen
 * @Date: 11/15/2019
 */
public class IsSymmetricalTree {


    /**
     * 使用队列BFS遍历判断基本一致，就注意一下弹出顺序即可
     * 使用栈实现DFS遍历并判断是否为对称二叉树
     * 每次成对弹出判断
     * @param pRoot
     * @return
     */
    boolean isSymmetricalDFS(TreeNode pRoot) {
        if (pRoot == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(pRoot.left);
        stack.add(pRoot.right);
        TreeNode left;
        TreeNode right;
        while (!stack.isEmpty()) {
            right = stack.pop();
            left = stack.pop();
            if (right == null && left == null) {
                return true;
            }
            if (right == null || left == null) {
                return false;
            }
            if (left.val != right.val) {
                return false;
            }

            stack.add(left.left);
            stack.add(right.right);
            stack.add(left.right);
            stack.add(right.left);
        }
        return true;
    }

    boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) {
            return true;
        }

        if (pRoot.left == null && pRoot.right != null) {
            return false;
        }

        if (pRoot.right == null && pRoot.left != null) {
            return false;
        }

        return isSymmetrical(pRoot.left, pRoot.right);
    }

    private boolean isSymmetrical(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }

        if (t1 == null || t2 == null) {
            return false;
        }

        if (t1.val == t2.val) {
            return isSymmetrical(t1.left, t2.right) && isSymmetrical(t1.right, t2.left);
        }else {
            return false;
        }
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(-1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(5);
        node.left = node1;
        node.right = node2;
        node1.left = node3;
        node2.right = node4;
        node1.right = node5;
        node2.left = node6;
        boolean symmetrical = new IsSymmetricalTree().isSymmetrical(node);
        System.out.println(symmetrical);
    }
}
