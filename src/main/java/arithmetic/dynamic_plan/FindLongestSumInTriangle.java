package arithmetic.dynamic_plan;

/**
 * @Description: 三角形中顶边（顶点）到底边的最大路径和，要求从某一点出发只能往下或右下走
 * 4 //行数
 *
 * 1
 * 2 3
 * 4 5 6
 * 7 8 9 10
 * @Author: nianjie.chen
 * @Date: 10/23/2019
 */
public class FindLongestSumInTriangle {

    int[][] arr;
    int[][] dp;
    int[] dpPro;
    int n;

    public FindLongestSumInTriangle() {
    }

    public FindLongestSumInTriangle(int[][] arr, int n, int[][] dp, int[] dpPro) {
        this.dpPro = dpPro;
        this.arr = arr;
        this.n = n;
        this.dp = dp;
    }

    public int bigestSum(int[][] arr) {
        if (arr == null) {
            return 0;
        }

        return 0;
    }

    /**
     * 使用递归的方式
     * @param i
     * @param j
     * @return
     */
    public int getMax(int i, int j) {
        if (i==n) {
            return arr[i][j];
        }

        int x = getMax(i + 1, j);
        int y = getMax(i + 1, j + 1);
        return Math.max(x, y) + arr[i][j];
    }


    /**
     * 递归动态规划
     * @param i
     * @param j
     * @return
     */
    public int getMaxDp(int i, int j) {
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        if (i==n) {
            return arr[i][j];
        }else {
            int x = getMax(i + 1, j);
            int y = getMax(i + 1, j + 1);
            dp[i][j] = Math.max(x, y) + arr[i][j];
            return dp[i][j];
        }
    }

    /**
     * 递推动态规划
     * 防止堆栈溢出
     * 1
     * 2 3
     * 4 5 6
     * 7 8 9 10
     *
     * 20
     * 16 19
     * 12 14 16
     * 7   8  9 10
     * @param i
     * @param j
     * @return
     */
    public int getMaxDt(int i, int j) {
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        if (i==n) {
            return arr[i][j];
        }
        //把dp最下面一层填好
        for (int k = 0; k <= n; k++) {
            dp[n][k] = arr[n][k];
        }

        for (int k = n-1; k >= 0; k--){
            for (int m = 0; m <= k; m++) {
                dp[k][m] = Math.max(dp[k+1][m],dp[k+1][m+1]) + arr[k][m];
            }
        }
        return dp[i][j];
    }

    /**
     * 递推式动态规划
     * 只需保存一行数据，空间优化，时间复杂度不变
     * 1
     * 2 3
     * 4 5 6
     * 7 8 9 10
     *
     * 20
     * 16 19
     * 12 14 16
     * 7   8  9 10
     *
     * 20 19 16 10
     * @param i
     * @param j
     * @return
     */
    public int getMaxDtPro(int i, int j) {
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        if (i==n) {
            return arr[i][j];
        }
        //把dp最下面一层填好
        for (int k = 0; k <= n; k++) {
            dp[n][k] = arr[n][k];
        }

        for (int k = n-1; k >= 0; k--){
            for (int m = 0; m <= k; m++) {
                dpPro[m] = Math.max(dp[k+1][m],dp[k+1][m+1]) + arr[k][m];
            }
        }
        return dp[i][j];
    }

    public static void main(String[] args) {
        int[][] arr = {{2},{2,3},{4,5,6},{7,8,9,11}};
        int[][] dp = {{-1},{-1,-1},{-1,-1,-1},{-1,-1,-1,-1}};
        int[] dpPro = {-1,-1,-1,-1};
        FindLongestSumInTriangle findLongestSumInTriangle = new FindLongestSumInTriangle(arr, 3, dp, dpPro);
        int max = findLongestSumInTriangle.getMax(0, 0);
        int maxDp = findLongestSumInTriangle.getMaxDp(0, 0);
        int maxDt = findLongestSumInTriangle.getMaxDt(0, 0);
        int maxDtPro = findLongestSumInTriangle.getMaxDtPro(0, 0);
        System.out.println("递归："+max);
        System.out.println("递归+dp："+maxDp);
        System.out.println("递推+dp："+maxDt);
        System.out.println("递推+dp+空间优化："+maxDtPro);
    }
}
