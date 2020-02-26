package arithmetic.dynamic_plan;

/**
 * @Description: 最大礼物价值
 *
 *   [1,3,1]
 *   [1,5,1]
 *   [4,2,1]
 *
 *   路径 1→3→5→2→1    12
 * @Author: nianjie.chen
 * @Date: 2/25/2020
 */
public class MaxValue {

    private int[][] dp;
    private int endR;
    private int endL;
    int max = 0;

    public int maxValue(int[][] grid){
        endR = grid.length - 1;
        endL = grid[0].length - 1;
        if (grid == null || grid.length == 0){
            return 0;
        }
        dp = new int[grid.length][grid[0].length];
        maxValue(0, 0, grid, 0);
        return max;
    }

    private void maxValue(int curR, int curL, int[][] grid, int sum){
        if (curL > endL){
            return;
        }
        if (curR > endR){
            return;
        }
        if (curL == endL && curR == endR){
            max = Math.max(max, sum + grid[curR][curL]);
        }
        maxValue(curR + 1, curL, grid, sum + grid[curR][curL]);
        maxValue(curR, curL + 1, grid, sum + grid[curR][curL]);
    }


    /**
     * 预处理+动态规划
     * @param grid
     * @return
     */
    public int maxValue2(int[][] grid) {
        //第一行元素做前缀和
        for(int a = 1;a<grid[0].length;a++) {
            grid[0][a] = grid[0][a-1] + grid[0][a];
        }
        //第一列元素做前缀和
        for(int b = 1;b<grid.length;b++) {
            grid[b][0] = grid[b-1][0] + grid[b][0];
        }
        //剩余元素选择左边和上边元素中较大者做前缀和
        for(int i = 1;i<grid.length;i++) {
            for(int j = 1;j<grid[0].length;j++) {
                grid[i][j] = (grid[i-1][j]>grid[i][j-1] ? grid[i-1][j] : grid[i][j-1]) + grid[i][j];
            }
        }
        //数组矩阵右下角的元素就是最大值，返回它即可
        return grid[grid.length-1][grid[0].length-1];
    }


    public static void main(String[] args) {
        int i = new MaxValue().maxValue(new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        });
        System.out.println(i);
    }
}
