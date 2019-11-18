package arithmetic.tree;

import arithmetic.structure.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @Description: 按”之“字型打印二叉树
 * @Author: nianjie.chen
 * @Date: 11/15/2019
 */
public class PrintTreeByZ {

    /**
     * 层级打印矩阵
     * 可以将偶数层的list直接reverse
     * @param pRoot
     * @return
     */
    public ArrayList<ArrayList<Integer>> PrintByFloor(TreeNode pRoot) {
        if (pRoot == null) {
            return null;
        }
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        queue.add(null);
        ArrayList<Integer> list = null;
        TreeNode pop = null;
        while (queue.size() != 1) {
            list = new ArrayList<>();
            while (queue.peek() != null) {
                pop = queue.pop();
                list.add(pop.val);
                if (pop.left != null) {
                    queue.add(pop.left);
                }
                if (pop.right != null) {
                     queue.add(pop.right);
                }
            }
            queue.pop();
            queue.add(null);
            lists.add(list);
        }
        return lists;
    }

    /**
     * 偶数行从左向右打印，奇数行从右向左打印
     * 偶数栈先存右子树，再存左子树；奇数栈先存左子树。再存右子树
     * @param pRoot
     * @return
     */
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {

        int layer = 1;
        //s1存奇数层节点
        Stack<TreeNode> s1 = new Stack<TreeNode>();
        s1.push(pRoot);
        //s2存偶数层节点
        Stack<TreeNode> s2 = new Stack<TreeNode>();

        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();

        while (!s1.empty() || !s2.empty()) {
            if (layer%2 != 0) {
                ArrayList<Integer> temp = new ArrayList<Integer>();
                while (!s1.empty()) {
                    TreeNode node = s1.pop();
                    if(node != null) {
                        temp.add(node.val);
                        System.out.print(node.val + " ");
                        s2.push(node.left);
                        s2.push(node.right);
                    }
                }
                if (!temp.isEmpty()) {
                    list.add(temp);
                    layer++;
                    System.out.println();
                }
            } else {
                ArrayList<Integer> temp = new ArrayList<Integer>();
                while (!s2.empty()) {
                    TreeNode node = s2.pop();
                    if(node != null) {
                        temp.add(node.val);
                        System.out.print(node.val + " ");
                        s1.push(node.right);
                        s1.push(node.left);
                    }
                }
                if (!temp.isEmpty()) {
                    list.add(temp);
                    layer++;
                    System.out.println();
                }
            }
        }
        return list;
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
        ArrayList<ArrayList<Integer>> print = new PrintTreeByZ().Print(node);
        for (ArrayList<Integer> list : print) {
            for (Integer i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
