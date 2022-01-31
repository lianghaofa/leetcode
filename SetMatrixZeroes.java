import java.util.Set;
import java.util.TreeSet;

/**
 * @author LiangHaoFa
 * @version 1.0.0
 * @description //TODO
 * @date 2022/1/11 23:00
 */
public class SetMatrixZeroes {

    public void setZeroes(int[][] matrix) {
        if (matrix.length == 0){
            return;
        }
        Set<Integer> cols = new TreeSet<>();
        Set<Integer> rows = new TreeSet<>();
        int rs = matrix.length;
        int cs = matrix[0].length;
        for (int i = 0; i < rs; i ++){
            for (int j = 0; j < cs ; j ++){
                if (matrix[i][j] == 0){
                    cols.add(j);
                    rows.add(i);
                }
            }
        }

        for (int i = 0; i < rs; i ++){
            for (int j = 0; j < cs ; j ++){
                if (cols.contains(j) || rows.contains(i)){
                    matrix[i][j] = 0;
                }
            }
        }


    }
}
