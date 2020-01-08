package arithmetic.tree.serializeTree;


import arithmetic.structure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description:
 * BFS OrderByLevel
 * DFS preOrderTree
 * leetcode 297
 * @Author: nianjie.chen
 * @Date: 1/8/2020
 */
public class SerializeTree297 {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "null,";
        }
        //当前头节点值拼上左节点，再拼右节点的值，先序序列化
        String res = root.val + ",";
        res += serialize(root.left);
        res += serialize(root.right);
        return res;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        //判断字符串是否为空
        //将字符串按照规则拆分，放进一个队列里面
        //从队列里面挨个弹出，按照先序反序列化，先生成头节点，再拼左节点，最后右节点
        if (data == null) {
            return null;
        }
        String values[] = data.split(",");
        Queue<String> queue = new LinkedList();
        for (String v : values) {
            queue.offer(v);
        }
        return reconTreePreContinue(queue);
    }

    /**
     * 先序反序列化
     * @param queue
     * @return
     */
    private TreeNode reconTreePreContinue(Queue<String> queue) {
        //每次从队列里面弹出一个数，如果当前数表示空，则返回null（base case）
        //生成一个头节点，拼接左节点，拼接右节点
        String value = queue.poll();
        if ("null".equals(value)){
            return null;
        }
        TreeNode head = new TreeNode(Integer.parseInt(value));
        head.left = reconTreePreContinue(queue);
        head.right = reconTreePreContinue(queue);
        return head;
    }


    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(7);
        node.left = node1;
        node.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        String serialize = new SerializeTree297().serialize(node);
        System.out.println(serialize);
    }
}
