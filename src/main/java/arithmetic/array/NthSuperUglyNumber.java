package arithmetic.array;

/**
 * @Description: 超级丑数
 * 超级丑数是指其所有质因数都是长度为 k 的质数列表 primes 中的正整数。
 * 输入: n = 12, primes = [2,7,13,19]
 * 输出: 32
 * 前12个超级丑数：[1,2,4,7,8,13,14,16,19,26,28,32]
 * @Author: nianjie.chen
 * @Date: 1/8/2020
 */
public class NthSuperUglyNumber {

    /**
     * 下一个丑数等于它前面的丑数数乘以2，3，5的乘积取最小的值
     * @param n
     * @param primes
     * @return
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] superUglyNum = new int[n];
        //初始化第一个超级丑数
        superUglyNum[0] = 1;
        //生成primes数组对应的辅助数组
        int[] helpPrimes = new int[primes.length];
        //丑数数组对应的索引下标
        int index = 1;
        int minHelp;
        while (n != 1) {
            minHelp = primes[0] * superUglyNum[helpPrimes[0]];
            //求最小值
            for (int i = 1; i < primes.length; i++) {
                minHelp = Math.min(minHelp, primes[i] * superUglyNum[helpPrimes[i]]);
            }
            for (int i = 0; i < primes.length; i++) {
                if (minHelp == primes[i] * superUglyNum[helpPrimes[i]]) {
                    helpPrimes[i]++;
                }
            }
            superUglyNum[index++] = minHelp;
            n--;
        }
        return superUglyNum[index - 1];
    }

    public static void main(String[] args) {
        int i = new NthSuperUglyNumber().nthSuperUglyNumber(12, new int[]{2, 7, 13, 19});
        System.out.println(i);
    }
}
