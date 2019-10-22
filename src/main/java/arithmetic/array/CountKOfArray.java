package arithmetic.array;

import java.util.ArrayList;

/**
 * @Description:从数组中找出最小的K个数
 * @Author: nianjie.chen
 * @Date: 10/22/2019
 */
public class CountKOfArray {


    public static void main(String[] args) {
        int[] input = {4,5,1,6,2,7,3,8};
        ArrayList<Integer> list = new CountKOfArray().GetLeastNumbers_Solution(input, 8);
        for (int i : list) {
            System.out.println(i);
        }
    }

    public  ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (k > input.length) {
            return list;
        }else {
            heapSort(input, k);
            int index = input.length - 1;
            while (k > 0) {
                list.add(input[index]);
                index--;
                k--;
            }
        }
        return list;
    }

    public void heapSort(int[] arr, int k) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        int size = arr.length;
        swap(arr, 0, --size);
        while (k > 1) {
            k--;
            heapify(arr, 0, size);
            swap(arr, 0, --size);
        }
    }
    //排成小根堆
    public void heapInsert(int[] arr, int index) {
        //(0-1)/2 --> 0
        while (arr[index] < arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }
    //这是一个向下调整的过程
    public void heapify(int[] arr, int index, int size) {
        int left = index * 2 + 1;
        while (left < size) {//左孩子不越界
            int largest = left + 1 < size && arr[left + 1] < arr[left] ? left + 1 : left;//返回左右孩子中较小那个值的下标
            largest = arr[largest] < arr[index] ? largest : index;//较大孩子与父节点比较选最小的下标
            if (largest == index) {//如果最大还是父节点，那以下的子节点就没有比他更大的值，退出循环
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
