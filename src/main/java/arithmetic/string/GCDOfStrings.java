package arithmetic.string;

/**
 * @Description:
 *
 * 求两个字符串的最大公约数
 * @Author: nianjie.chen
 * @Date: 1/6/2020
 */
public class GCDOfStrings {

    public String gcdOfStrings(String str1, String str2) {
        //str1 + str2 == str2 + str1   <===> 有最大公约数   （充要条件）
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }
        int max = pubMax(str1.length(), str2.length());
        return (str1 + str2).substring(0,max);
    }

    private int pubMax(int a, int b) {
        while (a != 0 && b != 0) {
            a = a % b;
            if (a == 0) {
                break;
            }
            //a,b交换
            int c = a;
            a = b;
            b = c;
        }
        return b;
    }

    public static void main(String[] args) {
        String s = new GCDOfStrings().gcdOfStrings("ABCABC", "ABC");
        System.out.println(s);
    }
}
