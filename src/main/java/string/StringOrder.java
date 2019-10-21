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
            //第一个位置上的数与其他数进行交换
            char temp = chars[i];
            chars[i] = chars[pos];
            chars[pos] = temp;
            //第2位置开始的所有排列全部找出
            permutation(chars, pos+1, list);
            //每交换完一次需要复位，然后做另一次交换
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
