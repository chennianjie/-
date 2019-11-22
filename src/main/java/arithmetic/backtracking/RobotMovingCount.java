package arithmetic.backtracking;

/**
 * 回溯法
 * 当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为3+5+3+8 = 19
 * @Description: 机器人最多可以运动多少步
 * @Author: nianjie.chen
 * @Date: 11/22/2019
 */
public class RobotMovingCount {


    /**
     *
     * @param threshold 阈值
     * @param rows 方格行数
     * @param cols 方格列数
     * @return
     */
    public int movingCount(int threshold, int rows, int cols)
    {
        if (threshold < 0 || rows < 0 || cols < 0) {
            return -1;
        }
        //标记数组，走过的格子不能重复走
        boolean[] flag = new boolean[rows*cols];
        //从（0，0）点开始走
        return movingCount(threshold, rows, cols, 0, 0, flag);
    }

    /**
     * moving
     * @param threshold
     * @param rows
     * @param cols
     * @param col
     * @param row
     * @param flag
     */
    private int  movingCount(int threshold, int rows, int cols, int col, int row, boolean[] flag) {
        int count = 0;
        //当前（row,col）在数组中对应的位置
        int index = row*cols + col;
        //满足条件才能继续
        if (col >= 0 && row >= 0 && col < cols && row < rows && (digitSum(col) + digitSum(row)) <= threshold && !flag[index]) {
            flag[index] = true;
            count = 1 + movingCount(threshold, rows, cols, col + 1, row, flag)
                      + movingCount(threshold, rows, cols, col, row + 1, flag)
                      + movingCount(threshold, rows, cols, col - 1, row, flag)
                      + movingCount(threshold, rows, cols, col, row - 1, flag);
        }

        return count;
    }



    /**
	递归回溯方法：
	@param threshold	约束值
	@param rows			方格行数
	@param cols			方格列数
	@param row			当前处理的行号
	@param col			当前处理的列号
	@param visted		访问标记数组
	@return				最多可走的方格
	*/
    public int movingCountCore(int threshold,int rows,int cols,int row,int col,boolean[] visted){
        int count = 0;
        if(check(threshold,rows,cols,row,col,visted)){
            visted[row*cols + col] = true;

            count = 1 + movingCountCore(threshold,rows,cols,row - 1,col,visted) +
                    movingCountCore(threshold,rows,cols,row,col - 1,visted) +
                    movingCountCore(threshold,rows,cols,row + 1,col,visted) +
                    movingCountCore(threshold,rows,cols,row,col + 1,visted);
        }
        return count;
    }

    boolean check(int threshold,int rows,int cols,int row,int col,boolean[] visted){
        if(row >= 0 && row < rows && col >= 0 && col < cols
                && (digitSum(row) + digitSum(col) <= threshold)
                && !visted[row* cols + col])
            return true;
        return false;
    }




    /**
     * 数字所有位上的值之和
     * eg:123 ---> 1+2+3 = 6
     * @param num
     * @return
     */
    private int digitSum(int num) {
        int sum = 0;
        while(num > 0){
            sum += num%10;
            num /= 10;
        }
        return sum;

    }

    public static void main(String[] args) {
        int i = new RobotMovingCount().movingCount(5, 10, 10);
        System.out.println(i);
    }
}
