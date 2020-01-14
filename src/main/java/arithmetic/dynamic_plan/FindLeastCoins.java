package arithmetic.dynamic_plan;

/**
 * @Description:
 * 找寻最少个数的硬币
 * 从{1,3,5}这三种面值的硬币中找出可以凑出n元的最少个数
 * @Author: nianjie.chen
 * @Date: 1/14/2020
 */
public class FindLeastCoins {

    /**
     * V = [1,3,5]
     * 状态转移方程：d(i) = min{d(i - Vj)+1, d(i-1)+1}；d(0)=0  Vj指的是第j枚面值的硬币
     * @param n
     * @return
     */
    public int findLeastCoins(int n) {
        int[] V = new int[]{1,3,5};
        int[] dp = new int[n+1];
        dp[0] = 0;
        //开始向dp中填值
        for (int i = 1; i < n+1; i++){
            dp[i] = dp[i - 1] + 1;
            for (int j = 0; j < V.length; j++) {
                if (i < V[j]){
                    break;
                }
                int includeCurrCoin = dp[i - V[j]]+1;
                if (includeCurrCoin < dp[i]) {
                    dp[i] = includeCurrCoin;
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int leastCoins = new FindLeastCoins().findLeastCoins(11);
        System.out.println(leastCoins);
    }
}
