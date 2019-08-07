package common;

/**
 * 处理数组相关的工具类
 */
public class ArrayTools {

    /**
     * 比较两个数组
     * @param arr1
     * @param arr2
     * @return
     */
    public static boolean isequals(int[] arr1,int[] arr2){
        //一个为空一个不为空false
        if(arr1==null&&arr2!=null || arr2==null&&arr1!=null){
            return false;
        }
        //两个都为空true
        if(arr1==null && arr2==null){
            return true;
        }
        //两个长度不相等即返回false
        if(arr1.length!=arr2.length){
            return false;
        }
        //挨个比较一个不相同即为false
        for(int i=0;i<arr1.length;i++){
            if(arr1[i]!=arr2[i]){
                return false;
            }
        }
        //最终没返回false即返回true
        return true;
    }

    /**
     * 遍历一个数组
     * @param arr
     */
    public static void printArr(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }

    /**
     * copy一个数组
     * @param arr
     * @return
     */
    public static int[] copyArr(int[] arr){
        if(arr==null){
            return null;
        }
        int[] newArr = new int[arr.length];
        for(int i=0;i<newArr.length;i++){
            newArr[i] = arr[i];
        }
        return newArr;
    }


    /**
     * 生成随机数组
     * @param maxSize 数组大小
     * @param maxValue 数组最大值
     * @return
     */
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

}
