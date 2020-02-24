package arithmetic.array;

/**
 * @Description:
 * 构建二维数组
 * 给定一个整数n，构建一个取值范围为1~n^2的沿顺时针递增的二维数组
 * eg:
 * 输入 n=3
 *
 * 输出
 *     1 2 3
 *     8 9 4
 *     7 6 5
 * @Author: nianjie.chen
 * @Date: 2/24/2020
 */
public class BuildDoubleMatrix {
    int v = 1;
    public int[][] buildMatrix(int n) {
        int col1 = 0;
        int row1 = 0;
        int col2 = n - 1;
        int row2 = col2;
        int[][] result = new int[n][n];
        while (col1 <= col2 && row1 <= row2) {
            buildMatrix(result, col1++, row1++, col2--, row2--);
        }
        return result;
    }

    private void buildMatrix(int[][] result, int col1, int row1, int col2, int row2) {
        if (col1 == col2){
            result[row1][col1] = v;
        }
        //这里不需要考虑横着和竖着单独一行，因为现行条件是一个正方形矩阵
        //顺时针旋转给矩阵赋值(右下左上)
        int curL = col1;
        int curR = row1;
        while (curL != col2){
            result[row1][curL++] = v++;
        }
        while (curR != row2){
            result[curR++][col2] = v++;
        }
        while (curL != col1){
            result[row2][curL--] = v++;
        }
        while (curR != row1){
            result[curR--][col1] = v++;
        }
    }

    public static void main(String[] args) {
        int[][] ints = new BuildDoubleMatrix().buildMatrix(3);
        for (int[] a : ints){
            for (int b : a){
                System.out.print(b + " ");
            }
            System.out.println();
        }
    }
}
