package arithmetic.string;


import java.util.HashMap;

/**
 * 暴力思路：每次遍历一个字符，就和他后面的字符比较，如果没有相等的，就满足条件，时间复杂度O(n^2)
 * 空间换时间：准备hashmap容器，key存值，value存次数，遍历第一遍往hashmap中存值，第二遍验证满足条件的值即为答案
 * @Description: 字符串第一个只出现一次的字符
 * @Author: nianjie.chen
 * @Date: 10/31/2019
 */
public class FirstNotRepeatingChar {

    public int FirstNotRepeatingChar(String str) {

        if (str == null) {
            return -1;
        }
        char[] chars = str.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        Integer integer;
        for (char a : chars) {
            integer = map.get(a);
            if (integer != null) {
                map.put(a, ++integer);
            }else {
                map.put(a, 1);
            }
        }
        for (int i = 0; i < chars.length; i++) {
            if (map.get(chars[i]) == 1) {
                return i;
            }
        }



        return -1;
    }


    public static void main(String[] args) {
        int sadws = new FirstNotRepeatingChar().FirstNotRepeatingChar("google");
        System.out.println(sadws);
    }
}
