package arithmetic.list;

import arithmetic.structure.ListNode;



/**
 * @Description:
 *  (3→4→2)
 * +(4→6→5)
 * = 8→0→7
 * 利用数学加法原理
 * (3→4→2)
 *+(4→6→5)
 *= 7→0→8
 * 然后反转链表
 * @Author: nianjie.chen
 * @Date: 1/6/2020
 */
public class TwoNumAdd {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //设置一个循环遍历两个链表上的位数进行相加，终止条件为两链表都遍历完
        //设置一个参数用记录是否有进位，该变量的值为0或1
        ListNode head = new ListNode(0);
        ListNode n1 = l1, n2 = l2, curr = head;
        int carry = 0;
        while (n1 != null || n2 != null) {
            //当前两个链表位数对应的值可能为当前位数值或null(0),所以用x和y变量表示
            //计算下一次进位的值和当前两个链表当前位相加之后组成当前新链表当前位置的值
            //两个链表进行下一次移动
            int x = n1 != null ? n1.val : 0;
            int y = n2 != null ? n2.val : 0;
            int sum = x + y + carry;
            curr.next = new ListNode(sum%10);
            curr = curr.next;
            carry = sum/10;
            if (n1 != null) {
                n1 = n1.next;
            }
            if (n2 != null) {
                n2 = n2.next;
            }
        }

        if (carry != 0) {
            curr.next = new ListNode(carry);
        }
        return reverse1(head.next);
    }

    /**
     * 反转链表
     * @param head
     * @return
     */
    public ListNode reverse1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode helpNode = new ListNode(-1);
        helpNode.next = head;
        ListNode pre = head;
        ListNode cur = pre.next;
        while (cur != null) {
            pre.next = cur.next;
            cur.next = helpNode.next;
            helpNode.next = cur;
            cur = pre.next;
        }
        return helpNode.next;
    }


    public static void main(String[] args) {
        ListNode ll = new ListNode(3);
        ListNode a = new ListNode(4);
        ListNode b = new ListNode(2);
        ListNode l2 = new ListNode(4);
        ListNode c = new ListNode(6);
        ListNode d = new ListNode(5);
        ll.next = a;
        a.next = b;
        l2.next = c;
        c.next = d;
        ListNode listNode = new TwoNumAdd().addTwoNumbers(ll, l2);
        while (listNode != null) {
            System.out.print(listNode.val);
            listNode = listNode.next;
        }
    }
}
