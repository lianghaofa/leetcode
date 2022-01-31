import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.*;

/**
 * @author LiangHaoFa
 * @version 1.0.0
 * @description //TODO
 * @date 2022/1/13 16:34
 */
public class Tree {

  public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
//    public class Node {
//        int val;
//        Node left;
//        Node right;
//        Node(int x) { val = x; }
//    }


    public static void main(String[] args) {
        TreeNode tree3 = new TreeNode(3);
        TreeNode tree4 = new TreeNode(4);
        TreeNode tree5 = new TreeNode(5);
        TreeNode tree1 = new TreeNode(1);
        TreeNode tree2 = new TreeNode(2);
        TreeNode tree6 = new TreeNode(6);



        tree2.left = tree1;

        findMode(tree2);
    }

    public static int[] findMode(TreeNode root) {
      
      int pre = 0;
      int maxFrequent = Integer.MIN_VALUE;
      int frequent = 0;
      int val = 0;
      TreeNode fNode = root;
      TreeNode cur = root,mostRight = null;
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
          System.out.println(cur.val);
          if (frequent == 0){
              pre = cur.val;
              frequent ++;
              val = cur.val;
              maxFrequent = frequent;
              fNode = cur;
          }else {
              if (pre != cur.val){
                  pre = cur.val;
                  frequent = 1;
              }else {
                  frequent ++;
              }
              if (frequent >= maxFrequent){
                  val = cur.val;
              }
              if (frequent > maxFrequent){
                  maxFrequent = frequent;
                  fNode = cur;
              }
          }
          cur = cur.right;
      }

      Set<Integer> set = new HashSet<>();
        cur = root;
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
            if (cur.val >= fNode.val){
                set.add(cur.val);
            }
            if (cur.val == val){
                break;
            }
            cur = cur.right;
        }

      int[] array = new int[set.size()];

        int i = 0;
        for (Integer s : set) {
            array[i ++] = s;
        }

      return array;
    }
    
    
    

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;
        public Node() {}
        public Node(int _val) {
            val = _val;
        }
        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };



    public Node connect(Node root) {
        if (root == null){
            return null;
        }
        Node cur = root,next = root,temp = null,dummy = null;
        while (cur != null){
            if (cur.left != null){
                next.next = cur.left;
                next = cur.left;
                dummy = dummy == null ? cur.left : dummy;
            }
            if (cur.right != null){
                next.next = cur.right;
                next = cur.right;
                dummy = dummy == null ? cur.right : dummy;
            }
            temp = cur;
            cur = cur.next;
            if (cur == dummy){
                dummy = null;
                temp.next = null;
            }
        }
        return root;
    }



    // 返回右子树下一层最左的节点
    private Node connLeft(Node node) {
        if (node == null){
            return null;
        }

        Node left = connLeft(node.right);
        if (left == null){
            return null;
        }
        left.next = connRight(node.left);
        return left;
    }

    // 返回左子树下一层最右的节点
    private Node connRight(Node node) {
        if (node == null){
            return null;
        }
        Node left = connLeft(node.right);
        Node right = connRight(node.left);
        left.next = right;
        return right;
    }



    public static void flatten(TreeNode root) {
        // Morris preorder
        TreeNode cur = root;
        TreeNode mostRight = null, pre = null,next = null;
        while (cur != null){
            mostRight = cur.left;
            if (mostRight != null){
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if (mostRight.right == cur){
                    mostRight = null;
                }else {
                    mostRight.right = cur;
                    // preorder
                    if (pre != null){
                        pre.left = cur;
                    }
                    pre = cur;
                    cur = cur.left;
                    continue;
                }
            }else {
                // preorder
                if (pre != null){
                    pre.left = cur;
                }
                pre = cur;
            }
            cur = cur.right;
        }
        // reverse
        cur = root;
        while (cur != null){
            next = cur.left;
            cur.right = next;
            cur.left = null;
            cur = next;
        }
    }

    public Node treeToDoublyList(Node root) {
        if (root == null){
            return null;
        }
        // Morris inorder
        Node cur = root;
        Node mostRight = null, pre = null,head = null;
        while (cur != null){
            mostRight = cur.left;
            if (mostRight != null){
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if (mostRight.right == cur){
                    mostRight = null;
                }else {
                    mostRight.right = cur;

                    cur = cur.left;
                    continue;
                }
            }
            if (head == null){
                head = cur;
            }else {
                pre.right = cur;
                cur.left = pre;
            }
            pre = cur;
            cur = cur.right;
        }
        pre.right = head;
        head.left = pre;
        return head;
    }


    public int findBottomLeftValue(TreeNode root) {
      if (root == null){
          return -1;
      }
      Deque<TreeNode> queue = new LinkedList<>();
      queue.addLast(root);
      int count = 1;
      int leftNodeVal = root.val;
      while (count > 0){
          leftNodeVal = queue.getFirst().val;
          while (count > 0){
              TreeNode node = queue.removeFirst();
              count --;
              if (node.left != null){
                  queue.addLast(node.left);
              }
              if (node.right != null){
                  queue.addLast(node.right);
              }
          }
          count = queue.size();
      }
      return leftNodeVal;
    }




    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        sum(root,target,list,lists);
        return lists;
    }

    private void sum(TreeNode node, int target, List<Integer> list, List<List<Integer>> lists) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null && target == node.val){
            list.add(node.val);
            lists.add(new ArrayList<>(list));
            list.remove(list.size() - 1);
            return;
        }
        list.add(node.val);
        sum(node.left, target - node.val, list, lists);
        sum(node.right, target - node.val, list, lists);
        list.remove(list.size() - 1);
    }



    public void recoverTree(TreeNode root) {
        TreeNode pre = new TreeNode(Integer.MIN_VALUE);
        TreeNode mostRight,node1 = null,node2 = null,cur = root;
        // find exception by inorder
        while (cur != null){
            mostRight = cur.left;
            if (mostRight != null){
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if (mostRight.right == cur){
                    mostRight.right = null;
                }else{
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }
            }
            // handle start
            if (node1 == null && cur.val < pre.val ){
                node1 = pre;
                node2 = cur;
            }else if (node1 != null &&  cur.val < pre.val){
                node2 = cur;
            }
            pre = cur;
            // handle end
            cur = cur.right;
        }
        int temp = node1.val;
        node1.val = node2.val;
        node2.val = temp;
    }

    private void swap(TreeNode parent1,TreeNode node1,TreeNode parent2,TreeNode node2){

      if (parent1 != null){
          if (parent1.left == node1){
              parent1.left = node2;
          }else {
              parent1.right = node2;
          }
      }
      if (parent2 != null){
          if (parent2.left == node2){
              parent2.left = node1;
          }else {
              parent2.right = node1;
          }
      }
      TreeNode temp;
      temp = node1.left;
      node1.left = node2.left;
      node2.left = temp;
      temp = node1.right;
      node1.right = node2.right;
      node2.right = temp;
    }





    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
      if (root == null){
          return null;
      }
      if (root == p || root == q){
          return root;
      }
      TreeNode left = lowestCommonAncestor(root.left,p,q);
      TreeNode right = lowestCommonAncestor(root.right,p,q);
      if (left != null && right != null){
          return root;
      }else if (left != null){
          return left;
      }else {
          return right;
      }
    }

    private boolean[] findLowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
      if (root == null){
          return new boolean[]{false,false};
      }

      return findLowestCommonAncestor(root.left, p,q);

    }




    public List<Integer> rightSideView(TreeNode root) {
      List<Integer> list = new ArrayList<>();
      if (root == null){
          return list;
      }
      Queue<TreeNode> queue = new LinkedList<>();
      queue.add(root);
      int count = 1;
      while (count > 0){
          while (!queue.isEmpty() && count > 0){
              TreeNode node = queue.poll();
              if (count == 1){
                  list.add(node.val);
              }
              count --;
              if (node.left != null){
                  queue.add(node.left);
              }
              if (node.right != null){
                  queue.add(node.right);
              }
          }
          count = queue.size();
      }
      return list;
    }


    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null){
            return lists;
        }
        Deque<TreeNode> nodes = new LinkedList<>();
        nodes.addLast(root);
        int count = 1;
        boolean order = true;
        while (count > 0){
             List<Integer> list = new ArrayList<>();
             while (!nodes.isEmpty() && count > 0){
                 TreeNode treeNode = nodes.removeFirst();
                list.add(treeNode.val);
                count --;
                if (treeNode.left != null){
                    nodes.addLast(treeNode.left);
                }
                if(treeNode.right != null){
                    nodes.addLast(treeNode.right);
                }
            }
            count = nodes.size();
            if (order){
                Collections.reverse(list);
            }
            order = !order;
            lists.add(list);
        }
        return lists;
    }



    public boolean isSymmetric(TreeNode root){
        if (root == null){
            return true;
        }
        return symmetric(root.left,root.right);
    }

    public boolean symmetric(TreeNode root1,TreeNode root2){
        if (root1 == null && root2 == null){
            return true;
        }
        if (root1 != null && root2 != null){
            return root1.val == root2.val && symmetric(root1.left,root2.right) && symmetric(root1.right,root2.left);
        }else {
            return false;
        }
    }

    public static boolean isSubStructure(TreeNode A, TreeNode B){
        if (A == null || B == null){
            return false;
        }
        return subStructure(A, B,false);
    }

    public static boolean subStructure(TreeNode A, TreeNode B,boolean connect) {
        if (B == null){
            return true;
        }
        if (A ==  null){
            return false;
        }
        return A.val == B.val && subStructure(A.left,B.left,true) && subStructure(A.right,B.right,true)
                || (!connect && subStructure(A.left,B,false)
                || (!connect && subStructure(A.right,B,false))) ;
    }



    int dia = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        diameterOfBinaryTree(root);
      return  dia;
    }

    // arr[0]   arr[1]
    private int diameter(TreeNode root) {
      if (root == null){
          return 0;
      }
      int left =  diameter(root.left);
      int right = diameter(root.right);
      dia = Math.max(left + right,dia);
      return Math.max(left,right) + 1;
    }


    public static TreeNode increasingBST(TreeNode root) {
        // Morris inorder
        boolean first = true;
        TreeNode cur = root;
        TreeNode preNode = null,node = null,mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            // start
            if (first) {
                // record the first node
                node = cur;
                first = false;
            }else {
                preNode.right = cur;
            }
            preNode = cur;
            cur.left = null;
            // end
            cur = cur.right;
        }
        return node;
    }









    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null){
            return null;
        }
        TreeNode next = null;
        boolean t = false;
        TreeNode cur = root;
        TreeNode mostRight = null;
        while (cur != null){
            mostRight = cur.left;
            if (mostRight != null){
                // mostRight != null  找左子树最右的节点
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight =  mostRight.right;
                }
                if (mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else {
                    mostRight.right = null;
                }
            }
            // cur 要往右
            if (t){
                return cur;
            }
            if (cur == p){
                t = true;
            }
            cur = cur.right;
        }
        return next;
    }


    public static void morrisIn(Node head){

      if (head == null){
          return;
      }
      Node cur = head;
      Node mostRight = null;
      while (cur != null){
          mostRight = cur.left;
          if (mostRight != null){
              // mostRight != null  找左子树最右的节点
              while (mostRight.right != null && mostRight.right != cur){
                  mostRight =  mostRight.right;
              }
              if (mostRight.right == null){
                  mostRight.right = cur;
                  cur = cur.left;
                  continue;
              }else {
                  mostRight.right = null;
              }
          }
          // cur 要往右
          System.out.println(cur.val + " ");
          cur = cur.right;
      }

    }


    public static void morrisPre(Node head){
        if (head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null){
            mostRight = cur.left;
            if (mostRight != null){
                // mostRight != null  找左子树最右的节点
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight =  mostRight.right;
                }
                if (mostRight.right == null){
                    mostRight.right = cur;
                    // 先序  往左遍历前
                    System.out.println(cur.val + " ");
                    cur = cur.left;

                    continue;
                }else {
                    // 第二次回来该节点   把mostRight.right 置为null
                    mostRight.right = null;
                }
            }else {
                System.out.println(cur.val + " ");
            }
            cur = cur.right;
        }
    }


    public static void morrisPost(Node head){
        if (head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null){
            mostRight = cur.left;
            if (mostRight != null){
                // mostRight != null  找左子树最右的节点
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight =  mostRight.right;
                }
                if (mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else {
                    // 第二次回来该节点   把mostRight.right 置为null    后序遍历 逆序打印左子树右边界

                    printEdge(cur.left);
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }
    }
    public static void printEdge(Node head) {
        Node tail = reverseEdge(head);
        Node cur = tail;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    public static Node reverseEdge(Node from) {
        Node pre = null;
        Node next = null;
        while (from != null) {
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
    }

    private static void printEdge1(Node head){
      Stack<Node> stack =  new Stack<>();
      while (head != null){
          stack.push(head);
          head = head.right;
      }
      while (!stack.empty()){
          Node node = stack.pop();
          System.out.println(node.val);
      }
    }



    public int reverseBits(int n) {

      int a = 0;





      return n ^ Integer.MIN_VALUE;

    }


    public static int maxAreaOfIsland(int[][] grid) {

      if (grid.length <= 0){
          return 0;
      }

      int max = 0;

      for (int i = 0 ; i < grid.length; i ++){
          for (int j = 0; j < grid[0].length; j ++){
              if (grid[i][j] == 1){
                  max = Math.max(max,infect(grid,i,j));

              }
          }
      }
      return max;
    }


    private static int infect(int[][] grid,int row,int col){
      if (row < 0 || row >= grid.length){
          return 0;
      }
      if (col < 0 || col >= grid[0].length){
          return 0;
      }
      int sum = 0;
      if (grid[row][col] == 1){
          sum ++;
          grid[row][col] = 2;
          //up

          sum += infect(grid,row - 1,col);
          //down
          sum += infect(grid,row + 1,col);
          //left
          sum += infect(grid,row,col - 1);
          //right
          sum += infect(grid,row,col + 1);

      }
      return sum;
    }


    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
      if (root1 == null){
          return root2;
      }
      if (root2 == null){
          return root1;
      }
      merge(root1,root2);
      return root1;
    }

    private TreeNode merge(TreeNode root1, TreeNode root2){
        if (root1 == null){
            return root2;
        }
        if (root2 == null){
            return root1;
        }
        TreeNode left =  merge(root1.left,root2.left);
        TreeNode right = merge(root1.right,root2.right);
        root1.val = root1.val + root2.val;
        root1.left = left;
        root1.right = right;
        return root1;

    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
      TreeNode treeNode = new TreeNode(val);
      if (root == null){
          return treeNode;
      }
      insertBST(root,treeNode);
      return root;
    }

    private void insertBST(TreeNode root,TreeNode treeNode){
      if (root == null){
          return ;
      }
      if (root.val > treeNode.val && root.left == null){
          root.left = treeNode;
      }else if (root.val > treeNode.val){
          insertBST(root.left,treeNode);
      }else if (root.val < treeNode.val && root.right == null){
          root.right = treeNode;
      }else if (root.val < treeNode.val ){
          insertBST(root.right,treeNode);
      }else {

      }
    }






    public TreeNode searchBST(TreeNode root, int val) {
      if (root == null){
          return root;
      }
      if (root.val == val){
          return root;
      }
      // search right
      if (val > root.val){
        return   searchBST(root.right,val);
      }else {
          return   searchBST(root.left,val);
      }
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
      if (root == null){
          return false;
      }

      return hasSum(root,targetSum);
    }

    private boolean hasSum(TreeNode root, int targetSum){

        if (root == null){
            return false;
        }
        if (root.left == null && root.right == null && root.val == targetSum ){
            return true;
        }
        if (root.left == null && root.right == null ){
            return false;
        }

        return hasSum(root.left,targetSum - root.val) || hasSum(root.right,targetSum - root.val);
    }








    public int maxDepth(TreeNode root) {
      if (root == null){
          return 0;
      }
      int l = maxDepth(root.left);
      int r = maxDepth(root.right);
      return Math.max(l, r) + 1;
     }

    private void pushRight(TreeNode treeNode,Stack<TreeNode> treeNodes,LinkedList<Integer> list){

        if (treeNode == null){
            return;
        }
        while (treeNode != null){
            list.addFirst(treeNode.val);
            treeNodes.push(treeNode);
            treeNode = treeNode.right;
        }
    }















}
