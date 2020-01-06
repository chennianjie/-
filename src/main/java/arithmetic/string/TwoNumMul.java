package arithmetic.string;


/**
 * @Description: 两个字符串相乘
 *
 *  num1 和 num2 的长度小于110。
    num1 和 num2 只包含数字 0-9。
    num1 和 num2 均不以零开头，除非是数字 0 本身。
    不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 * @Author: nianjie.chen
 * @Date: 1/6/2020
 */
public class TwoNumMul {

    /**
     * 乘法步骤
     *   123
     * x 352
     * ------
     *    246
     *   6150
     * +36900
     * ------
     * result
     *
     *
     * 实际运算
     *   324  num1
     * x  25  num2
     * ------
     *  0261
     *  0846
     *
     *  因为append方法只能从右向左拼接，所以需要num2中每个数从右向左挨个乘num1，然后再reverse
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        String res = "0";
        //num1 x num2,n;um2中每个位置上的数与num1的乘积之和
        for (int i = num2.length() - 1; i >= 0; i--) {
            //i位置上数的十进制表达值
            int a = num2.charAt(i) - '0';
            StringBuilder temp = new StringBuilder();
            //补多少个0
            int countOfZero = num2.length() - 1 - i;
            while (countOfZero != 0) {
                temp.append("0");
                countOfZero--;
            }
            //单个相乘之后的进位
            int carry = 0;
            for (int j = num1.length() - 1; j >= 0 || carry != 0; j--) {
                int b = j < 0 ? 0 : num1.charAt(j) - '0';
                int mul = a * b + carry;
                int currb = mul % 10;
                temp.append(currb);
                carry = mul / 10;
            }
            //temp取反相加求值
            res = addStrings(res, temp.reverse().toString());

        }
        return res;
    }

    private String addStrings (String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for (int i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j>= 0 || carry != 0; i--,j--) {
            int x = i < 0 ? 0 : num1.charAt(i) - '0';
            int y = j < 0 ? 0 : num2.charAt(j) - '0';
            int sum = x + y + carry;
            sb.append(sum%10);
            carry = sum / 10;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        String s = new TwoNumMul().multiply("123", "231");
        System.out.println(s);
        System.out.println(123*231);
    }

}
