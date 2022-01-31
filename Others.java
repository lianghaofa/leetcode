/**
 * @author LiangHaoFa
 * @version 1.0.0
 * @description //TODO
 * @date 2022/1/23 23:07
 */
public class Others {

    public static void main(String[] args) {

    }



    public String multiply(String num1, String num2) {
        char[] res = new char[num1.length() + num2.length()];
        int len = res.length - 1;

        for (int i = num1.length() - 1,k = 0; i >= 0 ; i --,k ++){
            if (num1.charAt(i) == '-'){
                break;
            }
            int count = 0;
            for (int j = num2.length() - 1, m = 0; j >= 0; j --,m ++){
                if (num2.charAt(j) == '-'){
                    break;
                }
                // do something
                // count = 低级的进位

                int a = num1.charAt(i) - '0';
                if (a == 0){
                    break;
                }
                int b = num2.charAt(j) - '0';
                int r = a * b + count;
                count = r / 10;
                r = r % 10;

                res[res.length - 1 - k - m] = (char) ('0' + r);
            }
            // 是否有进位
            if (count > 0){

            }


        }
        return num1;
    }

}
