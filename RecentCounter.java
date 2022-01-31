import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author LiangHaoFa
 * @version 1.0.0
 * @description //TODO
 * @date 2022/1/25 16:17
 */
public class RecentCounter {



    Queue<Integer> queue = new LinkedList<>();
    public RecentCounter() {

    }


    public int ping(int t) {
        queue.add(t);
        Integer first = queue.peek();
        while (first < t - 3000){
            queue.poll();
            if (queue.size() > 0){
                first = queue.peek();
            }else {
                break;
            }
        }
        return queue.size();

    }

}
