package arithmetic.list;

import common.TreeNode;

import java.util.Stack;


/**
 * @Description: 二叉排序树转换成双向链表
 * 使用中序排序，形成有序的双向链表
 * @Author: nianjie.chen
 * @Date: 10/18/2019
 */
public class OrderTreeToListNode {

    public TreeNode Convert(TreeNode pRootOfTree) {

        TreeNode currentNode = pRootOfTree;
        if (currentNode == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack();
        TreeNode node = null;
        TreeNode retNode = null;
        while (currentNode!= null || !stack.isEmpty()) {
            if (currentNode != null) {
                stack.push(currentNode);
                //左
                currentNode = currentNode.left;
            }else {
                //中
                currentNode = stack.pop();
                if (node == null) {
                    //currentNode与node之间的变化
                    node = currentNode;
                    retNode = node;
                }else {
                    node.right = currentNode;
                    currentNode.left = node;
                    node = node.right;
                }
                //右，这里的currentNode始终比node快一步
                currentNode = currentNode.right;
            }
        }

        return retNode;
    }
}
