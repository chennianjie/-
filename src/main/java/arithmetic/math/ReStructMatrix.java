package arithmetic.math;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 重构2行二维矩阵
 * @Author: nianjie.chen
 * @Date: 1/20/2020
 */
public class ReStructMatrix {

    /**
     * 贪心算法
     * 求出满足参数所指2行n列的数组
     *
     * 1 <= colsum.length <= 10^5
     * 0 <= upper, lower <= colsum.length
     * 0 <= colsum[i] <= 2
     * @param upper 第一行和
     * @param lower 第二行和
     * @param colsum colsum[i] 指i列的和
     * @return
     */
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        // up记录第0行可分配的1个数，lo记录第1行可分配的1个数
        int up = upper, lo = lower, sum = 0, len = colsum.length;
        List<List<Integer>> list = new ArrayList<>();
        for(int i = 0; i < len; i ++){
            if(colsum[i] == 2){
                up --;
                lo --;
            }
            else if(colsum[i] == 1){
                sum++;
            }
        }
        // 如果行列元素之和不相等，或行元素之和不够分配
        if(up + lo != sum || up < 0 || lo < 0){
            return list;
        }
        List<Integer> upl = new ArrayList<>();
        List<Integer> lol = new ArrayList<>();
        for(int i = 0; i < len; i ++){
            if(colsum[i] == 2){
                upl.add(1);
                lol.add(1);
            }
            else if(colsum[i] == 0){
                upl.add(0);
                lol.add(0);
            }
            else {
                // 先分配上
                if(up-- > 0){
                    upl.add(1);
                    lol.add(0);
                }
                // 再分配下
                else {
                    lol.add(1);
                    upl.add(0);
                }
            }
        }
        list.add(upl);
        list.add(lol);
        return list;
    }
}
