package arithmetic.array;

/**
 * @Description: 两个只出现一次的数字，其他数字都出现2次
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
     * 其它数出现3次的数组中找出出现一次的数
     * 使用一个32位的数组用来存储表示数组中每一个数的二进制表达式各位上值相加的结果，然后判断这个32位数组
     * 每个位置上的值是不是3的倍数
     * @return
     */
    public Integer findOnceNumFromOther3(int[] arr){
        if (arr == null || arr.length < 4){
            return null;
        }
        int res = 0;
        int[] bitArr = new int[32];
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < bitArr.length; j++) {
                //挨个把值转换成二进制存入数组
                if ((arr[i] & (1<<j)) != 0){
                    bitArr[j]++;
                }
            }
        }

        for (int i = 0; i < bitArr.length; i++) {
            //该二进制位置上存在出现一次的数，该位置二进制置为1
            if (bitArr[i]%3 != 0) {
                //该位置二进制置为1。0 | 任何数 = 任何数本身
                res |= (1<<i);
            }
        }

        return res;
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


    public static void main(String[] args) {
        Integer onceNumFromOther3 = new OnceNum().findOnceNumFromOther3(new int[]{2, 2, 2, 1, 3, 3, 3,4 ,4,4,5,5,5});
        System.out.println(onceNumFromOther3);
    }

}
