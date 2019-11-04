package arithmetic.array;

/**
 * @Description: 寻找一个数出现在排序数组中的个数
 * @Author: nianjie.chen
 * @Date: 11/4/2019
 */
public class GetNumFromOrderArray {

    int mid;
    public int GetNumberOfK(int [] array , int k) {
        int count = 0;
        Integer numIndex = getNum(array, 0, array.length - 1, k);
        if (numIndex == null) {
            return 0;
        }else {
            count = 1;
            int left = numIndex - 1;
            int right = numIndex + 1;
            while (left >= 0 && array[left] == k) {
                count++;
                left--;
            }
            while (right <= array.length - 1 && array[right] == k) {
                count++;
                right++;
            }
        }
        return count;
    }

    private Integer getNum(int[] array, int left, int right, int k) {
        if (left > right || k < array[left] || k > array[right]){
            return null;
        }

        mid = (left + right)/2;
        if (array[mid] > k) {
            return getNum(array, left, mid-1, k);
        }else if(array[mid] < k){
            return getNum(array, mid+1, right, k);
        }else {
            return mid;
        }
    }


    public static void main(String[] args) {
        int[] array = {1,1,1,2,2,3,4};
        GetNumFromOrderArray getNumFromOrderArray = new GetNumFromOrderArray();
        int i = getNumFromOrderArray.GetNumberOfK(array, 1);
        System.out.println(i);
        Integer num = getNumFromOrderArray.getNum(array, 0, array.length - 1, 1);
        System.out.println(num);
    }
}
