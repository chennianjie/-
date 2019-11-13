package arithmetic.string;

/**
 * @Description: 表示数值的字符串
 * @Author: nianjie.chen
 * @Date: 11/13/2019
 */
public class IsNumberic {


    /**
     * 逐一判断
     * 当前位置的值为“-/+”或“e/E”或为“.”三种情况
     * @param str
     * @return
     */
    public boolean isNumeric(char[] str) {
        //定义三个变量标记是否出现过符号、小数点、e/E
        boolean hasSign = false, hasDecomal = false, hasE = false;
        for (int i = 0; i < str.length; i++) {
            //当前数如果是e/E的时候
            if (str[i] == 'e' || str[i] == 'E') {
                //‘e’后面一定要接数字，且不能出现两个‘e’
                if (i == str.length - 1 || hasE) {
                    return false;
                }
                hasE = true;
            //当前数是“-/+“的时候
            } else if (str[i] == '-' || str[i] == '+'){
                //第二次出现符号的时候，一定是在e的后面，且不是最后一位
                if (hasSign && ((i == (str.length - 1)) || (str[i-1] != 'e' && str[i-1] != 'E'))) {
                    return false;
                }
                //第一次出现符号时候，且不是在开头，则必须在e的后面
                if (!hasSign && i > 0 && str[i-1] != 'e' && str[i-1] != 'E') {
                    return false;
                }
                hasSign = true;
            //当前数是”.“的时候，e后面必须是整数且不能出现两次”.“
            }else if (str[i] == '.') {
                if (hasDecomal || hasE) {
                    return false;
                }
                hasDecomal = true;
            //其中有不合法数字的时候
            }else if (str[i] < '0' || str[i] > '9') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[] str = "123.45e+6".toCharArray();
        str = "-1E-16".toCharArray();
        boolean numeric = new IsNumberic().isNumeric(str);
        System.out.println(numeric);
    }
}
