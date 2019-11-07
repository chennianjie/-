package arithmetic;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 11/7/2019
 */
public class AAddB {

    /**
     * @param num1: An integer
     * @param num2: An integer
     * @return: The sum of a and b
     */
    public int aplusb(int num1, int num2) {
        // write your code here
        int _a,_b;
        while(num2!=0){
            _a = num1 ^ num2;//不进位相加
            _b = (num1 & num2) << 1;//进位位置
            num1 = _a;
            num2 = _b;
        }
        return num1;
    }

}
