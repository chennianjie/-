package arithmetic.array;

/**
 * @Description:
 * 找0和1相同数量的最长连续子数组的长度
 * 1.暴力解，遍历每一个子数组，找出最长连续子数组
 * @Author: nianjie.chen
 * @Date: 1/16/2020
 */
public class FindMaxLength {

    /**
     * 遍历数组，遇到1就count+1,遇到0就count-1
     * 使用一个辅助数组记录count最早出现的index,下次再遇到相同的count则说明这段子数组中0和1的数量相同
     * 使用一个变量存储此时的距离并不断求出最大值
     * @param arr
     * @return
     */
    public int findMaxLength(int[] arr) {
        return 0;
    }
}
