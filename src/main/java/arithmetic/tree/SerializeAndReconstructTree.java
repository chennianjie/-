package arithmetic.tree;

import arithmetic.structure.TreeNode;

/**
 * 节点之间用"!"相拼接
 * 节点为空用"#"替换
 *
 * @Description: 序列化和反序列化二叉树
 * @Author: nianjie.chen
 * @Date: 11/18/2019
 */
public class SerializeAndReconstructTree {

    /**
     * 先序遍历的方式
     * 二叉树序列化成字符串的过程，空用#代替，每个字符连接用“_”
     * @param head
     * @return
     */
    public static String serializeTreePre(TreeNode head) {
        if (head == null) {
            return "#!";
        }
        //当前头节点值拼上左节点，再拼右节点的值，先序序列化
        String res = head.val + "!";
        res += serializeTreePre(head.left);
        res += serializeTreePre(head.right);
        return res;
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
        String s = SerializeAndReconstructTree.serializeTreePre(node);
        System.out.println(s);
    }
}
