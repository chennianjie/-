package arithmetic.search;

/**
 * 把数组中奇数放一边偶数放一边并保证相对顺序不变
 */
public class ReOrderArray {

    public void reOrderArray(int[] array) {
        //先找出数组中奇数的个数，按个数划分数组，然后申请一个同样大小的数组进行填充
        int oddNumCount = findOddNumCount(array);
        int[] arr = new int[array.length];
        int index = 0;
        for (int num : array) {
            if ((num&0x1) == 1){
                arr[index++] = num;
            }else {
                arr[oddNumCount++] = num;
            }
        }
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    /***
     * 找整数数组中的奇数个数
     * @param array
     * @return
     */
    public int findOddNumCount(int[] array) {
        int count = 0;
        for (int num : array) {
            if ((num&0x1) == 1){
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6,7};
        ReOrderArray reOrderArray = new ReOrderArray();
        reOrderArray.reOrderArray(array);
    }
}
