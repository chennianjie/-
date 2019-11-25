package arithmetic.stack;

import java.util.Stack;

/**
 * @Description:
 * 利用一个辅助栈，如果当前push值小于辅助栈顶元素或辅助栈为空，把当前值push进辅助栈
 * @Author: nianjie.chen
 * @Date: 9/17/2019
 */
public class MinStack {

    private Stack<Integer> stack = new Stack();
    private Stack<Integer> helpStack = new Stack();

    public void push(int node) {
        stack.push(node);
        if (helpStack.empty()){
            helpStack.push(node);
            return;
        }
        if (node <= helpStack.peek()){
            helpStack.push(node);
        }else {
            helpStack.push(helpStack.peek());
        }
    }

    public void pop() {
        stack.pop();
        helpStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return helpStack.peek();
    }
}
