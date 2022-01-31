/**
 * @author LiangHaoFa
 * @version 1.0.0
 * @description //TODO
 * @date 2022/1/23 0:06
 */
public  class NumArrayByTree {


    public static void main(String[] args) {
        int[] arr = new int[]{0,9,5,7,3};
        NumArrayByTree numArray = new NumArrayByTree(arr);
        numArray.update(1,2);
    }




    private SegmentTree head;
    public NumArrayByTree(int[] nums) {
        if (nums.length == 0){
            // do nothing
        }else if (nums.length == 1){
            head = new SegmentTree(nums[0]);
            head.left = nums[0];
            head.right = nums[0];
        }else {
            head = merge(nums,0,nums.length - 1);
            System.out.println(head.val);

        }

    }

    public void update(int index, int val) {
        treeUpdate(head,index,val);
    }


    public int sumRange(int left, int right) {
        return treeSum(head,left,right);
    }

    public class SegmentTree{

        private int left;
        private int right;
        private SegmentTree leftTree;
        private SegmentTree rightTree;
        private int val;
        public SegmentTree(int val){
            this.val = val;
        }

        public void setVal(int val){
            this.val = val;
        }

    }

    private int treeSum(SegmentTree head, int left, int right) {
        if (head == null){
            return 0;
        }
        if (left == head.left && right == head.right){
            return head.val;
        }
        if (head.leftTree == null || head.rightTree == null ){
            return 0;
        }
        int leftVal = head.leftTree.right;
        int rightVal = head.rightTree.left;
        // all in left side
        if (right <= leftVal){
            return treeSum(head.leftTree,left,right);
        }else if (left >= rightVal){
            // all in right side
            return treeSum(head.rightTree,left,right);
        }else {
            // left side and right side
            return treeSum(head.leftTree,left,leftVal) + treeSum(head.rightTree,rightVal,right);
        }
    }

    public SegmentTree merge(int[] arr,int left,int right){
        if (left > right){
            return null;
        }
        if (left == right){
            SegmentTree segmentTree = new SegmentTree(arr[left]);
            segmentTree.left = left;
            segmentTree.right = right;
            return segmentTree;
        }
        int mid = left + (right - left) / 2;
        SegmentTree leftTree = merge(arr,left,mid);
        SegmentTree rightTree = merge(arr,mid + 1,right);
        SegmentTree segmentTree = new SegmentTree(leftTree.val + rightTree.val);
        segmentTree.left = leftTree.left;
        segmentTree.right = rightTree.right;
        segmentTree.leftTree = leftTree;
        segmentTree.rightTree = rightTree;
        return segmentTree;
    }

    private int treeUpdate(SegmentTree head, int index, int val) {
        // 检查
        if (head == null){
            return 0;
        }
        int v = 0;

        if (head.leftTree == null && head.rightTree == null && head.left == index && head.right == index){
            v = head.val - val;
            head.val = val;
        }else if (head.leftTree == null || head.rightTree == null){

        } else if (index > head.leftTree.right){
            v = treeUpdate(head.rightTree,index,val);
            head.val -= v;
        }else if (index < head.rightTree.left){
            v = treeUpdate(head.leftTree,index,val);
            head.val -= v;
        }else {

        }
        return v;
    }







}
