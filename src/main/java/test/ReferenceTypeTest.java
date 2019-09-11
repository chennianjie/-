package test;

import common.ListNode;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 9/10/2019
 */
public class ReferenceTypeTest
{

    public static void main(String[] args) {
        ListNode listNode = new ListNode(4);
        ListNode listNode6 = new ListNode(6);
        ListNode listNode7 = new ListNode(7);
        ListNode listNode8 = new ListNode(8);
        ListNode listNode9 = new ListNode(9);
        listNode.next = listNode6;
        listNode6.next = listNode7;
        listNode7.next = listNode8;
        listNode8.next = listNode9;

        ListNode listNode1 = listNode;

        listNode1.next = new ListNode(100);

        System.out.println();
    }
}
