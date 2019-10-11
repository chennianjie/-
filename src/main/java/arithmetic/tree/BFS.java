package arithmetic.tree;

import common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 10/10/2019
 */
public class BFS {


    public static void bfs(TreeNode node) {

        Queue<TreeNode> queue = new LinkedBlockingQueue<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            System.out.println(treeNode.val);
            if (treeNode.left != null) {
                queue.add(treeNode.left);
            }
            if (treeNode.right != null) {
                queue.add(treeNode.right);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(3);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(7);
        TreeNode node5 = new TreeNode(8);
        node.left = node1;
        node.right = node2;
        node2.left = node3;
        node2.right = node4;
        node1.left = node5;
        bfs(node);
        LinkedList linkedList = new LinkedList();
    }
}
