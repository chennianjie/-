package arithmetic.string;

/**
 * @Description: 反转字符串
 * @Author: nianjie.chen
 * @Date: 11/5/2019
 */
public class ReverseSentence {

    public String ReverseSentence(String str) {

        if (str == null) {
            return null;
        }

        String[] s = str.split(" ");

        if (s.length <= 1) {
            return str;
        }
        int start = 0;
        int end = s.length - 1;

        int mid = (end + start) >> 1;
        String help = "";
        while (start <= mid && end >= mid) {
            help = s[start];
            s[start] = s[end];
            s[end] = help;
            start++;
            end--;
        }
        StringBuilder sb = new StringBuilder();
        for (String s1 : s) {
            sb.append(s1 + " ");
        }
        String trim = sb.toString().trim();
        return trim;
    }

    public static void main(String[] args) {
        String s = "i am a student";
        String s1 = new ReverseSentence().ReverseSentence(s);
        System.out.println(s1);
    }
}
