/**
 * @author LiangHaoFa
 * @version 1.0.0
 * @description //TODO 392. Is Subsequence
 * @date 2022/1/11 20:48
 */
public class IsUnique {



    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0){
            return true;
        }
        int i = 0,j = 0;
        while (i < s.length() && j < t.length()){
            if (s.charAt(i) == t.charAt(j ++)){
                i ++;
            }
            if (i == s.length()){
                return true;
            }
        }
        return false;
    }
}
