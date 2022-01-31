

import java.util.*;

/**
 * @author LiangHaoFa
 * @version 1.0.0
 * @description //TODO
 * @date 2022/1/18 9:35
 */
public class Str {

    public static void main(String[] args) {
//        System.out.println(numDecodings("12"));
//        System.out.println(numDecodings("226"));
//        System.out.println(numDecodings("0"));
//        System.out.println(numDecodings("06"));
//        System.out.println(numDecodings("12565464564"));
//        System.out.println(numDecodings("1256"));
//        System.out.println(numDecodings("10"));

        //System.out.println(reverseWords("a good   example"));
        String s = "adbcd";
        StringBuffer stringBuffer = new StringBuffer(s);
        System.out.println(stringBuffer.substring(0,0));
    }

    public int countSegments(String s) {
        String[] str = s.trim().split(" ");
        int res = 0;
        for (int i = 0; i < str.length; i ++){
            if  (!"".equals(str[i])){
                res ++;
            }
        }
        return res;
    }


    public List<List<String>> partition(String s) {
        List<String> list = new ArrayList<>();
        List<List<String>> lists = new ArrayList<>();
        par(s,0,lists,list);
        //lists = new ArrayList<>();
        return lists;
    }

    private void par(String s,int start,List<List<String>> lists ,List<String> strings){
        if (start == s.length()){
            lists.add(new ArrayList<>(strings));
            return;
        }
        for (int i = start; i <= s.length(); i ++){
            if (isPar(s.substring(start,i))){
                strings.add(s.substring(start,i));
                par(s,i,lists,strings);
                strings.remove(strings.size() - 1);
            }
        }
    }

    private boolean isPar(String str){
        if (str.length() == 0){
            return false;
        }
        int begin = 0;
        int end = str.length() - 1;
        while (begin < end){
            if (str.charAt(begin) != str.charAt(end)){
                return false;
            }
            begin ++;
            end --;
        }
        return true;
    }


    public static boolean exist(char[][] board, String word) {
        if (board.length == 0){
            return false;
        }
        boolean[][] matrix = new boolean[board.length][board[0].length];
        char[] chars = word.toCharArray();
        for (int i = 0 ; i < board.length; i ++){
            for (int j = 0; j < board[0].length ; j ++){
                if (check(board,matrix,chars,0,i,j)){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean check(char[][] board,boolean[][] matrix, char[] word,int len,int row,int col) {
        if (len == word.length){
            return true;
        }
        if (col < 0 || col >= matrix[0].length || row < 0 || row >= matrix.length || matrix[row][col] || board[row][col] != word[len]){
            return false;
        }
        matrix[row][col] = true;
        boolean res = check(board,matrix,word,len + 1, row - 1,col) || check(board,matrix,word,len + 1, row ,col + 1)
                || check(board,matrix,word,len + 1, row + 1,col) || check(board,matrix,word,len + 1, row,col - 1);
        matrix[row][col] = false;
        return res;
    }


    public static String reverseWords(String s) {
        String str = s.trim();
        String[] strings = s.split(" ");
        StringBuilder res = new StringBuilder();
        for (int i = strings.length - 1; i >= 0; i --){
            if ("".equals(strings[i])){
                continue;
            }
            res.append(strings[i]).append(" ");
        }
        return res.toString().trim();
    }


    public String reverseLeftWords(String s, int n) {
        n = n % s.length();
        return reverseStr(reverseStr(s.substring(0,n)) + reverseStr(s.substring(n,s.length())));
    }

    public String reverseStr(String s) {
        StringBuffer stringBuffer = new StringBuffer(s);
        int left = 0;
        int right = stringBuffer.length() - 1;
        while (left < right){
            char c = stringBuffer.charAt(left);
            stringBuffer.setCharAt(left++,stringBuffer.charAt(right));
            stringBuffer.setCharAt(right--,c);
        }
        return stringBuffer.toString();
    }






    public static int numDecodings(String s) {
        return decode(s,0);
    }

    private static int decode(String s, int i) {
        if (i == s.length()){
            return 0;
        }
        if (i == s.length() - 1 && s.charAt(i) > '0' && s.charAt(i) <= '9'){
            return s.charAt(i) > '0' && s.charAt(i) <= '9' ? 1 : 0;
        }
        if (i == s.length() - 2){
            int r = 0;
            if (s.charAt(i) > '0' && s.charAt(i) <= '9'){
                r = decode(s,i + 1);
            }
            return r + (s.charAt(i) > '0' && s.charAt(i) <= '2' && s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '6' ? 1 : 0);
        }
        int a = 0;
        int b = 0;
        // 取一位
        if (s.charAt(i) >= '1' && s.charAt(i) <= '9' ){
            a = decode(s,i + 1);
        }
        // 取二位
        if (s.charAt(i) > '0' && s.charAt(i) <= '2' && i + 2 < s.length()){
            b =  decode(s,i + 2);
        }
        return a + b;
    }


    public static String simplifyPath(String path) {
        Deque<String> deque = new ArrayDeque<>();

        String[] strs = path.split("/");
        for (int i = 0;i < strs.length ; i ++){
            if ("..".equals(strs[i])){
                if (deque.size() >= 1){
                    if (".".equals(deque.getLast())){
                        i --;
                    }
                    deque.removeLast();
                }
            }else if (".".equals(strs[i])){
                // do nothing
            } else if (strs[i].length() >= 1){
                deque.addLast(strs[i]);
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        while (deque.size() > 0){
            stringBuffer.append("/").append(deque.getFirst());
            deque.removeFirst();
        }
        String res = stringBuffer.toString();
        return res.length() == 0 ? "/" : res;
    }

    public String replaceSpace(String s) {


        StringBuffer stringBuffer = new StringBuffer();
        for (int i = s.length() - 1 ;i >=0 ; i --){
            if (s.charAt(i) != ' '){
                stringBuffer.insert(0,s.charAt(i));
            }else {
                stringBuffer.insert(0,"%20");
            }
        }
        return stringBuffer.toString();
    }


    public static boolean isInterleave(String s1, String s2, String s3) {

        int s1i = 0;
        int s2i = 0;
        boolean innerT = true;
        for (int i = 0 ;i < s3.length(); i ++){

            // 如果一样，两种可能性都要尝试  只要有一种可能性成立
            if (s1i < s1.length() && s2i < s2.length() && s3.charAt(i) == s1.charAt(s1i) && s3.charAt(i) == s2.charAt(s2i)){
                if (isInterleave(s1.substring(s1i + 1),s2.substring(s2i),s3.substring(i + 1)) == false && isInterleave(s1.substring(s1i),s2.substring(s2i + 1),s3.substring(i + 1)) == false){

                }

                continue;
            }

            // 如果不一样  1.有相等的=取相等  不相等=返回false
            if (s1i < s1.length() && s3.charAt(i) == s1.charAt(s1i)){
                s1i ++;
            }else if (s2i < s2.length() && s3.charAt(i) == s2.charAt(s2i)){
                s2i ++;
            }else {
                return false;
            }
        }

        return innerT;
    }

    public static int lengthOfLastWord(String s) {
        String str = s.trim();
        int i = str.length() - 1;
        for (; i > 0 ;i --){
            if (str.charAt(i) == ' '){
                break;
            }
        }
        return str.length() - i - 1;
    }

    public static boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() -1;
        char[] chars = s.toCharArray();
        while (left < right){
            if (chars[left] != chars[right]){
                return isPalindrome(chars,left + 1,right) || isPalindrome(chars,left ,right -1 );
            }else {
                right --;
                left ++;
            }
        }
        return true;
    }

    public static boolean isPalindrome(char[] chars,int start,int end){

        while (start < end){
            if (chars[start] != chars[end]){
                return false;
            }
            start ++;
            end --;
        }
        return true;
    }


}
