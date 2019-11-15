package arithmetic.list;

import arithmetic.structure.TreeLinkNode;

/**
 * @Description: 中序遍历获取下一个节点
 * @Author: nianjie.chen
 * @Date: 11/14/2019
 */
public class InOrderGetNextNode {

    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null) {
            return pNode;
        }

        //如果右节点不为空，则下一个节点为其右节点的最左节点
        if (pNode.right != null) {
            pNode = pNode.right;
            while (pNode.left != null) {
                pNode = pNode.left;
            }
            return pNode;
        }

        //如果右节点为空，情况1：当前节点为父节点的左子节点，则下一个节点就是他的父节点
        //情况2：如果当前节点为父节点的右子节点，则循环找寻当前节点的父节点，直到找到一个父节点，它是它父节点的左孩子，则下一个节点就是它父节点
        //      如果找不到一个父节点是它父节点的左孩子，则当前节点是尾节点，下一个节点为空。
        TreeLinkNode helpNode;
        while (pNode.next != null) {
            if (pNode.next.left == pNode) {
                return pNode.next;
            }
            pNode = pNode.next;
        }
        return null;
    }
}
