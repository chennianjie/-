package arithmetic.bit_operation;

/**
 * 找寻一个十进制数的二进制表达形式中包含1的个数
 *
 * 思路：在当前数不为0的情况下，当前数减一之后与原来数进行&运算，得到下一个1所在位置
 * 思路2：将此数变成二进制字符串再变成字符数组，进而与‘1’进行对比，依此得到1的个数
 *
 * 例如：1100      减一1011     1100 & 1011 = 1000依此类推
 */
public class NumberOf1 {

    public int NumberOf1(int n) {
        int count = 0;
        while (n!=0){
            count++;
            n = n & (n - 1);
        }
        return count;
    }
}
