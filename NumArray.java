import com.sun.corba.se.impl.resolver.SplitLocalResolverImpl;

/**
 * @author LiangHaoFa
 * @version 1.0.0
 * @description //TODO
 * @date 2022/1/23 13:34
 */
class NumArray {


    public static void main(String[] args) {
//        int[] arr = new int[]{1,3,5};
//        NumArray numArray = new NumArray(arr);
//        numArray.update(1,2);
//        System.out.println(numArray.sumRange(0,2));

        int[] arr = new int[]{-1};
        NumArray numArray = new NumArray(arr);
        System.out.println(numArray.sumRange(0,0));
        numArray.update(0,-1);
        System.out.println(numArray.sumRange(0,0));

    }

    int[] tree;
    int[] nums;
    private int lowbit(int x){
        return (x & -x);
    }

    public NumArray(int[] nums) {
        this.nums = nums;
        // generate tree
        tree = new int[nums.length + 1];
        for (int i = 1;i < tree.length; i ++){
            // odd copy
            tree[i] = nums[i - 1];
            // even cal   i 的二进制中末尾 0 的个数
            for (int j = i - lowbit(i) + 1;   j < i  ; j ++){
                tree[i] += nums[j - 1];
            }
        }
    }

    public void update(int index, int val) {
        int m = nums[index] - val;
        nums[index] = val;
        int parent = index + 1;
        while (parent < tree.length){
            tree[parent] = tree[parent] - m;
            parent = parent + lowbit(parent);
        }
    }

    private int query(int index){
        int sum = 0;
        for (int i = index + 1; i > 0; i -= lowbit(i)){
            sum += tree[i];
        }
        return sum;
    }

    public int sumRange(int left, int right) {
        return query(right) - query(left - 1);
    }

}
