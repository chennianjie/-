package concurrent.forkjoin.sumarray;

import java.util.Random;

/**
 * 生成随机整型数组
 */
public class RandomArray {

    public static int[] buildRandomArray(int arrayLength){
        int[] randomArray = new int[arrayLength];
        Random random = new Random();
        for (int i = 0; i < arrayLength; i++) {
            randomArray[i] = random.nextInt(arrayLength*3);
        }
        return randomArray;
    }
}
