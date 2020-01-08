package arithmetic.tree.serializeTree;

import arithmetic.structure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

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



    /**
     * 先序方式的反序列化
     * @param res
     * @return
     */
    public static TreeNode reconTreePre(String res) {
        //判断字符串是否为空
        //将字符串按照规则拆分，放进一个队列里面
        //从队列里面挨个弹出，按照先序反序列化，先生成头节点，再拼左节点，最后右节点
        if (res == null) {
            return null;
        }
        String values[] = res.split("!");
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
    private static TreeNode reconTreePreContinue(Queue<String> queue) {
        //每次从队列里面弹出一个数，如果当前数表示空，则返回null（base case）
        //生成一个头节点，拼接左节点，拼接右节点
        String value = queue.poll();
        if ("#".equals(value)){
            return null;
        }
        TreeNode head = new TreeNode(Integer.parseInt(value));
        head.left = reconTreePreContinue(queue);
        head.right = reconTreePreContinue(queue);
        return head;
    }


    /**
     * 层级序列化
     * @param head
     * @return
     */
    public static String serializeByLevel(TreeNode head) {
        if (head == null) {
            return "#_";
        }
        //需要一个队列，先放入头节点，拼接好头节点，然后循环检查这个队列是否有值
        //如果有左节点，则把左节点放入队列并拼接；右节点同理
        //注意，如果节点为空，还需要拼接上“#”，空字符串的标记
        String res = head.val + "_";
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(head);
        TreeNode node;
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
                res += node.left.val;
            }else {
                res += "#_";
            }
            if (node.right != null) {
                queue.offer(node.right);
                res += node.right.val;
            }else {
                res += "#_";
            }
        }
        return res;
    }


    /**
     * 层级反序列化
     * @param value
     * @return
     */
    public static TreeNode reconByLevel(String value) {
        //参考层级序列化的规则反序列化
        //使用一个队列，存储所有拆分的序列化字符串
        //如果字符串不是空，则生成一个节点，然后根据队列的规律按层拼接
        String[] values = value.split("_");
        int index = 0;//数组下标辅助索引
        TreeNode head = generateNodeByString(values[index++]);
        Queue<TreeNode> queue = new LinkedList<>();
        if (head != null) {
            queue.offer(head);
        }
        TreeNode node;
        while (!queue.isEmpty()) {
            node = queue.poll();
            TreeNode left = generateNodeByString(values[index++]);
            TreeNode right = generateNodeByString(values[index++]);
            if (left != null) {
                node.left = left;
                //把左节点放入队列
                queue.offer(node.left);
            }
            if (right != null) {
                node.right = right;
                //把有节点放入队列
                queue.offer(node.right);
            }
        }
        return head;//这里返回head，请仔细分析循环中的赋值过程
    }

    /**
     * 根据字符串生成一个节点
     * @param val
     * @return
     */
    private static TreeNode generateNodeByString(String val) {
        if (val.equals("#")) {
            return null;
        }
        return new TreeNode(Integer.valueOf(val));
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
