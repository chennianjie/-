package concurrent.forkjoin.sumarray;

/**
 * 正常数组求和
 */
public class NormalSumArray {

    public static int sum(int[] arr) {
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            result += arr[i];
        }
        return result;
    }
}
