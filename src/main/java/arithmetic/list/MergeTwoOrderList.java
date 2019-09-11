package arithmetic.list;

import common.ListNode;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 9/10/2019
 */
public class MergeTwoOrderList {
        public static ListNode Merge(ListNode list1, ListNode list2) {
            if (list1 == null) {
                return list2;
            }
            if (list2 == null) {
                return list1;
            }
            ListNode newList = new ListNode(-1);
            ListNode p1 = list1;
            ListNode p2 = list2;
            ListNode p3 = newList;
            while (p1 != null && p2 != null) {
                if (p1.val < p2.val) {
                    p3.next = p1;
                    p1 = p1.next;
                } else {
                    p3.next = p2;
                    p2 = p2.next;
                }
                p3 = p3.next;
            }

            while (p1 != null) {
                p3.next = p1;
                p1 = p1.next;
                p3 = p3.next;
            }

            while (p2 != null) {
                p3.next = p2;
                p2 = p2.next;
                p3 = p3.next;
            }
            return newList.next;
        }

}

