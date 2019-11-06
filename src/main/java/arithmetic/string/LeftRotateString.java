package arithmetic.string;

/**
 * @Description: 左位移字符串
 * @Author: nianjie.chen
 * @Date: 11/5/2019
 */
public class LeftRotateString {

    public String LeftRotateString(String str,int n) {
        if (str == null) {
            return null;
        }

        if (str.length() == 1) {
            return str;
        }

        n = n % str.length();//位移的有效次数
        String s = str.substring(0, n);
        str = str.substring(n) + s;
        return str;
    }


    public static void main(String[] args) {
        String s = "6";
        LeftRotateString leftRotateString = new LeftRotateString();
        String s1 = leftRotateString.LeftRotateString(s, 1);
        String s2 = leftRotateString.LeftRotateString(s, 3);
        System.out.println(s1 + " " + s2);
    }
}
