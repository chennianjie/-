package arithmetic.tree;

import arithmetic.structure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description:  二叉树层级遍历
 * @Author: nianjie.chen
 * @Date: 11/18/2019
 */
public class LevelIterator {
    public void levelIterator(TreeNode root) {
        if (root == null)
            return;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode current = null;
        //将根节点入队
        queue.offer(root);
        while (!queue.isEmpty()) {
            current = queue.poll();//出队队头元素并且访问
            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }
    }
}