/**
 * Created by youzhang on 2017/9/30.
 */
public class powXN {
    //the general idea is to use binary search. However, there's some edge cases need to be consider:
    //1. n==0;
    //2. n==Integer.MAX_VALUE|Integer.MIN_VALUE
    //3. x is negative
    public double myPow(double x, int n) {
        if(n==0) return 1;
        boolean flag = false;
        long ln = n;
        if(n<0){
            ln = -ln;
            flag = true;
        }
        long i = 2;
        double res = 1.0;
        while(ln>0){
            i = 2;
            double temp = x;
            while(i<=ln){
                temp*=temp;
                i*=2;
            }
            i/=2;
            ln-=i;
            res*=temp;
        }
        if(flag) return 1.0/res;
        return res;
    }
}
