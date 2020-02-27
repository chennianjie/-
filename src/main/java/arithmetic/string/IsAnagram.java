package arithmetic.string;

/**
 * @Description: 有效的字母异位词
 * @Author: nianjie.chen
 * @Date: 2/27/2020
 */
public class IsAnagram {

    /**
     * 值s和t字符串是由相同的字符组成只是顺序不同
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()){
            return false;
        }
        int[] az = new int[26];
        for (int i = 0; i < s.length(); i++){
            az[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++){
            if (--az[t.charAt(i) - 'a'] < 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        boolean anagram = new IsAnagram().isAnagram("asax", "saxa");
        System.out.println(anagram);
    }
}
