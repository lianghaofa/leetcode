/**
 * @author LiangHaoFa
 * @version 1.0.0
 * @description //TODO
 * @date 2022/1/17 23:25
 */
public class BFPRT {


    public static void main(String[] args) {

        int[] arr = new int[]{4,3,2,6,5,1};

        System.out.println(select(arr,0,arr.length - 1,5));
    }



    private static int select(int[] arr,int start,int end, int k){
        if (k > end){
            return -1;
        }
        if (start == end){
            return arr[start];
        }

        // begin
        // grouping
        int num = end - start + 1;
        int count = num/5 + (num % 5 == 0 ? 0 : 1);
        int[] groups = new int[count];
        for (int i = 0;i < count; i ++){
            int e = start + i * 5 + 4;
            groups[i] = getMedian(arr,start + i * 5,Math.min(e,end));
        }
        //  select median
        int pivot = getMedian(groups, 0, groups.length - 1);
        // end


        //  select median,then partition
        int[] p = partition(arr,start,end,pivot);
        //  done
        if (k >= p[0] && k <= p[1]){
            return arr[k];
        }else if (k < p[0]){
            return select(arr, start, p[0] - 1, k);
        }else {
            return select(arr, p[1] + 1, end, k);
        }
    }

    private static int[] partition(int[] arr,int start,int end,int pivot){
        int less = start - 1;
        int more = end + 1;
        while (start < more){
            if (arr[start] < pivot){
                swap(arr,++ less,start ++);
            }else if (arr[start] > pivot){
                swap(arr,-- more,start);
            }else {
                start ++;
            }
        }
        return new int[]{less + 1,more - 1};
    }

    private static int getMedian(int[] arr, int start, int end) {
        insertionSort(arr,start,end);
        return arr[start + (end - start) / 2];
    }

    public static void insertionSort1(int[] arr, int begin, int end) {
        for (int i = begin + 1; i != end + 1; i++) {
            for (int j = i; j != begin; j--) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                } else {
                    break;
                }
            }
        }
    }

    private static void insertionSort(int[] arr,int begin,int end){
        for (int i = begin + 1; i <= end ; i ++ ){
            for (int j = i; j > begin; j --){
                if (arr[j - 1] > arr[j]){
                    swap(arr,j - 1,j);
                }else {
                    break;
                }
            }
        }
    }

    public static void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }


}
