package arithmetic.array;

/**
 * B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]
 * B[i]等于除A[i]之外的所有A数组中的数的乘积
 *
 *
 * 1   a1  a2  a3    | b0
 * a0  1   a2  a3    | b1
 * a0  a1  1   a3    | b2
 * a0  a1  a2  1     | b3
 * @Description: 构建乘积数组
 * @Author: nianjie.chen
 * @Date: 11/12/2019
 */
public class MutilplyArray {

    public int[] multiply(int[] A) {
        int[] res = new int[A.length];
        res[0] = 1;
        //先计算上三角乘积
        for(int i = 1;i < A.length;i++){
            res[i] = res[i-1]*A[i-1]; //b3 = b2 * a2（如上图）
        }
        //下三角形乘积
        int temp = 1;
        //从下往上，逐步增加乘积个数，使计算结果得到复用（递推思想）
        for(int j = A.length - 2 ;j >= 0 ;j--){
            temp = temp * A[j+1];//表示a3、a3*a2、a3*a2*a1
            res[j]*=temp;
        }
        return res;
    }
}
