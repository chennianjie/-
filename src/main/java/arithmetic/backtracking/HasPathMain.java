package arithmetic.backtracking;

import java.util.Scanner;

/**
 * 回溯法
 * @Description:  矩阵中的路径
 * @Author: nianjie.chen
 * @Date: 11/21/2019
 */
public class HasPathMain {
    public static void main(String[] args){
        char[] arr = {'a','b','c','e','s','f','c','s','a','d','e','e'};
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String str = sc.nextLine();
            char[] c = str.toCharArray();
            int rows = sc.nextInt();//3
            int cols = sc.nextInt();//4
            System.out.println(hasPath(arr,rows,cols,c));
        }
    }

    public static boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if(matrix==null || matrix.length==0 || str==null || str.length==0 || matrix.length!=rows*cols || rows<=0 || cols<=0 || rows*cols < str.length) {
            return false ;
        }

        boolean[] flag = new boolean[rows*cols] ;
        int len = 0 ;

        for(int i=0 ; i<=rows-1 ; i++) {
            for(int j=0 ; j<=cols-1 ; j++) {
                if(hasPathCore(matrix, rows, cols, str, i, j, flag, len)) { return true ; }
            }
        }

        return false ;
    }

    public static boolean hasPathCore(char[] matrix, int rows, int cols, char[] str, int row, int col, boolean[] visited, int len) {
        boolean flag = false ;

        if(row>=0 && row<rows && col>=0 && col<cols && !visited[row*cols+col] && matrix[row*cols+col]==str[len]) {
            len++ ;
            visited[row*cols+col] = true ;
            if(len==str.length) { return true ; }
            flag = hasPathCore(matrix, rows, cols, str, row, col+1, visited, len)  ||
                    hasPathCore(matrix, rows, cols, str, row+1, col, visited, len)  ||
                    hasPathCore(matrix, rows, cols, str, row, col-1, visited, len)  ||
                    hasPathCore(matrix, rows, cols, str, row-1, col, visited, len) ;

            if(!flag) {
                len-- ;
                visited[row*cols+col] = false ;
            }
        }

        return flag ;
    }
}
