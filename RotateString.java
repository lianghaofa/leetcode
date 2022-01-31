import java.util.Arrays;

/**
 * @author LiangHaoFa
 * @version 1.0.0
 * @description // TODO
 * @date 2022/1/11 23:25
 */
public class RotateString {

    public static void main(String[] args) {

        String s = "abcdefg";
        int k = 8;
        reverseStr( s,  k);


    }

    public boolean rotateString(String s, String goal) {
        if (s.length() == 0 || s.length() != goal.length()){
            return false;
        }

        for (int i = 0;i < s.length();i ++){
            String g =  goal.substring(i,goal.length()) + goal.substring(0,i);
            if (g.equals(s)){
                return true;
            }
        }
        return false;
    }

    public static String restoreString(String s, int[] indices) {
        char[] chars = new char[s.length()];
        for (int i = 0; i < indices.length; i ++){
            if (indices[i] < indices.length && indices[i] >= 0){
                chars[indices[i]]=s.charAt(i);
            }
        }
        return String.valueOf(chars);
    }


    public static String reverseStr(String s, int k) {

        char[] arr = s.toCharArray();
        int loop = 0;
        while ((loop * k+ (k - 1) < s.length()) || (k >= s.length() && loop == 0)){

            swap(arr,loop*k,k >= s.length() ? (s.length() - 1) : loop * k+ (k - 1));
            loop +=2;
        }
        return String.valueOf(arr);
    }


    private static void swap(char[] arr,int start,int end){

        while (start < end){
            char c = arr[start];
            arr[start] = arr[end];
            arr[end] = c;
            start ++;
            end --;
        }
    }




}
