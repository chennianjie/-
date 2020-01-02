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

    public static void main(String[] args) {
        LongestValidParentheses test = new LongestValidParentheses();
        String s1 = "()()))";
        String s2 = "(())()()";
        System.out.println(test.longestValidParentheses(s1));
        System.out.println(test.longestValidParentheses(s2));
    }
}
