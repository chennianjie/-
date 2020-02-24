package arithmetic.dynamic_plan;


/**
 * @Description: 1130. 叶值的最小代价生成树
 * 我们可以递归地求解最小和，即定义一个方法getMinSum，让它返回可以得到的最小和。
 * 因为我们将序列分为左右两边，它们分别是左右子树，所以只要在两边分别找出最大元素就可以计算当前节点的值。
 *
 * @Author: nianjie.chen
 * @Date: 2/24/2020
 */
public class MctFromLeafValues {

    /**
     * @param arr 中序遍历的叶子节点顺序
     * @return 存在非叶节点值的和的最小值
     */
    public int mctFromLeafValues(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        dp = new int[n][n];
        return getMinSum(arr, 0, arr.length - 1);
    }

    //记录范围内的最小非叶节点值总和
    private int[][] dp;

    /**
     * 存在非叶节点值的和的最小值
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private int getMinSum(int[] arr, int left, int right) {
        //base case
        if (left == right){
            return 0;
        }

        if (dp[left][right] != 0){
            return dp[left][right];
        }

        int minSum = Integer.MAX_VALUE;

        for (int i=left; i < right; i++){
            //考虑所有可拆分的情况
            int leftStart = left, leftEnd = i, rightStart = i + 1, rightEnd = right;
            int temp = getMinSum(arr, leftStart, leftEnd) + getMinSum(arr, rightStart, rightEnd) +
                    getMaxNum(arr, left, leftEnd) * getMaxNum(arr, right, rightEnd);//左子树最小 + 右子树最小 + 当前节点
            minSum = Math.min(minSum, temp);
        }
        dp[left][right] = minSum;
        return minSum;
    }

    /**
     * 求范围内数组中的最大值
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private int getMaxNum(int[] arr, int left, int right) {
        int max = Integer.MIN_VALUE;
        for (int i=left; i <= right; i++){
            max = Math.max(arr[i], max);
        }
        return max;
    }

    public static void main(String[] args) {
        int i = new MctFromLeafValues().mctFromLeafValues(new int[]{2, 4, 6});
        System.out.println(i);
    }
}
