/**
 * @author LiangHaoFa
 * @version 1.0.0
 * @description //TODO
 * @date 2022/1/17 11:27
 */
public class Arr {



    // k
    private void bfprt(int[] arr){


    }

    public void merge(int[] A, int m, int[] B, int n) {

        int last = m + n - 1;
        while (last >= 0){

            if ((m - 1) < 0){
                A[last--] = B[--n];
            }else if ((n - 1) < 0){
                A[last--] = A[--m];
            } else if (A[m - 1] > B[n - 1]){
                A[last--] = A[--m];
            }else {
                A[last--] = B[--n];
            }

        }


    }

}
