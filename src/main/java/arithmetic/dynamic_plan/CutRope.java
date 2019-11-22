package arithmetic.dynamic_plan;

/**
 * @Description: 剪绳子
 * @Author: nianjie.chen
 * @Date: 11/22/2019
 */
public class CutRope {

    /**
     * 动态规划解法
     * @param target
     * @return
     */
    public static int maxAfterCutting(int target) {
        if (target < 2) {
            return 0;
        }
        if (2 == target) {
            return 1;
        }
        if (3 == target) {
            return 2;
        }
        int[] products = new int[target + 1];  // 将最优解存储在数组中
        // 数组中第i个元素表示把长度为i的绳子剪成若干段之后的乘积的最大值
        products[0] = 0;
        products[1] = 1;
        products[2] = 2;
        products[3] = 3;
        int max;
        for (int i = 4; i <= target; i++) {  //i表示长度
            max = 0;
            for (int j = 1; j <= i / 2; j++) {  //由于长度i存在（1，i-1）和（i-1，1）的重复，所以只需要考虑前一种
                int product = products[j] * products[i - j];
                if (product > max) {
                    max = product;
                }
            }
            products[i] = max;
        }
        return products[target];
    }

    /**
     * 贪心算法版本
     * @param target
     * @return
     */
    public static int maxAfterCutting2(int target) {
        if (target < 2) {
            return 0;
        }
        if (2 == target) {
            return 1;
        }
        if (3 == target) {
            return 2;
        }
        int a = 0;
        int timesOf3 = target/3;
        if (target - timesOf3*3 == 1) {
            timesOf3--;
            a = 4;
        }
        if (a == 0) {
            a = 2;
        }
        return a * (int)Math.pow(3, timesOf3);
    }


    public static void main(String[] args) {
        int i = CutRope.maxAfterCutting(10);
        System.out.println(i);
        int i1 = CutRope.maxAfterCutting2(10);
        System.out.println(i1);
    }

}
