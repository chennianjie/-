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


    /**
     * 中心扩展法
     * 遍历每一个字符中的字母，同时左右扩大，判断其是否相等，进而判断出是否是回文
     * 特殊情况：'baac'，这个测试用例的中心就在a与a之间，考虑把所有字母变成奇数个，类似'#b#a#a#c#'
     * 时间复杂度：O（N^2）
     * 空间复杂度：O(1)
     * @param str
     * @return
     */
    public String longestPalindrome2(String str) {
        if (str == null || "".equals(str) || str.length() == 1) {
            return str;
        }
        //变成奇数个
        StringBuilder sb = new StringBuilder();
        sb.append("#");
        for(int i = 0; i < str.length(); i++){
            sb.append(str.charAt(i));
            sb.append("#");
        }
        str = sb.toString();
        StringBuilder result = new StringBuilder(str.charAt(0));
        int maxLen = 1;
        int left,right;
        StringBuilder res;
        for (int i = 1; i < str.length(); i++) {
            left = i - 1;
            right = i + 1;
            int len = 0;
            res = new StringBuilder();
            res.append(str.charAt(i));
            while (left >= 0 && right < str.length()){
                if (str.charAt(left) != str.charAt(right)){
                    break;
                }else {
                   len+=2;
                   res.insert(0, str.charAt(left));
                   res.append(str.charAt(right));
                }
                left--;
                right++;
            }
            if (len > maxLen) {
                maxLen = len;
                result = res;
            }
        }
        str = result.toString();
        str = str.replaceAll("#","");
        return str;
    }


    /**
     * 最牛逼解法
     * @param s
     * @return
     */
    public String longestPalindrome3(String s){
            if (s == null || s.length() == 0) {
                return "";
            }
            //保存起始位置，测试了用数组似乎能比全局变量稍快一点
            int[] range = new int[2];
            char[] str = s.toCharArray();
            for (int i = 0; i < s.length(); i++) {
            //把回文看成中间的部分全是同一字符，左右部分相对称
            //找到下一个与当前字符不同的字符
                i = findLongest(str, i, range);
            }
            return s.substring(range[0], range[1] + 1);
        }

        public static int findLongest(char[] str, int low, int[] range) {
            //查找中间部分
            int high = low;
            while (high < str.length - 1 && str[high + 1] == str[low]) {
                high++;
            }
            //定位中间部分的最后一个字符
            int ans = high;
            //从中间向左右扩散
            while (low > 0 && high < str.length - 1 && str[low - 1] == str[high + 1]) {
                low--;
                high++;
            }
            //记录最大长度
            if (high - low > range[1] - range[0]) {
                range[0] = low;
                range[1] = high;
            }
            return ans;
    }

    /**
     * 动态规划解法：
     * 大问题-->小问题
     * 规定：1.1表示是回文  2.dp[i][j]==1表示 i<-->j 范围内是回文
     * 状态方程为： d（i,j）==1  charAt(i)==chatAt(j) && d(i+1,j-1)==1 存在0=<i,j<s.length;i<=j
     * @param s
     * @return
     */
    public String longestPalindrome4(String s) {
        if (s == null || s.length() == 1 || "".equals(s)) {
            return s;
        }
        int[][] dp = new int[s.length()][s.length()];
        //使用两个变量存储最长回文串的开始和结束索引
        int start = 0;
        int end = 0;
        //在dp数组中初始化1个字母和2个字母回文
        for (int i = 0; i < s.length(); i++){
            dp[i][i] = 1;
            if (i+1 < s.length() && s.charAt(i) == s.charAt(i+1)){
                dp[i][i+1] = 1;
                start = i;
                end = i+1;
            }
        }
        //在dp数组中初始化字母长度为3及以上的回文
        int lastIndex;
        for (int l = 3; l <= s.length(); l++){
            for (int i = 0; l+i-1 < s.length(); i++) {
                lastIndex = l+i-1;
                if (s.charAt(i) == s.charAt(lastIndex) && dp[i+1][lastIndex-1] == 1) {
                    dp[i][lastIndex] = 1;
                    start = i;
                    end = lastIndex;
                }
            }
        }
        return s.substring(start, end + 1);
    }


    public static void main(String[] args) {
        LongestPalindrome longestPalindrome = new LongestPalindrome();
        System.out.println(longestPalindrome.longestPalindrome3("addadddd"));
        System.out.println(longestPalindrome.longestPalindrome4("adda"));
        System.out.println(longestPalindrome.longestPalindrome4(""));
        System.out.println(longestPalindrome.longestPalindrome4("aaad"));
        System.out.println(longestPalindrome.longestPalindrome4("caad"));
//        longestPalindrome.isPalindrome("ddadddd", 0, 7);
    }
}
