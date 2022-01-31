import java.util.*;

/**
 * @author LiangHaoFa
 * @version 1.0.0
 * @description //TODO
 * @date 2022/1/26 22:09
 */
public class MergeIntervals {


    public static void main(String[] args) {


    }

    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> list = new ArrayList<>();
        for (int j = 1;j <= 9; j ++){
            int res = 0;
            for (int i = j ;i <= 9; i ++){
                res = res + i;
                if (res >= low && res <= high){
                    list.add(res);
                }
                res *= 10;
            }
        }
        Collections.sort(list);
        return list;
    }

    private int findHigh(int i){
        if (i < 10){
            return 0;
        }
        int res = 1;
        while (i / 10 > 0){
            res *= 10;
            i /= 10;
        }
        return res;
    }


    public boolean checkPerfectNumber(int num) {
        if (num <= 5){
            return false;
        }
        int res = num;
        for (int i = 2;i * i <= num; i ++){
            if (num % i == 0){
                res -= (i + num/i);
            }
        }
        return res == 1;
    }




    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 1){
            return intervals;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return 0;
            }
        });
        List<int[]> list = new ArrayList<>();
        int left = intervals[0][0], right = intervals[0][1],i =1;
        while (i < intervals.length){
            // intervals[i][1] >=  right
            if (intervals[i][0] >= left && intervals[i][0] <= right && intervals[i][1] >= right){
                right = intervals[i][1];
            }else if (intervals[i][0] >= left && intervals[i][0] <= right && intervals[i][1] < right){
                // intervals[i][1] < right
                // do nothing
            } else {
                // push the left and right
                list.add(new int[]{left,right});
                left = intervals[i][0];
                right = intervals[i][1];
            }
            i ++;
        }
        if (i == intervals.length){
            // push the left and right
            list.add(new int[]{left,right});
        }
        return list.toArray(new int[list.size()][2]);
    }




}
