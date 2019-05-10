package queue;

import java.util.Stack;

/**
 * 问题：用两个栈实现一个队列，提供pop，push操作即可
 * 分析：准备一个push栈，一个pop栈，push操作就放入push栈，pop操作就从pop栈里面取值，每次当pop栈为空时，push栈向pop栈导入
 * 导入规则：1.pop栈为空  2.每次从push栈全部数值导入
 */
public class TwoStackToQueue {
    Stack<Integer> pushStack = new Stack<>();
    Stack<Integer> popStack = new Stack<>();

    public void push(int v){
        pushStack.push(v);
    }

    public int pop(){
        this.update();
        return popStack.pop();
    }

    public void update(){
        if (popStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }
    }
}

