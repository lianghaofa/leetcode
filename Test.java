import java.util.ArrayList;
import java.util.List;

/**
 * @author LiangHaoFa
 * @version 1.0.0
 * @description //TODO test
 * @date 2022/1/11 17:03
 */
public class Test {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {
        }
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        System.out.println("test");
        Integer integer = 6;
        testInteger(integer);
        int i = 6;

        System.out.println(integer);
        testInt(i);
        System.out.println(i);

    }

    private static void testInteger(Integer val){
        val = val - 1;
    }


    private static void testInt(int val){
        if (val == 0){
            return;
        }
        val --;
        testInteger(val);
    }



}
