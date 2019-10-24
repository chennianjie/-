package arithmetic.dynamic_plan.fbonacci;

/**
 * https://blog.csdn.net/jsqfengbao/article/details/47164537#commentBox  参考链接
 * 求一个double类型的数base的exponent次方
 */
public class DoublePowerNum {


    public double power(double base, int exponent){
        //数值校验，情况一：0的负次幂无意义；情况二：除情况一之外任何数的0次幂都为一；情况三：exponent为负数时求base的倒数次幂
        //校验double类型的数与某数是否相等，不能用==
        if (equal(base, 0.0) && exponent < 0) {
            System.out.println("0的负次幂无意义");
            return Double.MAX_VALUE;
        }else if (exponent == 0){
            return 1.0;
        }else if(exponent < 0){
            return powerWithExponent(1/base, -exponent);
        }else {
            return powerWithExponentPro(base, exponent);
        }

    }

    /**
     * 核心求次方方法
     * @param base
     * @param exponent
     * @return
     */
    private double powerWithExponent(double base, int exponent){
        double result = 1.0;
        for (int i = 1; i <= exponent; i++) {
            result= result*base;
        }
        return result;
    }

    /**
     * 核心求次方方法改良版本  费波纳茨
     * @param base
     * @param exponent
     * @return
     */
    private double powerWithExponentPro(double base, int exponent){

        if (exponent == 0) {
            return 1.0;
        }
        if (exponent == 1) {
            return base;
        }else {
            double result = powerWithExponentPro(base, exponent>>1);
            result*=result;
            //如果exponent是奇数则再此基础上乘以base，偶数则是此结果
            if ((exponent&0x1)==1) {
                result*=base;
            }

            return result;

        }


    }

    /**
     * 比较两个double类型的数是否相等
     * @param a
     * @param b
     * @return
     */
    public boolean equal(double a, double b){
        if (a - b < 0.0000001 && b - a < 0.0000001){
            return true;
        }else {
            return false;
        }
    }

    public static void main(String[] args) {
        DoublePowerNum doublePowerNum = new DoublePowerNum();
        double power = doublePowerNum.power(-2.0, 2);
        System.out.println(power);
    }
}
