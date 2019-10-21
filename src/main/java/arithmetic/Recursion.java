package arithmetic;

import arithmetic.structure.ListNode;
import arithmetic.structure.Node;

/**
 * @Description:递归
 * @Author: nianjie.chen
 * @Date: 10/21/2019
 */
public class Recursion {

    public void recursion(int depth) {
        System.out.println("抱着");
        if (depth == 1) {
            System.out.println("我的小鲤鱼");
        } else {
            recursion(--depth);  // 递归调用
        }
        System.out.println("的我");
    }

    public static void main(String[] args) {
        Recursion recursion = new Recursion();
        recursion.recursion(2);

        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(3);
        ListNode listNode3 = new ListNode(4);
        listNode.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        ListNode listNode4 = Recursion.reverseNode(listNode);
        while (listNode4 != null) {
            System.out.println(listNode4.v);
            listNode4 = listNode4.next;
        }
    }


    /**
     * eg:1->2->3->4
     * 第一步：（newList）4->3->2<-1；第二步：4->3->2->1
     * 首先确定反转链表可以是一个递归的过程
     * 使用递归方式反转链表
     * @param head
     * @return
     */
    public static ListNode reverseNode(ListNode head) {
        //终止条件
        if (head == null || head.next == null) {
            return head;
        }

        //head的next节点全部调整成功
        ListNode newList = reverseNode(head.next);
        ListNode t1 = head.next;//这里head.next是值为2的节点，要把2->1，1->null
        t1.next = head;
        head.next = null;

        return newList;
    }

}
