package arithmetic.array;

/**
 * @Description: 从数组中找出重复出现的一个数
 * @Author: nianjie.chen
 * @Date: 11/8/2019
 */
public class DuplicateNumber {

    /**
     *
     * @param numbers 原始数组
     * @param length  数组最大值
     * @param duplication 重复数字数组
     * @return
     */
    public boolean duplicate(int numbers[], int length, int[] duplication) {
        //生成标记重复数字位置的重复数组（原始数组最大值长度的数组）
        boolean[] k = new boolean[length];
        for (int i = 0; i < k.length; i++) {
            //再次为true时说明遇到重复值的情况
            if (k[numbers[i]] == true) {
                duplication[0] = numbers[i];
                return true;
            }
            //当前值对应下标的地方标记为true
            k[numbers[i]] = true;
        }
        return false;
    }
}
