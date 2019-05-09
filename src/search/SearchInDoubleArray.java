package search;


/**
 * 在第一个二维数组中，每个一维数组长度一样，二维数组从左到右，从上至下依此递增
 * 给一个数target，判断其是否存在二维数组中
 *
 * 思路：本体要求时间复杂度O（N+M），所以不能遍历二维数组，考虑从左下角或者右上角开始，
 * 判断当前数与目标值大小，然后根据数据状况规律进行移动
 */
public class SearchInDoubleArray {

    public static boolean Find(int target, int [][] array) {
        //从左下角开始
        int maxRow = array.length - 1;
        int minCol = 0;
        boolean result = false;
        while (maxRow >= 0 && minCol <= (array[0].length - 1)){
            if (target == array[maxRow][minCol]) {
                result = true;
                break;
            }else if (target > array[maxRow][minCol]) {
                minCol++;
            }else {
                maxRow--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] array = {{1,2,8,9},
                         {2,4,9,12},
                         {4,7,10,13},
                         {6,8,11,15}};
        System.out.println(SearchInDoubleArray.Find(20, array));

    }
}
