/**
 * @author LiangHaoFa
 * @version 1.0.0
 * @description //TODO
 * @date 2022/1/15 21:54
 */
public class StringAlo {





    public static void main(String[] args) {
        String haystack = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String needle = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        System.out.println(strStr(haystack,needle));

    }

    public static int strStr(String haystack, String needle) {
        if (haystack.length() < needle.length()){
            return -1;
        }
        // construct next array
        int[] arr = new int[needle.length()];
        for (int i = 0;i < needle.length(); i ++){
            int max = 0;
            for (int j = 1;j < i ; j ++){
                if (needle.substring(0, j).equals(needle.substring(i - j, i))){
                    max = Math.max(j,max);
                }
            }
            arr[i] = max;
        }


        // iterator haystack
        int i = 0,j = 0;
        while (i  < haystack.length() && j < needle.length()){
            if (haystack.charAt(i) != needle.charAt(j)){
                if (j == 0){
                    i ++;
                }else {
                    j = arr[j];
                }
            }else {
                i ++;
                j ++;
            }
        }
        if (j == needle.length()){
            return i - needle.length();
        }else {
            return -1;
        }
    }


    public static int nextArr(char[] chars){
        int[] next = new int[chars.length + 1];
        next[0] = -1;
        next[1] = 0;
        int pos = 2;
        int cn = 0;
        while (pos < next.length){
            if (chars[pos - 1] == chars[cn]){
                next[pos ++] = ++ cn;
            }else if (cn > 0){
                cn = next[cn];
            }else {
                next[pos ++] = 0;
            }
        }
        return next[next.length - 1];
    }












}
