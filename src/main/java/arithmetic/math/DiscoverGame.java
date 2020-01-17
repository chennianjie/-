package arithmetic.math;

/**
 * @Description:
 * 选出任一 x，满足 0 < x < N 且 N % x == 0 。
 * 用 N - x 替换黑板上的数字 N 。
 * 如果玩家无法执行这些操作，就会输掉游戏。
 *
 *
 * 数学问题：
 * 1.奇数的约数必定为奇数，偶数的约数可以是奇数也可以是偶数
 * 2.谁先等到N=2时谁就赢了
 * 3.若N为偶数，爱丽丝先手一直选1，鲍勃就只有一直选奇数，爱丽丝一直选偶数，最终N=2时爱丽丝赢
 * 4.若N为奇数，爱丽丝先手只能选奇数，N会变成偶数，鲍勃一直选1或其他奇数，最终N=2时轮到鲍勃选，鲍勃赢
 * 综上所述：爱丽丝先手情况下，N为偶数爱丽丝赢，N为奇数鲍勃赢
 * @Author: nianjie.chen
 * @Date: 1/17/2020
 */
public class DiscoverGame {

    public boolean divisorGame(int N) {
        return (N&1) == 0;
    }

    public static void main(String[] args) {
        System.out.println(new DiscoverGame().divisorGame(2));
        System.out.println(new DiscoverGame().divisorGame(3));
    }
}
