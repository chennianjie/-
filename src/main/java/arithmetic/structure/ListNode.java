package arithmetic.structure;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 10/21/2019
 */
public class ListNode {

    public int v;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int v, ListNode next) {
        this.v = v;
        this.next = next;
    }

    public ListNode(int i) {
        this.v = i;
    }
}
