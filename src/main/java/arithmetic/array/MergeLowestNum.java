package arithmetic.array;

import java.util.Collections;
import java.util.ArrayList;

/**
 * 利用比较器，定义比较规则
 * compareTo方法完美解决
 * @Description: 把一个正整数数组中的数拼接成最小的数
 * @Author: nianjie.chen
 * @Date: 10/30/2019
 */
public class MergeLowestNum {

    public String PrintMinNumber(int[] numbers) {
        if (numbers == null || numbers.length == 1) {
            return null;
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            list.add(numbers[i]);
        }

        Collections.sort(list, (o1, o2) -> {
            String s1 = o1 + "" + o2;
            String s2 = o2 + "" + o1;
            return s1.compareTo(s2);
        });

        StringBuilder sb = new StringBuilder();
        for (Integer i : list) {
            sb.append(i);
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        int[] arr = {32,12,3};
        String s = new MergeLowestNum().PrintMinNumber(arr);
        System.out.println(s);
    }

}
