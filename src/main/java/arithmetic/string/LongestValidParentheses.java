package arithmetic.string;

import java.util.Stack;

/**
 * 最长有效括弧//todo 动态规划和栈和辅助变量解
 * eg:'()()))' ---> 4
 *    '(())()' ---> 8
 * @Description:
 * @Author: nianjie.chen
 * @Date: 1/2/2020
 */
public class LongestValidParentheses {


    /**
     * 解法一：暴力解
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push('(');
            } else if (!stack.empty() && stack.peek() == '(') {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.empty();
    }
    public int longestValidParentheses(String s) {
        int maxlen = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 2; j <= s.length(); j+=2) {
                if (isValid(s.substring(i, j))) {
                    maxlen = Math.max(maxlen, j - i);
                }
            }
        }
        return maxlen;
    }


    /**
     * 解法二：使用变量代替栈的方法
     * 设置一个left和right变量分别记录字符串中“（”和“）”的个数
     * 规则：从左向右遍历，出现“（”，left++，出现“）”，right++，当left==right更新最大值，right>left时重置left=right=0
     * 然后按照此方式再从右往左遍历，进而统计出最大值
     * @param s
     * @return
     */
    public int longestValidParentheses2(String s) {
        int maxlen = 0;
        int left = 0,right = 0;
        for (int i=0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            }else {
                right++;
            }
            if (left == right){
                maxlen = Math.max(maxlen, right*2);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i=s.length()-1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            }else {
                right++;
            }
            if (left == right) {
                maxlen = Math.max(maxlen, right*2);
            }else if (left > right){
                left = right = 0;
            }
        }
        return maxlen;
    }


    public static void main(String[] args) {
        LongestValidParentheses test = new LongestValidParentheses();
        String s1 = "()()))";
        String s2 = "(())()()";
        System.out.println(test.longestValidParentheses(s1));
        System.out.println(test.longestValidParentheses2(s1));
        System.out.println(test.longestValidParentheses(s2));
        System.out.println(test.longestValidParentheses2(s2));
    }
}
