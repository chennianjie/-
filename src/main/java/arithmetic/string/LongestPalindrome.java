package arithmetic.string;

/**
 * @Description: 最长回文字符串
 * @Author: nianjie.chen
 * @Date: 1/10/2020
 */
public class LongestPalindrome {


    /***
     * 暴力解，直接遍历检测每一个可能存在的情况
     * 时间复杂度：O（n^3）
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
        if (str.length() == 1){
            return str;
        }
        int curStart = -1;
        int curEnd = -1;
        int currMaxStart = -1;
        int currMaxEnd = -1;
        int currLen = Integer.MIN_VALUE;
        int currMaxLen = Integer.MIN_VALUE;
        for (int i = 0; i < str.length(); i++) {
            for (int j = i; j < str.length(); j++){
                if (isPalindrome(str, i, j)) {
                    curStart = i;
                    curEnd = j;
                    currLen = j - i + 1;
                }
            }
            if (currLen > currMaxLen) {
                currMaxLen = currLen;
                currMaxStart = curStart;
                currMaxEnd = curEnd;
            }
        }
        return str.substring(currMaxStart, currMaxEnd + 1);
    }

    private boolean isPalindrome(String str, int start, int end) {
        for (int i = start; i <= (end + start)/2; i++){
            if (str.charAt(i) != str.charAt(end - i + start)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LongestPalindrome longestPalindrome = new LongestPalindrome();
        System.out.println(longestPalindrome.longestPalindrome("addadddd"));
//        longestPalindrome.isPalindrome("ddadddd", 0, 7);
    }
}
