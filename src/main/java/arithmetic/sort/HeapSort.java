package arithmetic.sort;

/**
 * 常数项比较大   不稳定
 * 时间复杂度：N*logN
 */
public class HeapSort {

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        int size = arr.length;
        swap(arr, 0, --size);
        while (size > 0) {
            heapify(arr, 0, size);
            swap(arr, 0, --size);
        }
    }
    //排成大根堆的过程是O(n)的时间复杂度
    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }
    //这是一个向下调整的过程
    public static void heapify(int[] arr, int index, int size) {
        int left = index * 2 + 1;
        while (left < size) {//左孩子不越界
            int largest = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;//返回左右孩子中较大那个值的下标
            largest = arr[largest] > arr[index] ? largest : index;//较大孩子与父节点比较选最大的下标
            if (largest == index) {//如果最大还是父节点，那以下的子节点就没有比他更大的值，退出循环
                break;
            }
            //如果子孩子有比父节点大的就交换值然后接着循环上一个过程
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
