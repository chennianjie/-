package arithmetic.sort;

import common.ArrayTools;

import java.util.Arrays;


public class BubbleSort {

    public static void bubbleSort(int[] arr){
        if(arr==null || arr.length<2){
            return ;
        }
        //确定end=arr.length，一直end=0挨个确定最大值就完成了排序
        for(int end=arr.length-1;end>0;end--){
            for(int i=0;i<end;i++){
                if(arr[i]>arr[i+1]){
                    ArrayTools.swap(arr, i,i+1);
                }
            }
        }
    }

    public static void main(String[] args) {
        boolean success = false;
        int count = 500000;
        String mess = null;
        for(int i=0;i<count;i++){
            int[] arr1 = ArrayTools.generateRandomArray(100,100);
            int[] arr2 = ArrayTools.copyArr(arr1);
            int[] arr3 = ArrayTools.copyArr(arr1);
            bubbleSort(arr1);
            Arrays.sort(arr2);
            success = ArrayTools.isequals(arr1,arr2);
            if(!success){
                ArrayTools.printArr(arr3);//错误测试用例
                break;
            }
        }
        System.out.println(mess = success ? "NICE" : "GG");
    }
}
