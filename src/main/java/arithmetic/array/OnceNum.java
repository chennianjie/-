package arithmetic.array;

/**
 * @Description: 两个只出现一次的数字，其他数字都出现2次
 * todo 数组中出现一次的数，其他数出现n次
 * @Author: nianjie.chen
 * @Date: 11/4/2019
 */
public class OnceNum {

        public void FindNumsAppearOnce(int[] array, int[] num1, int[] num2)    {
            int length = array.length;
            if(length == 2){
                num1[0] = array[0];
                num2[0] = array[1];
                return;
            }
            int bitResult = 0;
            for(int i = 0; i < length; ++i){
                bitResult ^= array[i];
            }
            //找到第一个1的位置
            int index = findFirst1(bitResult);
            //对数组中数的二进制按第一个1所在位置进行分组
            for(int i = 0; i < length; ++i){
                if(isBit1(array[i], index)){
                    num1[0] ^= array[i];
                }else{
                    num2[0] ^= array[i];
                }
            }
        }

    /**
     * 第一个1出现的位置
     * @param bitResult
     * @return
     */
    private int findFirst1(int bitResult){
            int index = 0;
            while(((bitResult & 1) == 0) && index < 32){
                bitResult >>= 1;
                index++;
            }
            return index;
        }

    /**
     * target的二进制表达式中index位置的值是否为1
     * @param target
     * @param index
     * @return
     */
    private boolean isBit1(int target, int index){
            return ((target >> index) & 1) == 1;
        }

}
