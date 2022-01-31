import com.sun.jmx.snmp.SnmpNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author LiangHaoFa
 * @version 1.0.0
 * @description //TODO
 * @date 2022/1/19 3:28
 */
public class Num {

    public static void main(String[] args) {
//        int[] arr = new int[]{5,7,7,8,8,10};
//        int[] res = searchRange(arr, 7);
//        System.out.println(res[0]);
//        System.out.println(res[1]);

        //System.out.println(bitSum(35,37));

        //System.out.println(movingCount(1,2,1));

        int[] arr = new int[]{4,3,2,6};

        System.out.println(maxRotateFunction(arr));
    }

    //  0所在的位置分割成 right ,left ,mid  0的位置为i-1,每次往右走，可以发现除了i-1的位置，其余位置都比上一次减少1份。
    //  即 F(i+1) =  F(i) + (nums[i - 1] * (nums.length - 1))  - (sum - nums[i - 1])
    //  题目示例是往左走
    public int maxRotateFunction1(int[] nums) {
        int max = 0;
        int[] sumArr = new int[nums.length + 1];
        for (int i = 1;i < sumArr.length ; i ++){
            max += (nums[i - 1] * (i - 1));
            sumArr[i] = sumArr[i - 1] + nums[i - 1];
        }
        int res = max;
        for (int i = 1; i < nums.length ; i ++){
            int right = sumArr[nums.length] - sumArr[i];
            int mid = (nums.length - 1) * nums[i - 1];
            int left = 0;
            if (i - 2 >= 0){
                left = sumArr[i - 1] - sumArr[0];
            }
            int now = max - right - left + mid;
            res = Math.max(now,max);
            max = now;
        }
        return res;
    }
    // F(i+1) =  F(i) + (nums[i - 1] * (nums.length - 1))  - (sum - nums[i - 1])
    // simplify maxRotateFunction1
    public int maxRotateFunction2(int[] nums) {
        int fn_1 = 0,sum = 0;
        for (int i = 0;i < nums.length ; i ++){
            fn_1 += nums[i] * i;
            sum += nums[i];
        }
        int res = fn_1;
        for (int i = 1; i < nums.length ; i ++){
            int fn = fn_1 + (nums[i - 1] * (nums.length - 1)) - (sum - nums[i - 1]);
            res = Math.max(fn,res);
            fn_1 = fn;
        }
        return res;
    }
    // F(i+1) - F(i) = nums[i - 1] * nums.length  - sum
    // simplify maxRotateFunction2
    public int maxRotateFunction(int[] nums) {
        int fn_1 = 0,sum = 0;
        for (int i = 0;i < nums.length ; i ++){
            fn_1 += nums[i] * i;
            sum += nums[i];
        }
        int res = fn_1;
        for (int i = 1; i < nums.length ; i ++){
            fn_1 += nums[i - 1] * nums.length - sum ;
            res = Math.max(fn_1,res);
        }
        return res;
    }



    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < nums.length; i ++){
            // position nums[i] - 1
            int position = (nums[i] - 1) % n;
            nums[position] = nums[position]  + n;
        }
        for (int i = 0 ; i < nums.length; i ++){
            if (nums[i] < nums.length){
                list.add(i + 1);
            }
        }
        return list;
    }


    public static int numSquares(int n) {
        int left = 0;
        int right = n;
        while (left <= right){
            int mid = left + (right - left >> 1);

            if (mid * mid == n){
                return mid;
            }else if (mid * mid > n){
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return left * left == n ? left : n;
    }

    public List<Integer> majorityElement(int[] nums) {
        List<Integer> list = new ArrayList<>();
        if (nums.length == 0){
            return list;
        }
        int elementA = nums[0],elementB = nums[0],countA = 0,countB = 0;
        for (int i = 0;i < nums.length; i ++){
            if (nums[i] != elementA && nums[i] != elementB){
                if (countA == 0){
                    elementA = nums[i];
                    countA ++;
                }else if (countB == 0){
                    elementB = nums[i];
                    countB ++;
                }else {
                    countA --;
                    countB --;
                }
            }else if (nums[i] == elementA){
                countA ++;
            }else {
                countB ++;
            }
        }
        // 计数阶段
        countA = 0;
        countB = 0;
        for (int num : nums){
            if (elementA == num){
                countA ++;
            }
            if (elementB == num){
                countB ++;
            }
        }
        if (countA > nums.length / 3){
            list.add(elementA);
        }
        if (countB > nums.length / 3 && elementB != elementA){
            list.add(elementB);
        }
        return list;
    }

    public int addDigits(int num) {
        // yzk = 100*y + 10*z + k = 999*x + 99*y + 9 *z + ( y + z + k)  % 9 = y + z + k % 9
        // 0 % 9 = 0 ,9 % 9 = 0,18 % 9 = 0 ...
        // 只有个位数，取模后等于0的情况，0或者9
        // 如果不仅仅是个位数，取模后等于0，不可能为0或者9.必然为18.27.36...
        // 返回0的情况只有num==0,其他情况必然为9
        if (num == 0){
            return 0;
        }
        return num % 9 == 0 ? 9 : num % 9;

        // num - 1 % 9
        //return (num - 1) % 9 + 1;
    }


    public int titleToNumber(String columnTitle) {

        int sum = 0;
        int res = 1;
        for (int i = columnTitle.length() - 1; i >= 0; i ++){
            sum += (columnTitle.charAt(i) - 'A' + 1) * res;
            res *= 26;
        }
        return sum;
    }

    public List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();
        if (nums.length == 0){
            return list;
        }
        int start = 0;
        for (int i = 1; i < nums.length + 1; i ++){
            if (i == nums.length ){
                list.add(appendStr(nums,start,i - 1));
                continue;
            }
            if (nums[i] - nums[start] !=  i - start){
                list.add(appendStr(nums,start,i - 1));
                start = i;
            }
        }
        return list;
    }

    private String appendStr(int[] nums,int start,int end){
        StringBuffer temp = new StringBuffer();
        if (nums[end] == nums[start]){
            temp = new StringBuffer();
            temp.append(nums[start]);

        }else {
            temp = new StringBuffer();
            temp.append(nums[start]).append("->").append(nums[end]);
        }
        return temp.toString();
    }



    public String convertToTitle(int columnNumber) {
        StringBuffer stringBuffer = new StringBuffer();
        while (columnNumber > 0) {
            int a = columnNumber % 26;
            if (a == 0) {
                a = 26;
            }
            columnNumber -= a;
            columnNumber /= 26;
            stringBuffer.insert(0, (char) ('A' + a - 1));
        }
        return stringBuffer.toString();
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 0 || triangle.get(0).size() == 0){
            return -1;
        }
        int[] dp = new int[triangle.size()];
        for (int i = 0; i < dp.length ; i ++){
            dp[i] = triangle.get(triangle.size() - 1).get(i);
        }


        for (int i = triangle.size() - 2; i >= 0; i --){
            for (int j = 0;j <= i ; j ++){
                dp[j] = Math.min(dp[j],dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return triangle.get(0).get(0);
    }






    public List<Integer> getRow(int rowIndex) {

        Integer[] arr = new Integer[rowIndex + 1];


        for (int i = 0; i < arr.length; i ++){
            arr[0] = 1;
            arr[i] = 1;
            for (int j = i - 1 ; j > 0  ; j --){
                arr[j] = arr[j - 1] + arr[j];
            }
        }

        return Arrays.asList(arr);
    }

    public static int movingCount(int m, int n, int k) {
        boolean[][] arr = new boolean[m][n];
        return moving(arr,0,0,k);
    }

    private static int moving(boolean[][] matrix,int row,int col,int k){
        if (row < 0 || col < 0 || row >= matrix.length || col >= matrix[0].length){
            return 0;
        }
        if (matrix[row][col] || bitSum(row,col) > k){
            matrix[row][col] = true;
            return 0;
        }

        matrix[row][col] = true;
        int sum = 1;
        // up
        //sum += moving(matrix,row - 1,col,k);
        // right
        sum += moving(matrix,row,col + 1,k);
        // down
        sum += moving(matrix,row + 1,col,k);
        // left
        //sum += moving(matrix,row,col - 1,k);
        return sum;
    }


    private static int bitSum(int a,int b){
        int sum = 0;
        while (a > 0){
            sum +=a % 10;
            a /= 10;
        }
        while (b > 0){
            sum +=b % 10;
            b /= 10;
        }
        return sum;


    }




    public static int[] searchRange(int[] nums, int target) {
        int left = findStart(nums,target);
        int right = findEnd(nums,target);

        return new int[]{left,right};
    }

    public int[] twoSum(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right){
            if (nums[left] + nums[right] == target){
                return new int[]{nums[left],nums[right]};
            }else if (nums[left] + nums[right] > target){
                right --;
            }else {
                left ++;
            }
        }
        return new int[]{nums[left],nums[right]};
    }

    private static int findStart(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int res = -1;
        while (left <= right){
            int mid = left + (right - left >> 1);
            if (nums[mid]==target){
                res = mid;
                right = mid - 1;
            }else if (nums[mid] > target){
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return res;
    }

    private static int findEnd(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int res = -1;
        while (left <= right){
            int mid = left + (right - left >> 1);
            if (nums[mid] == target){
                res = mid;
                left = mid + 1;
            }else if (nums[mid] > target){
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return res;
    }

    public boolean isStraight(int[] nums) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int[] arr = new int[14];
        for (int i = 0;i < nums.length ; i ++){
            if (nums[i] == 0){
                continue;
            }else {
                max = Math.max(max,nums[i]);
                min = Math.min(min,nums[i]);
            }
            if (arr[nums[i]] == 1){
                return false;
            }
            arr[nums[i]] ++;
        }
        return Math.abs(max - min) <= 4  ;
    }


    public int[] exchange(int[] nums) {
        int l = -1;
        int r = nums.length;
        int left = 0;
        while (left < r){
            if (nums[left] % 2 == 1){
                left ++;
                l ++;
            }else {
                swap(nums,left,--r);
            }
        }
        return nums;
    }


    private void swap(int[] arr,int l,int r){
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }


    public int search(int[] nums, int target) {
        if (nums.length == 0){
            return -1;
        }
        int left = 0,right = nums.length - 1;
        while (left < right){
            int mid = left + (right - left >> 1);
            if (nums[mid] == target){
                return mid;
            }else if (nums[mid] < nums[right]){
                if (target > nums[mid] && target <= nums[right]){
                    left = mid + 1;
                }else {
                    right = mid - 1;
                }
            }else if (nums[mid] > nums[right]){
                if (target >= nums[left] && target < nums[mid] ){
                    right = mid - 1;
                }else {
                    left = mid + 1;
                }
            }else {

            }
        }
        return nums[left] == target ? left : -1;
    }


    public int[] singleNumbers(int[] nums) {
        int a = 0,b = 0,xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        int x = (xor & xor - 1);
        for (int num : nums) {
            if ((num & x) > 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        return new int[]{a,b};
    }








    public static int singleNumber(int[] nums) {

        // a 当前
        int a = 0,b = 0;
        for(int num : nums) {
            a = (a ^ num) & ~b;

            //
            b = (b ^ num) & ~a;
        }
        return a;
    }


    public int findSpecialInteger(int[] arr) {
        for (int i = 0,len = arr.length >> 2;i < arr.length - len; i ++){
            if (arr[i] == arr[i + len]){
                return  arr[i];
            }
        }
        return -1;
    }






    public static int findRepeatNumber(int[] nums) {
        for (int i = 0;i < nums.length; i ++){
            while (nums[i] != i){
                if (nums[nums[i]] == nums[i]){
                    return nums[i];
                }
                int temp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = temp;
            }
        }
        return -1;
    }

}
