import com.sun.xml.internal.org.jvnet.mimepull.CleanUpExecutorFactory;

import java.util.*;

/**
 * @author LiangHaoFa
 * @version 1.0.0
 * @description //TODO
 * @date 2022/1/21 23:31
 */
public class ListTest {


    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }


    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        reverseList(node1);
    }


    public Node copyRandomList(Node head) {
        if (head == null){
            return null;
        }
        Node dummy = head;
        while (head != null){
            // generate a new node
            Node node = new Node(head.val);
            node.next = head.next;
            head.next = node;
            head = node.next;
        }
        head = dummy;
        Node randDummy = head.next;
        // set random node
        while (head != null) {
            if (head.random != null) {
                head.next.random = head.random.next;
            }
            head = head.next.next;
        }
        Node temp;
        head = dummy;
        // separate the node
        while (head != null){
            temp = head.next;
            head.next = temp.next;
            head = temp.next;
            if (head != null){
                temp.next = head.next;
            }else {
                temp.next = null;
            }
        }
        return randDummy;
    }




    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int len1 = 0,len2 = 0;
        ListNode travel = headA;
        while (travel != null){
            len1 ++;
            travel = travel.next;
        }
        travel = headB;
        while (travel != null){
            len1 ++;
            travel = travel.next;
        }
        int a = Math.abs(len1 - len2);
        while (a > 0){
            if (len1 > len2){
                headA = headA.next;
            }else {
                headB = headB.next;
            }
            a --;
        }
        while (headA != null && headB != null){

            if (headA.val == headB .val){
                return headA;
            }
            headA = headA.next;
            headB = headB.next;

        }
        return null;
    }




    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null){
            return null;
        }
        // in case of deleting the first node
        ListNode dummyNode = new ListNode();
        dummyNode.next = head;
        ListNode del = dummyNode;
        while (head != null){
            if (k < 0){
                del = del.next;
            }
            head = head.next;
            k --;
        }
        // delete the node

        return del.next;

    }






    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode next = null;
        while (head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static ListNode mergeKLists(ListNode[] lists) {

        return mergeList(lists,0,lists.length - 1);

    }

    public static ListNode mergeList(ListNode[] lists,int start,int end){
        if (start > end){
            return null;
        }
        if (start == end){
            return lists[start];
        }
        if (end - start == 1){
            return mergeTwoLists(lists[start],lists[end]);
        }
        System.out.println(start + " " + end );
        int mid = start + (end - start >> 1);
        System.out.println(start + " " + end + " " + mid);
        ListNode leftNode = mergeList(lists,start,mid);
        ListNode rightNode = mergeList(lists,mid + 1,end);
        return mergeTwoLists(leftNode,rightNode);
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null ) {
            return l2;
        }
        if (l2 == null){
            return l1;
        }
        ListNode node = new ListNode();
        ListNode pre = node;
        while (l1 != null && l2 != null){
            if (l1.val > l2.val){
                pre.next = l2;
                pre = l2;
                l2 = l2.next;
            }else {
                pre.next = l1;
                pre = l1;
                l1 = l1.next;
            }
        }
        if (l1 != null){
            pre.next = l1;
        }
        if (l2 != null){
            pre.next = l2;
        }
        return node.next;
    }

    public static ListNode mergeKLists1(ListNode[] lists) {

        if (lists.length <= 0 ){
            return null;
        }

        HashMap<Integer,ListNode> hashMap = new HashMap<>();
        // 首先 获取第一个
        ListNode head = null;
        ListNode pre =  null;
        Long val = Long.MAX_VALUE;
        int belong = -1;
        for (int i = 0; i < lists.length; i ++){
            ListNode list = lists[i];
            if (list != null && list.val < val){
                belong = i;
                val = Long.valueOf(list.val);
                head = list;
            }
            hashMap.put(i,lists[i]);
        }
        if (head == null){
            return null;
        }
        hashMap.put(belong,head.next);
        pre = head;

        while (true){
            val = Long.MAX_VALUE;
            belong = -1;
            ListNode node =  null;
            for (int i = 0 ; i < lists.length; i ++){
                ListNode j = hashMap.get(i);
                if (j != null && j.val < val){
                    belong = i;
                    val = Long.valueOf(j.val);
                    node = j;
                }
            }
            if (belong == -1 || node == null){
                break;
            }
            pre.next = node;
            pre = node;
            node = node.next;
            hashMap.put(belong,node);

        }

        return head;
    }

}
