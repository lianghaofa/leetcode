import java.util.ArrayList;
import java.util.List;

/**
 * @author LiangHaoFa
 * @version 1.0.0
 * @description //TODO
 * @date 2022/1/19 1:45
 */
public class TreeTraver {


    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }



    public int kthLargest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        TreeNode cur = root;
        TreeNode mostRight = null;
        while (cur != null){
            mostRight = cur.left;
            if (mostRight != null){
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else {
                    mostRight.right = null;
                }
            }
            list.add(cur.val);
            cur = cur.right;
        }
        if (k > list.size()){
            return -1;
        }else {
            return list.get(list.size() - k);
        }
    }

    private boolean MorrisIn(TreeNode treeNode){
        long pre = Long.MIN_VALUE;
        TreeNode cur = treeNode;
        TreeNode mostRight = null;
        while (cur != null){
            mostRight = cur.left;
            if (mostRight != null){
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else {
                    mostRight.right = null;
                }
            }
            if (pre >= cur.val){
                return false;
            }else {
                pre = cur.val;
            }
            cur = cur.right;
        }
        return true;
    }



}
