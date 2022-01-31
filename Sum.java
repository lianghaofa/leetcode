import java.util.*;

/**
 * @author LiangHaoFa
 * @version 1.0.0
 * @description //TODO
 * @date 2022/1/12 0:44
 */
public class Sum {


    public static void main(String[] args) {

        //reverse(-123);
        //System.out.println(reverse(-123))
        int[] arr = {-1,2,1,-4};

        System.out.println(threeSumClosest(arr,1));
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        if (nums.length < 3){
            return -1;
        }
        int res = nums[0] + nums[1] + nums[2];
        for (int i = 0;i < nums.length ; i ++){
            int start = i + 1,end = nums.length - 1;
            while (start < end){
                int sum = nums[start] + nums[end] + nums[i];
                if (Math.abs(target - sum) < Math.abs(target - res)){
                    res = sum;
                }
                if (sum > target){
                    end --;
                }else if (sum < target){
                    start ++;
                }else {
                    return res;
                }
            }
        }
        return res;
    }









    private boolean isBadVersion(int n){
        return true;
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()){
            return false;
        }
        int[] arr = new int[26];
        char[] sarr = s.toCharArray();
        char[] tarr = t.toCharArray();
        for (int i = 0; i < s.length(); i ++){
            arr[sarr[i] - 97] ++;
            arr[tarr[i] - 97] --;
        }
        for (int i = 0; i < arr.length; i ++){
            if (arr[i] != 0){
                return false;
            }
        }
        return true;
    }








    public boolean isValidSudoku(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] cols = new int[9][9];
        int[][] matrix = new int[9][9];
        for (int i = 0; i < 9;i ++){
            for (int j = 0; j < 9; j ++){
                char c = board[i][j];
                if (c != '.'){
                    int col = c - '1';
                    rows[i][col] ++;
                    cols[col][j] ++;
                    int index = j/3 + (i/3)*3;
                    matrix[index][col] ++;
                    if (rows[i][col] > 1 || cols[col][j] > 1 || matrix[index][col] > 1){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        if (magazine.length() < ransomNote.length()){
            return false;
        }

        int[] arr = new int[256];
        for (int i = 0;i < magazine.length(); i ++){
            arr[magazine.charAt(i)] ++;
        }
        for (int i = 0; i < ransomNote.length(); i ++){
            arr[ransomNote.charAt(i)] --;
            if (arr[ransomNote.charAt(i)] < 0){
                return false;
            }
        }
        return true;
    }





    public static int reverse(int x) {
        if (x == 0){
            return x;
        }
        long s = x > 0 ? (long)x : -(long)x;
        long res = 0;
        while (s != 0){
            long c = s % 10;
            s /= 10;
            res = res * 10 + c;

        }
        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE){
            return 0;
        }
        return x > 0 ? (int) res : -(int) res;
    }

    public static boolean isPerfectSquare(int num) {

        int left = 0;
        int right = num;
        while (left <= right){
            int mid = (left + (right - left) / 2) ;
            long pow = (long) mid * mid;
            if (pow == num){
                return true;
            }else if (pow > num){
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }

        return false;
    }

    public static int search(int[] arr, int target) {

        int left = 0;
        int right = arr.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (arr[mid] == target){
                return mid;
            }
            if (arr[left] == target){
                return left;
            }
            if (arr[right] == target){
                return right;
            }
            // left  monotony, 在范围内 search left   右侧单调但不在范围
            if (arr[mid] >= arr[left] && target <= arr[mid] && target >= arr[left]){
                right = mid - 1;
            }
            // 右侧单调但不在范围
            else if (arr[right] >= arr[mid] && arr[mid] > target && target > arr[right] ){
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }

        }
        return -1;
    }



    public int findMin(int[] nums) {

        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            // 必然有一边是有序的

            // 左侧有序，数组被移动过，最小值在右侧
            if (nums[mid] > nums[right]){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return nums[left];

    }

    public static int lengthOfLongestSubstring(String s) {
        int max = 0;
        int[] arr = new int[256];
        int left = 0;
        int right = 0;
        while (right < s.length()){
            // not equal
            if (arr[s.charAt(right)] > 0){
                // 检索第一次到达的位置
                while (s.charAt(left) != s.charAt(right) && left < right){
                    arr[s.charAt(left)] --;
                    left ++;
                }
                arr[s.charAt(left)] --;
                left ++;
            }
            arr[s.charAt(right)] ++;
            max = Math.max(right-left + 1,max);
            right ++;
        }
        return max;
    }

    public static boolean checkInclusion(String s1, String s2) {
        if (s2.length() < s1.length()){
            return false;
        }
        for (int end = s1.length() - 1,start = 0 ; end < s2.length(); end ++,start ++){
            int[] arr = new int[26];
            for (int i = 0; i < s1.length() ; i ++){
                arr[s1.charAt(i) - 'a'] ++;
            }
            boolean t = true;
            for (int i = start; i <= end; i ++){
                if (-- arr[s2.charAt(i) - 'a'] < 0){
                    t = false;
                }
            }
            if (t){
                return true;
            }

        }
        return false;
    }






    private void swap(int[] num,int l,int r){

        if (l > num.length || r > num.length || l < 0 || r < 0){
            return;
        }
        int temp = num[l];
        num[l] = num[r];
        num[r] = temp;
    }


    private void swapChars(char[] num,int l,int r){

        if (l > num.length || r > num.length || l < 0 || r < 0){
            return;
        }
        char temp = num[l];
        num[l] = num[r];
        num[r] = temp;
    }




}
