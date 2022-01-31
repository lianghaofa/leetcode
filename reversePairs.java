/**
 * @author LiangHaoFa
 * @version 1.0.0
 * @description //TODO
 * @date 2022/1/19 1:02
 */
public class reversePairs {

    public int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        return process(nums, 0, nums.length - 1);

    }

    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid)
                + process(arr, mid + 1, r)
                + merge(arr, l, mid, r);
    }

    public static int merge(int[] arr, int L, int M, int R) {
        // 准备辅助空间，额外空间复杂度为O(N)
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        int res = 0;
        while(p1 <= M && p2 <= R) {
            res += arr[p1] > arr[p2] ? (R - p2 + 1) : 0;
            help[i++] = arr[p1] > arr[p2] ? arr[p1++] : arr[p2++];

        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return res;
    }

}
