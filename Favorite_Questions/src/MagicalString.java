/**
 * Created by youzhang on 2017/11/18.
 */
public class MagicalString {
    public int magicalString(int n) {
        if(n==0)
            return 0;
        int[] s = new int[n];
        s[0] = 1;
        s[1] = 2;
        s[2] = 2;
        int c_pointer = 2, total = 1, s_pointer = 3;
        while(s_pointer<n) {
            int temp = 3-s[s_pointer-1];
            s[s_pointer++] = temp;
            if(temp==1)
                total++;
            if(s_pointer==n)
                break;
            if(s[c_pointer]==2){
                s[s_pointer++] = temp;
                if(temp==1)
                    total++;
            }
            c_pointer++;
        }
        return total;
    }
}
