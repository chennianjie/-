package arithmetic.fbonacci;

/**
 * 1 1 2 3 5 8 13
 * 输入一个整数n，请你输出斐波那契数列的第n项
 */
public class FibonacciFind {

    public static int n2 = 0;
    public static int n1 = 0;
    public static int n3 = 0;

    /**
     * 优化后的递归函数
     * @param a
     * @param b
     * @param n
     * @return
     */
    public static int fibonac(int a,int b,int n)
    {
        n2 ++;
        if(n > 2)
        {
            return fibonac(a+b,a,n-1);
        }
        return a;
    }


    /**
     * 利用递归的方法
     * @param n
     * @return
     */
    public static int fibonacci(int n) {
        n1++;
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
        n3++;
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
        int fibonac = FibonacciFind.fibonac(1, 1, 4);
        System.out.println(fibonacci);
        System.out.println(fibonacci2);
        System.out.println(fibonac);
        System.out.println(n1 + " " + n2 + " " + n3);
    }
}
