package arithmetic.array;

/**
 * 先求相邻两数之间是否是逆序对，比较完成之后将其排序合并，再求合并后的数之间的逆序对
 * 归并排序变型题
 * @Description: 数组中的逆序对
 * @Author: nianjie.chen
 * @Date: 11/1/2019
 */
public class InversePairs {
    int count = 0;
    public int InversePairs(int [] array) {

        if (array == null || array.length < 2) {
            return count;
        }

        InversePairs(array, 0, array.length - 1);
        return count;
    }

    /**
     * 排序的同时查找逆序对
     * @param array
     * @param left
     * @param right
     * @return
     */
    private void InversePairs(int[] array, int left, int right) {
        //base case
        if (left == right) {
            return;
        }

        int mid = (left + right)/2;
        InversePairs(array, left, mid);
        InversePairs(array, mid + 1, right);
        //合并
        InversePairs(array, left, mid, right);
    }

    /**
     * 合并外排的过程，统计满足逆序对的个数
     * @param arr
     * @param left
     * @param mid
     * @param right
     */
    private void InversePairs(int[] arr, int left, int mid, int right) {
        //创建一个数组
        int[] help = new int[right-left+1];
        int i = 0;
        int p1 = left;
        int p2 = mid+1;
        //做一个循环进行排序
        while(p1 <= mid && p2 <= right){
            if (arr[p1]<arr[p2]) {
                help[i++] = arr[p1++];
            }else {
                help[i++] = arr[p2++];
                count++;
                count = count + mid - p1;
            }
        }
        //当左边没有越界时
        while(p1 <= mid){
            help[i++] = arr[p1++];
        }
        while(p2 <= right){
            help[i++] = arr[p2++];
        }
        //合并
        for(i = 0; i < help.length; i++){
            arr[i+left] = help[i];
        }

    }

    public static void main(String[] args) {
        InversePairs inversePairs = new InversePairs();
        int[] array = {7, 5, 6, 4, 3};
        inversePairs.InversePairs(array);
        System.out.println(inversePairs.count);
        for (int a : array) {
            System.out.print(a + " ");
        }
    }
}
