package arithmetic.sort;

/**
 * 插入排序
 */
public class InsertSort {
    public static void insertSort(int[] arr){
        //排除数组为空和数组长度小于2的情况
        if(arr==null || arr.length<2){
            return ;
        }
        //外循环，挨个比较每个值与有序列之间的关系
        //最好：12345  O(N)    最坏：54321 O(N^2)
        //eg:6324==>3624==>2364==>2346
        for(int i=1;i<arr.length;i++){
            for(int j=i-1 ; j>=0 && arr[j]>arr[j+1] ; j--){
                int a = arr[j+1];
                arr[j+1] = arr[j];
                arr[j] = a;
            }
        }
        for(int a:arr){
            System.out.print(a);
        }
    }
}
