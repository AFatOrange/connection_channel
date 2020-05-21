package others;

import java.util.ArrayList;
import java.util.List;

public class tsst {

    public static int pivotIndex(int[] nums) {
        
        int intSize = nums.length-1;
        int leftSum = 0;
        int sum = 0;
        for(int n =0;n<=intSize;n++) {
            sum = sum+nums[n];
        }
        for(int index = 0;index<=intSize;index++) {
            
            if(index == 0) {
                leftSum = 0;
            }else {
                leftSum = leftSum+nums[index-1];
            }
            if(leftSum*2+nums[index]==sum)
                return index;
        }
        return -1;

    }  
    
    public static int dominantIndex(int[] nums) {

        int max = 0;
        int maxIndex = 0;
        int numsSize = nums.length-1;
        //找出最大值
        for(int n =0;n<=numsSize;n++) {
            if(n==0) {
                max = nums[n];
                maxIndex = n;
            }else {
                if(max<nums[n]) {
                    max = nums[n];
                    maxIndex = n;
                }
            }
        }
        for(int n =0;n<=numsSize;n++) {
            if(max<nums[n]*2&&n!=maxIndex) {
                return -1;
            }
        }
        return maxIndex;
        
    }
    
    public static int[] findDiagonalOrder(int[][] matrix) {
           int matrixSizeAll = 0;
           //计算矩阵大小，M x N
           int M = matrix.length;
           int N = matrix[0].length;
           matrixSizeAll = M*N;
           int[] result = new int [matrixSizeAll];
           int[][] direction= {
                                   {-1,1},
                                   {1,-1}
                               };
           int row = 0;
           int col = 0;
           int k = 0;
           for(int n=0;n<matrixSizeAll;n++) {
               result[n] = matrix[row][col];
               System.out.println(matrix[row][col]);
               row = row+direction[k][0];
               col = col+direction[k][1];
               if(col>N-1) {
                   col = N-1;
                   row = row+2;
                   k = 1-k;
               }
               if(row<0) {
                   row = 0;
                   k = 1-k;
               }
               if(row>M-1) {
                   row = M-1;
                   col = col+2;
                   k = 1-k;
               }
               if(col<0) {
                   col = 0;
                   k = 1-k;
               }
           }
           return result;
    }
    
    public int[] findDiagonalOrder2(int[][] matrix) {
        // 参数校验
        if (matrix.length == 0) {
          return new int[0];
        }
        int depth = matrix.length;
        int width = matrix[0].length;
        // 遍历次数
        int count = depth + width - 1;
        int m = 0;
        int n = 0;
        // 出参
        int[] ret = new int[depth * width];
        int retIndex = 0;
        // 遍历次数
        for (int k = 0; k < count; k++) {
          if (k % 2 == 0) {
            // 从左到右往上遍历
            while (m >= 0 && n < width) {
              ret[retIndex++] = matrix[m--][n++];
            }

            // 数组越界，计算下次遍历开始坐标
            if (n < width) {
              m += 1;
            } else {
              m += 2;
              n -= 1;
            }
          } else {
            // 从右到左往下遍历
            while (m < depth && n >= 0) {
              ret[retIndex++] = matrix[m++][n--];
            }
            // 数组越界，计算下次遍历开始坐标
            if (m < depth) {
              n += 1;
            } else {
              m -= 1;
              n += 2;
            }
          }

        }

        return ret;
      }
    
    
    public static void main(String[] args) {
        
        /*
         * int[] nums = {-1,-1,-1,0,1,1}; System.out.println(pivotIndex(nums));
         */
        /*
         * int[] nums2 = {3, 6, 1, 0}; System.out.println(dominantIndex(nums2));
         */
        int [] [] matrix = {{1, 2, 3, 1}, {4, 5, 6, 2}, {7, 8, 9, 3}, {1, 2, 3, 4}, {4, 5, 6, 5}, {7, 8, 9, 6}};
        findDiagonalOrder(matrix);
        
    }
    
    
    /**
     * 1 2 3 1 1
     * 4 5 6 2 1 
     * 7 8 9 3 1
     * 1 2 3 4 1
     * 4 5 6 5
     * 7 8 9 6
     * 
     * 
     * -1  1 
     *  1 -1
     */
}
