package arithmetic.math;

/**
 * @Description: 分糖果
 *
 * 每个孩子至少分配到 1 个糖果。 相邻的孩子中，评分高的孩子必须获得更多的糖果
 * @Author: nianjie.chen
 * @Date: 1/20/2020
 */
public class Candy {

    /**
     * 1.相邻评分高糖果得数多，评分低则相反
     * 2.相邻评分相同糖果数无要求
     * 3.每个孩子必须得到一个糖果
     *
     * 思路：使用一个数组存储需要分的糖果数
     * 1.从左向右遍历，右边评分高于左边，糖果数在左边的基础上+1，反之初始化成1
     * 2.从右向左遍历，左边评分高于右边且糖果左边小于等于右边，左边在右边的基础上+1
     * @param ratings [1,2,1]
     * @return 4
     */
    public int candy(int[] ratings) {
        if (ratings == null){
            return 0;
        }
        if (ratings.length == 1){
            return 1;
        }
        int[] candy = new int[ratings.length];
        candy[0] = 1;
        for (int i = 1; i < candy.length; i++) {
            if (ratings[i] > ratings[i-1]){
                candy[i] = candy[i-1] + 1;
            }else {
                candy[i] = 1;
            }
        }
        for (int i = ratings.length - 1; i > 0; i--) {
            //评分不等，糖果不能一样
            if (ratings[i-1] > ratings[i] && candy[i-1] <= candy[i]) {
                candy[i-1] = candy[i] + 1;
            }
        }
        int sum = 0;
        for (int i : candy) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new Candy().candy(new int[]{1,0,2}));
        System.out.println(new Candy().candy(new int[]{1,2,2}));
    }
}
