package fbonacci;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）
 * 分析：跳上一级台阶有一种跳法，跳上二级台阶有两种跳法，跳上n'级台阶有f(n-1) + f(n-2)中跳法，因为可以跳一步或者两步跳上n级台阶
 */
public class JumpFloor {

    public static int jumpFloor(int target) {
        if (target == 1) {
            return 1;
        }
        if (target == 2) {
            return 2;
        }
        return jumpFloor(target - 1) + jumpFloor(target - 2);
    }

    public static void main(String[] args) {
        int i = JumpFloor.jumpFloor(5);
        System.out.println(i);
    }

}
