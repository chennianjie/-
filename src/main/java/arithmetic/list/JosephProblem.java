package arithmetic.list;

import java.util.LinkedList;

/**
 * //todo 约瑟夫环的递推公式解法
 * @Description: 约瑟夫环问题
 * @Author: nianjie.chen
 * @Date: 11/6/2019
 */
public class JosephProblem {

    /**
     * 孩子们的游戏（牛客）
     * 从0每到m时删除
     * @param n 数字的总数
     * @param m
     * @return
     */
    public int LastRemaining_Solution(int n, int m) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i ++) {
            list.add(i);
        }

        int bt = 0;
        while (list.size() > 1) {
            //每次删除的是m-1的人(核心公式)
            bt = (bt + m - 1) % list.size();
            list.remove(bt);
        }

        return list.size() == 1 ? list.get(0) : -1;
    }
}
