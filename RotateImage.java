import com.sun.org.apache.regexp.internal.RE;

/**
 * @author LiangHaoFa
 * @version 1.0.0
 * @description //TODO 旋转矩阵
 * @date 2022/1/11 20:55
 */
public class RotateImage {



    public void rotate(int[][] matrix) {
        int n = matrix.length;
        if (n <= 1){
            return;
        }
        int left = 0;
        int bottom = n - 1;
        int[] arr = new int[n];
        while (left < bottom){
            for (int l = left + 1,b = bottom - 1;l <= bottom;l++,b --){
                arr[l] = matrix[left][l];
                matrix[left][l] = matrix[b][left];
                matrix[b][left] = matrix[bottom][b];
                matrix[bottom][b] = matrix[l][bottom];
                matrix[l][bottom] = arr[l];
            }
            bottom --;
            left ++;
        }
    }

}
