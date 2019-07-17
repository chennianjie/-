package arithmetic.fbonacci;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法
 */
public class JumpFloorⅡ {

    public int JumpFloor1(int target) {
        if (target == 1) {
            return 1;
        }
        if (target == 2) {
            return 2;
        }
        int result = 1;
        for (int i = 1; i < target; i++) {
            result += JumpFloor1(i);
        }
        return result;
    }

    /**
     * 每个台阶都有跳与不跳两种情况（除了最后一个台阶），最后一个台阶必须跳。所以共用2^(n-1)中情况
     * @param target
     * @return
     */
    public int JumpFloor2(int target) {

        return 1 << (target - 1);
    }

}
