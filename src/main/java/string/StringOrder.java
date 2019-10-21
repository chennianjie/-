package string;

import java.util.ArrayList;

/**
 * @Description:字符串排列
 * @Author: nianjie.chen
 * @Date: 10/21/2019
 */
public class StringOrder {

    public static ArrayList<String> Permutation(String str) {
        if(str == null) {
            return new ArrayList<String>();
        }
        ArrayList<String> list = new ArrayList<>();
        permutation(str.toCharArray(), 0 , list);
        return list;
    }

    private static void permutation(char[] chars, int pos, ArrayList<String> list) {
        if(pos == chars.length - 1){
            String s = new String(chars);
            if (!list.contains(s)){
                list.add(s);
            }
        }
        for(int i = pos; i < chars.length; i++){
            //首部字符和它后面的字符（包括自己）进行交换
            char temp = chars[i];
            chars[i] = chars[pos];
            chars[pos] = temp;
            //递归求后面的字符的排列
            permutation(chars, pos+1, list);
            //由于前面交换了一下，所以chs的内容改变了，我们要还原回来
            temp = chars[i];
            chars[i] = chars[pos];
            chars[pos] = temp;
        }
    }

    public static void main(String[] args) {
//        char[] chars = {'a','b','c'};
//        int pos = 0;
//        StringOrder.permutation(chars, pos);

        ArrayList<String> a = StringOrder.Permutation("aa");
        for (String v : a) {
            System.out.println(v);
        }
    }
}
