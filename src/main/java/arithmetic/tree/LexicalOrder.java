package arithmetic.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 字典序排数
 * 类似对10叉树的先序遍历
 * 给定一个数字n，找出以1，2，3依此开头且小于等于数字n的数
 * eg：13   -->  1 10 11 12 13 2 .... 9
 * @Author: nianjie.chen
 * @Date: 2/19/2020
 */
public class LexicalOrder {

    public List<Integer> lexicalOrder(int n) {
        if (n < 1 ){
            return null;
        }
        //使用递归求解，currentValue指的是当前数
        List<Integer> result = new ArrayList<>();
        lexicalOrder(result, null, n);
        return result;
    }

    private void lexicalOrder(List<Integer> result, Integer currentValue, int n) {
        //base case
        if (currentValue != null && currentValue > n) {
            return;
        }
        //收集结果
        if (currentValue != null) {
            result.add(currentValue);
        }

        //注意仔细理解递归的过程，1开头的所有数、10开头的所有数
        for (int i=0; i<=9; i++) {
           if (currentValue == null) {//刚开始传进来null时候，保证最后向上递归的时候计算以2，3，4...9开头的数
               if (i == 0) {
                   continue;
               }else {
                   currentValue = 0;
               }
           }
           lexicalOrder(result, currentValue*10 + i, n);
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new LexicalOrder().lexicalOrder(13);
        for (int a : list) {
            System.out.print(a + " ");
        }
    }
}
