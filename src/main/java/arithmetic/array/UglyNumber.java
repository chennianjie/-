package arithmetic.array;

/** 质因子只包含2，3，5这三个数的数就是丑数
 * 寻找第n个丑数
 * 规定1为丑数，其他数如果是1，2，3，5这三个数相乘得来即是丑数
 * @Description:
 * @Author: nianjie.chen
 * @Date: 10/31/2019
 */

public class UglyNumber {


    public int uglyNumber1(int n) {
        int number = 1;
        while (true) {
            if (check(number)){
                n--;
                if (n == 0){
                    break;
                }
            }
            number++;
        }
        return number;
    }

    public boolean check(int number){
        while (number%2 == 0) {
            number = number/2;
        }
        while (number%3 == 0) {
            number = number/3;
        }
        while (number%5 == 0) {
            number = number/5;
        }
        return number == 1;
    }

    /**
     * 发现规律：下一个丑数等于它前面的丑数数乘以2，3，5的乘积取最小的值
     * 丑数数组是从小到大排序好的
     *
     * @return
     */
    public int uglyNumber2(int n){
        if (n <= 6) {
            return n;
        }
        //创建一个数组存储所有丑数，用于计算之后丑数
        int[] uglyArr = new int[n];
        uglyArr[0] = 1;
        int i2 = 0;//乘2的索引
        int i3 = 0;//乘3
        int i5 = 0;//乘5
        int index = 1;
        while (n != 1) {
            //使用丑数数组中最小的值与2，3，5取乘积并记录其最小值作为下一个丑数存入丑数数组
            //然后本次最小丑数对应的2，3，5中某一个数与下一次和丑数数组的乘积时，丑数数组的索引变到下一个
            uglyArr[index] = Math.min(uglyArr[i2] * 2, Math.min(uglyArr[i3] * 3, uglyArr[i5] * 5));
            if (uglyArr[index] == uglyArr[i2] * 2) {
                i2++;
            }
            if (uglyArr[index] == uglyArr[i3] * 3) {
                i3++;
            }
            if (uglyArr[index] == uglyArr[i5] * 5) {
                i5++;
            }
            index++;
            n--;
        }
        return uglyArr[index-1];
    }

    public static void main(String[] args) {
        UglyNumber uglyNumber = new UglyNumber();
        System.out.println(uglyNumber.uglyNumber1(8));
        System.out.println(uglyNumber.uglyNumber2(8));
    }
}
