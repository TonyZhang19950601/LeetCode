/**
 * Created by youzhang on 2017/8/24.
 */
import java.util.List;
import java.util.ArrayList;
public class LexicographicalNumbers {
    /*
    * This is in fact a dfs questoin, we just need to do a preorder traversal. The solution below has O(n) time comlexity and O(1) space complexity.
    * One thing need to pay attention is to dealing with numbers end with 9.We need to make the next number not end with 0.
    * for example, the next number of 19 is 2,139 is 14, 199 is 2.209 is 21.
    * */
    public List<Integer> lexicalOrder(int n) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        int count = 0;
        int current = 1;
        while(count<n){
            if(current<=n){
                result.add(current);
                count++;
                current = current*10;
            }
            else{
                current = current/10;
                if(current%10==9){
                    current++;
                    while(current%10==0) current/=10;
                }
                else{
                    current++;
                }
            }
        }
        return result;
    }
}
