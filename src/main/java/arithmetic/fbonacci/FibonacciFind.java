package arithmetic.fbonacci;

/**
 * 输入一个整数n，请你输出斐波那契数列的第n项
 */
public class FibonacciFind {

    /**
     * 利用递归的方法
     * @param n
     * @return
     */
    public static int fibonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 1;
        }
        return fibonacci(n-1) + fibonacci(n-2);
    }

    public static int fibonacci2(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 1;
        }
        int firstNum = 1, nextNum = 1;
        int curNum = 0;
        for (int i = 3; i <= n; i++) {
            curNum = firstNum + nextNum;
            firstNum = nextNum;
            nextNum = curNum;
        }
        return curNum;
    }

    public static void main(String[] args) {
        int fibonacci = FibonacciFind.fibonacci(4);
        int fibonacci2 = FibonacciFind.fibonacci2(4);
        System.out.println(fibonacci);
        System.out.println(fibonacci2);
    }
}
