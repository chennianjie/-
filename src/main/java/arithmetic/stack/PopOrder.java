package arithmetic.stack;

/**
 * @Description:
 * 输入两个整数序列，其中一个序列为入栈序列，两个序列都是不相等的整数，判断另一个序列是否可能是前一个序列的出栈序列
 * 思路：
 * 规律，假如入栈是12345，出栈序列第一个数例如为3，则3之后的4、5随意顺序出栈，紧接着是3之前2、1顺序出栈
 * @Author: nianjie.chen
 * @Date: 9/20/2019
 */
public class PopOrder {

    public static boolean IsPopOrder(int [] pushA,int [] popA) {

        boolean result = true;
        int flag = popA[0];
        Integer index = null;
        for (int i = 0; i < pushA.length; i++) {
            if (pushA[i] == flag) {
                index = i;
            }
        }
        if (index == null) {
            return false;
        }
        int startIndex = pushA.length - index;
        for (int i = startIndex; i < popA.length; i++) {
            if (pushA[--index] != popA[i]) {
                result = false;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] pushA = new int[]{1, 2, 3, 4, 5};
        int[] popA = new int[]{4, 5, 3 ,2 ,1};

        boolean b = IsPopOrder(pushA, popA);
        System.out.println(b);

    }
}
