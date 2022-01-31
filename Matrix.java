/**
 * @author LiangHaoFa
 * @version 1.0.0
 * @description //TODO
 * @date 2022/1/19 19:21
 */
public class Matrix {


    public static void main(String[] args) {
        int[][] matrix = new int[5][5];
        int[] arr0 = new int[]{1,4,7,11,15};
        int[] arr1 = new int[]{2,5,8,12,19};
        int[] arr2 = new int[]{3,6,9,16,22};
        int[] arr3 = new int[]{10,13,14,17,24};
        int[] arr4 = new int[]{18,21,23,26,30};
        matrix[0] = arr0;
        matrix[1] = arr1;
        matrix[2] = arr2;
        matrix[3] = arr3;
        matrix[4] = arr4;

        System.out.println(findNumberIn2DArray(matrix,20));

        System.out.println(search(arr4,0));
        //findNumberIn2DArray(matrix,20);

    }

    public static int search(int[] nums, int target) {

        int left = 0;
        int right = nums.length -1;

        while (left < right){
            int mid = left + (right - left) / 2;
            if (target == nums[mid]){
                return mid;
            }else if (target > nums[mid]){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return right;
    }


    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0){
            return false;
        }
        int top = 0;
        int right = matrix[top].length - 1;
        while (right > 0 && top < matrix.length){
            int left = 0;
            while (left < right){
                int mid = left + (right - left) / 2;
                if (matrix[top][mid] == target){
                    return true;
                }else if (target > matrix[top][mid]){
                    left = mid + 1;
                }else {
                    right = mid - 1;
                }
            }
            if (matrix[top][left] == target){
                return true;
            }
            top ++;
        }
        return false;

    }


    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid.length <= 0){
            return -1;
        }
        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;
        for (int i = rows - 1;i >= 0; i --){
            for (int j = cols - 1; j >= 0; j--) {
                int right = j + 1 >= cols ? 0 : obstacleGrid[i][j + 1];
                int down = i + 1 >= rows ? 0 : obstacleGrid[i + 1][j];
                if (i == rows - 1 && j == cols - 1){
                    obstacleGrid[i][j] = obstacleGrid[i][j] == 1 ? 0 : 1;
                }else if (obstacleGrid[i][j] == 1){
                    obstacleGrid[i][j] = 0;
                }else {
                    obstacleGrid[i][j] = right + down + obstacleGrid[i][j];
                }
            }
        }
        return obstacleGrid[0][0];
    }



    public int minPathSum(int[][] grid) {
        if (grid.length <= 0){
            return -1;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = rows - 1;i >= 0; i --){
            for (int j = cols - 1; j >= 0; j--) {
                int right = j + 1 >= cols ? 1000000 : grid[i][j + 1];
                int down = i + 1 >= rows ? 1000000 : grid[i + 1][j];
                if (i == rows - 1 && j == cols - 1){

                }else {
                    grid[i][j] = Math.min(right,down) + grid[i][j];
                }
            }
        }
        return grid[0][0];
    }





    public int[][] generateMatrix(int n) {

        int[][] matrix = new int[n][n];
        int num = 1;
        for (int left = 0,right= n- 1;left <= right; left ++,right --){
            for (int i = left;i < right;i ++){
                matrix[left][i] = num ++;
            }
            for (int i = left;i < right;i ++){
                matrix[i][right] = num ++;
            }
            for (int i = right;i > left;i --){
                matrix[right][i] = num ++;
            }
            for (int i = right; i > left ; i --){
                matrix[i][left] = num ++;
            }
        }
        if (n % 2 == 1){
            matrix[n/2][n/2] = num;
        }
        return matrix;
    }

}
