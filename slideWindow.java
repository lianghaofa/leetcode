import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author LiangHaoFa
 * @version 1.0.0
 * @description //TODO
 * @date 2022/1/31 17:41
 */
public class slideWindow {

    class WindowNode{
        int val;
        int index;
        WindowNode(int index,int val){
            this.val = val;
            this.index = index;
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        k = k % nums.length;
        if (k == 0){
            k ++;
        }
        Deque<WindowNode> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        int left = - k,index = 0;
        for (int i = 0; i < nums.length; i ++){
            // nums[i] in   window.size =  i - ( i - k + 1) + 1     nums[i - k] out
            // decrease
            while (!deque.isEmpty() && deque.getLast().val < nums[i]){
                deque.removeLast();
            }
            deque.addLast(new WindowNode(i,nums[i]));
            if (left >= 0){
                res[index ++] = deque.getFirst().val;
            }
            if (deque.getFirst().index <= left){
                deque.removeFirst();
            }
        }
        return res;
    }
}
