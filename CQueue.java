import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author LiangHaoFa
 * @version 1.0.0
 * @description //TODO
 * @date 2022/1/25 21:28
 */
public class CQueue {

    public static void main(String[] args) {
        CQueue cQueue = new CQueue();
        cQueue.deleteHead();
        cQueue.appendTail(5);
        cQueue.appendTail(2);
        System.out.println(cQueue.deleteHead());;
        System.out.println(cQueue.deleteHead());;
    }


    Deque<Integer> stack = new LinkedList<>();
    public CQueue() {
    }

    public void appendTail(int value) {
        stack.addLast(value);
    }

    public int deleteHead() {
        int res = -1;
        if (stack.size() == 0){
            return -1;
        }else {
            res = stack.getFirst();
            stack.removeFirst();
        }
        return res;
    }
}
