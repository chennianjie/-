package arithmetic.list;

import common.ListNode;

/**
 * 找到链表倒数第k个结点
 * 找出需要停止的下标在哪里是关键所在
 *
 * 代码思路：两个指针，先让第一个指针和第二个指针都指向头结点，然后再让第一个指正走(k-1)步，到达第k个节点。
 * 然后两个指针同时往后移动，当第一个结点到达末尾的时候，第二个结点所在位置就是倒数第k个节点了
 */
public class FindFinalListNode {
    public ListNode FindKthToTail(ListNode head, int k) {
        int nodeLength = findNodeLength(head);
        if (k > nodeLength || k < 1) {
            return new ListNode(404);
        }
        for (int i = 1; i <= nodeLength - k; i++) {
            head = head.next;
        }
        return head;
    }

    public int findNodeLength(ListNode listNode) {
        int length = 0;
        while (listNode != null) {
            length++;
            listNode = listNode.next;
        }
        return length;
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
        FindFinalListNode findFinalListNode = new FindFinalListNode();
        ListNode listNode5 = findFinalListNode.FindKthToTail(listNode, 1);
        System.out.println(listNode5.val);
    }
}
