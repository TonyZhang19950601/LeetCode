/**
 * Created by youzhang on 2017/11/1.
 */
public class EliminateGame {
    /*
    * the idea is to store the head of the list and the step length.
    * When there's only one element left in the list
    * return the head and that will be the result.
    * O(log(n)) time complexity.
    * */
    public int lastRemaining(int n) {
        int start = 1, mul = 1, base = 0;
        boolean forward = true;
        while(n>1) {
            if(forward) {
                start+=mul;
                mul*=2;
                forward = false;
            }
            else{
                if(n%2==1) {
                    start+=mul;
                }
                mul*=2;
                forward = true;
            }
            if(n%2==1)
                n--;
            n /=2;

        }
        return start;
    }
}
