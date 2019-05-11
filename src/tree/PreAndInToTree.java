package tree;

import structure.TreeNode;

/**
 * 两个数组，一个代表二叉树的先序遍历，一个代表中序遍历，还原二叉树的head节点
 * 前序遍历{1,2,4,7,3,5,6,8}     中序遍历{4,7,2,1,5,3,8,6}
 *
 * 思路：作者：Monotone
 * 来源：牛客网
 * 递归思想，每次将左右两颗子树当成新的子树进行处理，中序的左右子树索引很好找，前序的开始结束索引通过计算中序中左右子树的大小来计算，
 * 然后递归求解，直到startPre>endPre||startIn>endIn说明子树整理完到。方法每次返回左子树活右子树的根节点
 *
 */
public class PreAndInToTree {

    public TreeNode reConstructBinaryTree(int [] pre, int [] in) {
        TreeNode root = reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
        return root;
    }

    private TreeNode reConstructBinaryTree(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd) {
        //base case
        if (preStart > preEnd || inStart > inEnd){
            return null;
        }
        TreeNode head = new TreeNode(pre[preStart]);
        //遍历中序数组，找出先序第一个（头节点）的位置，依此区分出左右节点的界限
        for (int i = inStart; i <= inEnd; i++) {
            if (in[i] == pre[preStart]) {
                head.left = reConstructBinaryTree(pre, preStart + 1, preStart + i - inStart, in, inStart, i - 1);
                head.right = reConstructBinaryTree(pre, preStart + i - inStart + 1, preEnd, in, i + 1, inEnd);
                break;
            }
        }
        return head;
    }

}
