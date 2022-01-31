import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author LiangHaoFa
 * @version 1.0.0
 * @description //TODO
 * @date 2022/1/27 21:31
 */
public class MinStack {


    Deque<Integer> deque;
    /** initialize your data structure here. */
    public MinStack() {
        deque = new LinkedList<>();
    }

    public void push(int val) {
        deque.addLast(val);
    }

    public void pop() {
        deque.removeLast();
    }

    public int top() {
        return deque.getLast();
    }

    public int min() {
        return -1;
    }
}
