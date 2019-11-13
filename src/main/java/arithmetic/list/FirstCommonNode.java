package arithmetic.list;

import arithmetic.structure.ListNode;

import java.util.HashMap;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 11/4/2019
 */
public class FirstCommonNode {

    /**
     * 使用hashmap特性，先遍历一遍存储链表1的值，然后遍历链表2的值是否有和map中相等的值
     * @param pHead1
     * @param pHead2
     * @return
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        HashMap<ListNode, Integer> map = new HashMap<>();
        while (pHead1 != null) {
            map.put(pHead1, 0);
            pHead1 = pHead1.next;
        }

        while (pHead2 != null) {
            if (map.containsKey(pHead2)) {
                return pHead2;
            }
            pHead2 = pHead2.next;
        }
        return null;
    }

    /**
     * 长链表先走两链表长度差值的距离，然后两个链表同步next移动对比
     * @param pHead1
     * @param pHead2
     * @return
     */
    public ListNode FindFirstCommonNode2(ListNode pHead1, ListNode pHead2) {
        int count1 = 0, count2= 0;
        ListNode node1 = pHead1;
        while (node1 != null) {
            node1 = node1.next;
            count1++;
        }
        node1 = pHead2;
        while (node1 != null) {
            node1 = node1.next;
            count2++;
        }
        if (count1 > count2) {
            while ((count1 - count2) > 0) {
                pHead1 = pHead1.next;
                count1--;
            }
        }else {
            while ((count2 - count1) > 0) {
                pHead2 = pHead2.next;
                count2--;
            }
        }

        while (pHead1 != null) {
            if (pHead1 == pHead2) {
                return pHead1;
            }
            pHead1 = pHead1.next;
            pHead2 = pHead2.next;
        }

        return null;
    }


    /**
     * 长度相同有公共结点，第一次就遍历到；没有公共结点，走到尾部NULL相遇，返回NULL
     * 长度不同有公共结点，第一遍差值就出来了，第二遍一起到公共结点；没有公共，一起到结尾NULL。
     * @param pHead1
     * @param pHead2
     * @return
     */
    public ListNode FindFirstCommonNode3(ListNode pHead1, ListNode pHead2) {
        ListNode p1 = pHead1;
        ListNode p2 = pHead2;
        while(p1!=p2){
            p1 = (p1==null ? pHead2 : p1.next);
            p2 = (p2==null ? pHead1 : p2.next);
        }
        return p1;
    }


    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;

        listNode4.next = listNode5;
        listNode5.next = listNode3;
        ListNode listNode = new FirstCommonNode().FindFirstCommonNode3(listNode1, listNode4);
        System.out.println(listNode.val);
    }
}
