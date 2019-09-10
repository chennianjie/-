package arithmetic.list;

import common.ListNode;

/**
 * @Description:  输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 * @Author: nianjie.chen
 * @Date: 9/9/2019
 */
public class MergeNode {
    public static ListNode Merge(ListNode list1, ListNode list2) {
        ListNode newNode = null;
        while (list1 != null && list2 != null) {
            if (newNode == null) {
                if (list1.val <= list2.val){
                    newNode = new ListNode(list1.val);
                    list1 = list1.next;
                }else {
                    newNode = new ListNode(list2.val);
                    list2 = list2.next;
                }
            }else {
                if (list1.val <= list2.val){
                    newNode.next = new ListNode(list1.val);
                    list1 = list1.next;
                }else {
                    newNode.next = new ListNode(list2.val);
                    list2 = list2.next;
                }
                //newNode不能变
                newNode = newNode.next;
            }

        }

        if(list1 != null) {
            newNode.next = list1;
        }
        if(list2 != null) {
            newNode.next = list2;
        }
        return newNode;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(3);
        ListNode listNode3 = new ListNode(4);
        ListNode listNode4 = new ListNode(5);
        listNode.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;

        ListNode listNode5 = new ListNode(4);
        ListNode listNode6 = new ListNode(6);
        ListNode listNode7 = new ListNode(7);
        ListNode listNode8 = new ListNode(8);
        ListNode listNode9 = new ListNode(9);
        listNode5.next = listNode6;
        listNode6.next = listNode7;
        listNode7.next = listNode8;
        listNode8.next = listNode9;

        ListNode merge = MergeNode.Merge(listNode, listNode5);
        System.out.println(merge.val);
    }
}
