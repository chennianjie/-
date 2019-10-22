package arithmetic.array;

/**
 * @Description:
 * 找出数组中超过数组一半长度的那个数字
 * （数组中只存在一个这样的数字）
 * 思路：遍历数组，定义两个变量，一个用于保存当前结果，一个保存当前结果值出现的次数
 *      规则：当前次数为0则换下一个值，并把次数设置为1；当前次数为0，下一个值与当前结果值相等，次数++；与当前结果值不相等，次数--；
 * @Author: nianjie.chen
 * @Date: 10/21/2019
 */
public class OverHalfLengthNum {

    public int MoreThanHalfNum_Solution(int [] array) {
        int result = array[0];
        int times = 1;

        for (int i = 1; i < array.length; i++) {
            if (times == 0) {
                result = array[i];
                times = 1;
            }else if (result == array[i]) {
                times++;
            }else {
                times--;
            }
        }

        times = 0;
        for (int i = 0; i < array.length; i++) {
            if (result == array[i]) {
                times++;
            }
        }
        if (times > array.length/2) {
            return result;
        }else {
            return 0;
        }
    }
}
