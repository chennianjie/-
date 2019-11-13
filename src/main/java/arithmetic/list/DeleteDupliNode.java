package arithmetic.list;

import arithmetic.structure.ListNode;

/**
 * @Description: 删除链表中的重复节点
 * @Author: nianjie.chen
 * @Date: 11/13/2019
 */
public class DeleteDupliNode {

    /**
     * 1,2,3,3,4  -->  1,2,3,4
     * @param pHead
     * @return
     */
    public ListNode deleteDuplication2(ListNode pHead) {

        ListNode helpNode = pHead;
        while (helpNode != null) {
            if (helpNode.next != null && helpNode.val == helpNode.next.val) {
                helpNode.next = helpNode.next.next;
            }
            helpNode = helpNode.next;
        }

        return pHead;
    }

    /**
     * 删掉
     * 链表为排序好的链表
     * 1,2,3,3,4  -->  1,2,4
     * @param pHead
     * @return
     */
    public ListNode deleteDuplication(ListNode pHead) {
        ListNode node = new ListNode(-1);
        ListNode cur = pHead;
            //始终存放不重复的结点
        ListNode temHead = node;
            while (cur != null){
                if (cur.next != null&&cur.val == cur.next.val){
                    while (cur.next != null&&cur.val == cur.next.val){
                        cur = cur.next;
                    }
                    cur = cur.next;
                    //将temHead.next的指向更新,但保持temHead的值不变
                    temHead.next = cur;
                }else {
                    //确定是不重复的结点，串在虚拟节点之后
                    temHead.next = cur;
                    temHead = cur;
                    cur = cur.next;
                }
            }
            return node.next;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(3);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(4);
        ListNode listNode6 = new ListNode(5);
        listNode.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;


        ListNode listNode7 = new DeleteDupliNode().deleteDuplication(listNode);
        while (listNode7 != null) {
            System.out.print(listNode7.val);
            listNode7 = listNode7.next;
        }
    }
}
