package arithmetic.tree;


import common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: 是否是完全二叉树
 * 若设二叉树的深度为 h，除第 h 层外，其它各层 (1～h-1) 的结点数都达到最大个数，第 h 层所有的结点都连续集中在最左边，
 * 这就是完全二叉树。（注：第 h 层可能包含 1~ 2h 个节点。）
 *
 * @Author: nianjie.chen
 * @Date: 1/7/2020
 */
public class IsComplementTree {

    /**
     * BFS方式遍历二叉树，当遍历到null元素时，如果还有未遍历的结点，就不是完全二叉树
     * @param root
     * @return
     */
    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean flag = false;
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            if (treeNode == null) {
                flag = true;
                //continue的精髓，treeNode为空一直continue，不为空且flag=true即满足不是完全二叉树的条件
                continue;
            }
            if (flag) {
                return false;
            }
            queue.add(treeNode.left);
            queue.add(treeNode.right);
        }
        return true;
    }

    public static void main(String[] args) {
        common.TreeNode node = new common.TreeNode(3);
        common.TreeNode node1 = new common.TreeNode(4);
        common.TreeNode node2 = new common.TreeNode(5);
        common.TreeNode node3 = new common.TreeNode(6);
        common.TreeNode node4 = new common.TreeNode(7);
        common.TreeNode node5 = new common.TreeNode(8);
        node.left = node1;
        node.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = null;
        node2.right = node5;
        boolean completeTree = new IsComplementTree().isCompleteTree(node);
        System.out.println(completeTree);
    }

}
