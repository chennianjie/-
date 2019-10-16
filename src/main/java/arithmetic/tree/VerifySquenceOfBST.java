package arithmetic.tree;

/**
 * @Description:
 * 验证一个数组是否是一个二叉搜索树的后序遍历
 * 数组最后一个数的二叉树的头节点，后序遍历（左右中）
 * @Author: nianjie.chen
 * @Date: 10/15/2019
 */
public class VerifySquenceOfBST {

    public static void main(String[] args) {
        VerifySquenceOfBST verifySquenceOfBST = new VerifySquenceOfBST();
        int[] arr = new int[]{4,8,6,12,16,14,10};
        boolean b = verifySquenceOfBST.VerifySquenceOfBST(arr);
        System.out.println(b);
    }


    public boolean VerifySquenceOfBST(int [] sequence) {
        if (sequence == null || sequence.length == 0) {
            return false;
        }
        return verifySquenceOfBST2(sequence,0, sequence.length - 1);
    }

    public boolean verifySquenceOfBST2(int[] sequence,int start, int end) {

        if (start >= end) {
            return true;
        }

        //根节点
        int root = sequence[end];
        //找到大于右子树的开始位置
        int i = start;
        for(; i < end; i++) {
            if (sequence[i] > root) {
                break;
            }
        }

        //如果右子树开始位置往后有存在值小于根节点，即不是二插搜索树
        int j = i;
        for (; j < end; j++) {
            if (sequence[j] < root) {
                return false;
            }
        }

        //判断左右子树是否是搜索二叉树
        return verifySquenceOfBST2(sequence,start, i - 1 ) && verifySquenceOfBST2(sequence, i, end - 1);
    }
}
