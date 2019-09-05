package arithmetic.sort;

/**
 * 时间复杂度O(N*logN)，额外空间复杂度O(N)，实现可以做到稳定性
 */
public class MergeSort {

    /**
     * 获取数组范围中的最大值，理解递归的过程
     * @param arr
     * @param l
     * @param r
     * @return
     */
    public static int getMax(int[] arr,int l,int r){
        //baseCase
        if(l==r){
            return arr[l];
        }
        //中间值
        int mid = l + (r-l)/2;
        //左边求最大值
        int lMax = getMax(arr,l,mid);
        //右边求最大值
        int rMax = getMax(arr,mid+1,r);
        return Math.max(lMax, rMax);
    }







    public static void main(String[] args) {
//		int[] arr = generateRandomArray(20,100);
//		for(int a:arr){
//			System.out.print(a+" ");
//		}
        //System.out.println();
        int[] arr = {1,2,3,5,8,1,4,2};
        mergeSort(arr);
        for(int a:arr){
            System.out.print(a+" ");
        }
    }

    public static void mergeSort(int[] arr){
        //参数判断，避免报错
        if(arr==null || arr.length<2){
            return ;
        }
        mergeSort(arr,0,arr.length-1);
    }
    //归并排序
    public static void mergeSort(int[] arr,int left,int right){
        if(left==right){
            return; //base case递归终止条件
        }
        //中点值
        int mid = left + ((right-left)>>1);
        //左边排序递归调用
        mergeSort(arr,left,mid);
        //右边同上
        mergeSort(arr,mid+1,right);
        //外排合并
        merge(arr,left,mid,right);
    }
    //外排合并

    public static void merge(int[] arr,int left,int mid,int right){
        //创建一个数组
        int[] help = new int[right-left+1];
        int i = 0;
        int p1 = left;
        int p2 = mid+1;
        //做一个循环进行排序
        while(p1 <= mid && p2 <= right){
            help[i++] = arr[p1]<arr[p2] ? arr[p1++] : arr[p2++];
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

}
