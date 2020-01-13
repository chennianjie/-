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


    /**
     *
     * 竖式相乘改进版
     * num1位数N   num2位数M  num1*num2位数=M+N
     * 使用一个数组存储res[M+N]
     * num1 index-->i
     * num2 index-->j
     * num1[i]*num2[j]=(0x,xy两种情况)  y第二位为res[i+j+1]的值，x第一位为res[i+j]的值
     * @param num1
     * @param num2
     * @return
     */
    public String multiplyPro(String num1, String num2) {
        //输入检验
        if (num1 == null || num2 == null){
            return null;
        }
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        //构建res数组
        int[] res = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >=0; i--){
            int n1 = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                int sum = (res[i + j + 1] + n1 * n2);
                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        for (int i = 01; i < res.length; i++) {
            if (res[i] != 0) {
                flag = true;
            }
            if (flag) {
                sb.append(res[i]);
            }
        }

        //转换格式返回
        return sb.toString();

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
        String s1 = new TwoNumMul().multiplyPro("123", "231");
        System.out.println(s);
        System.out.println(s1);
        System.out.println(123*231);
    }

}
