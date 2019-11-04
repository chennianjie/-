package arithmetic.array;

/**
 * @Description: 两个只出现一次的数字，其他数字都出现2次
 * @Author: nianjie.chen
 * @Date: 11/4/2019
 */
public class OnceNum {

    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        if (array == null || array.length <= 1) {
            return;
        }
        int res = array[0];
        for (int i = 1; i < array.length; i++) {
            res ^= array[i];
        }
        num1[0] = res;
        num2[0] = res;
    }
}
