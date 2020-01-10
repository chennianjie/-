package arithmetic.string;

/**
 * @Description: 最长回文字符串
 * @Author: nianjie.chen
 * @Date: 1/10/2020
 */
public class LongestPalindrome {


    /***
     * 暴力解，直接遍历检测每一个可能存在的情况
     * 时间复杂度：O（n*2）
     * 空间复杂度：O（1）
     * @param str
     * @return
     */
    public String longestPalindrome(String str){
        if ("".equals(str)){
            return  "";
        }
        if (str == null){
            return null;
        }
        String temp;
        String currRes = null;
        String res = null;
        int currLen = Integer.MIN_VALUE;
        int currMaxLen = Integer.MIN_VALUE;
        for (int i = 0; i < str.length(); i++) {
            for (int j = i; j < str.length(); j++){
                temp = str.substring(i, j+1);
                if (isPalindrome(temp)) {
                    currRes = temp;
                    currLen = j - i + 1;
                }
            }
            if (currLen > currMaxLen) {
                currMaxLen = currLen;
                res = currRes;
            }
        }
        return res;
    }

    private boolean isPalindrome(String str) {
        for (int i = 0; i < str.length()/2; i++){
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LongestPalindrome longestPalindrome = new LongestPalindrome();
        System.out.println(longestPalindrome.isPalindrome("adda"));
        System.out.println(longestPalindrome.longestPalindrome("addadddd"));
    }
}
