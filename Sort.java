import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * @author LiangHaoFa
 * @version 1.0.0
 * @description //TODO
 * @date 2022/1/17 20:33
 */
public class Sort {


    public static void main(String[] args) {

        int[] arr = new int[]{7,5,6,4};

        System.out.println(reversePairs(arr));

    }










    public static int reversePairs(int[] nums) {
        return countPairs(nums,0,nums.length - 1);
    }


    private static int countPairs(int[] nums,int begin,int end){
        if (begin >= end){
            return 0;
        }
        int mid = begin + (end - begin) / 2;
        return countPairs(nums,begin,mid) + countPairs(nums,mid + 1,end) + dealPairs(nums,begin,mid,end);
    }
    private static int dealPairs(int[] nums,int begin,int mid,int end){
        int[] temp = new int[end - begin + 1];
        int count = 0;
        int l = begin;
        int r = mid + 1;
        for (int i= 0; i < temp.length; i ++){
            if (r > end){
                temp[i] = nums[l++];
                count += r - (mid + 1) ;
            } else if (l > mid){
                temp[i] = nums[r++];
            }else if (nums[l] > nums[r]){
                temp[i] = nums[r++];
            }else {
                temp[i] = nums[l++];
                count += (r - (mid + 1));
            }
        }
        for (int i = begin;i <= end;i ++){
            nums[i] = temp[i-begin];
        }
        return count;
    }






    public static int[] smallestK(int[] arr, int k) {
        int[] array = new int[k];
        quickSort(arr);
        for (int i = 0; i < k; i ++){
            array[i] = arr[i];
        }
        return array;
    }


    public static void mergeSort(int[] arr,int start,int end){
        // 利用左侧相对有序   交换？no 选择？no 插入 yes  移位？
        if (start >= end){
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSort(arr,start,mid);
        mergeSort(arr,mid + 1,end);
        insertionSort(arr,start,mid,end);
    }

    private static void insertionSort(int[] arr,int begin,int mid,int end){
        for (int i = mid + 1; i <= end ; i ++ ){
            for (int j = i; j > begin; j --){
                if (arr[j - 1] > arr[j]){
                    swap(arr,j - 1,j);

                }else {
                    break;
                }
            }
        }
    }



    public static void quickSort(int[] arr){
        if (arr.length <= 1){
            return;
        }
        quickSort(arr,0,arr.length - 1);
    }

    public static void quickSort(int[] arr,int l ,int r){
        if (l < r){
            swap(arr, l + (int) (Math.random() * (r - l)), r);
            int[] res = partition(arr,l,r);
            quickSort(arr,l,res[0] - 1);
            quickSort(arr,res[1] + 1,r);
        }
    }

    private static int[] partition(int[] arr,int l, int r) {
        int less = l - 1;
        int more = r;
        while (l < more){
            if (arr[l] < arr[r]){
                swap(arr,++less,l++);
            }else if (arr[l] > arr[r]){
                swap(arr,--more,l);
            }else {
                l ++;
            }
        }
        swap(arr,r,more);
        return new int[]{less + 1,more};
    }

    private static void swap(int[] arr,int l,int r){
        int tmp = arr[l];
        arr[l] = arr[r];
        arr[r] = tmp;
    }



}
